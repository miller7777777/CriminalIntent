package com.bignerdranch.android.criminalintent;


import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.List;

public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks{
    @Override
    protected Fragment createFragment() {
        CrimeLab crimeLab = CrimeLab.get(this);
        List<Crime> crimes = crimeLab.getCrimes();

        if (crimes.size() == 0) {
            return new EmptyCrimeListFragment();
        } else {
//

            return new CrimeListFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null){
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
