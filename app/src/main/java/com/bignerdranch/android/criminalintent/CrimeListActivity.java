package com.bignerdranch.android.criminalintent;


import android.support.v4.app.Fragment;

import java.util.List;

public class CrimeListActivity extends SingleFragmentActivity {
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
}
