package com.bignerdranch.android.criminalintent;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import static android.widget.CompoundButton.*;

public class CrimeFragment extends Fragment{

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Menu menu;
    private List<Crime> mCrimes;
    private Intent mIntent;


    private static final String ARG_CRIME_ID = "crime_id";

    public static CrimeFragment newInstance(UUID crimeID){
        Bundle args = new Bundle();
        CrimeFragment fragment = new CrimeFragment();
        args.putSerializable(ARG_CRIME_ID, crimeID);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
//        UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);

        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Здесь намеренно оставлено пустое место
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

                // Здесь намеренно оставлено пустое место
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mCrime.getTitle());
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.crime_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mCrimes = CrimeLab.get(getActivity()).getCrimes();
        switch (item.getItemId()){
            case R.id.first_crime:
                //TODO:
                Toast.makeText(getActivity(), "first crime", Toast.LENGTH_LONG).show();
                mIntent = CrimePagerActivity.newIntent(getActivity(), mCrimes.get(0).getId());
                startActivity(mIntent);
                return true;
            case R.id.last_crime:
                //TODO:
                Toast.makeText(getActivity(), "last crime", Toast.LENGTH_LONG).show();
                mIntent = CrimePagerActivity.newIntent(getActivity(), mCrimes.get(mCrimes.size()-1).getId());
                startActivity(mIntent);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
