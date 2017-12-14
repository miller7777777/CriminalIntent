package com.bignerdranch.android.criminalintent;


import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {


    private List<Crime> mCrimes;
    private Map<UUID, Crime> mCrimesMap;

    private static CrimeLab sCrimeLab;
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context) {

        mCrimes = new ArrayList<>();
        mCrimesMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved(i / 2 == 0);
//            crime.setRequiresPolice(i / 2 == 0);
//            crime.setRequiresPolice(Math.random() >= 0.9);
            mCrimes.add(crime);
            mCrimesMap.put(crime.getId(), crime);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){


//        for (Crime crime : mCrimes) {
//
//            if(crime.getId().equals(id)) {
//                return crime;
//            }
//        }
//        return null;
        return mCrimesMap.get(id);
    }
}
