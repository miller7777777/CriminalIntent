package com.bignerdranch.android.criminalintent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {


//    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;
//    private Map<UUID, Crime> mCrimesMap;

    private static CrimeLab sCrimeLab;
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context) {

        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

//        mCrimes = new ArrayList<>();
//        mCrimesMap = new HashMap<>();


    }

    public List<Crime> getCrimes(){
//        return mCrimes;
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id){



//        return mCrimesMap.get(id);
        return null;
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Crime crime){

        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    public void addCrime(Crime c){
//        mCrimes.add(c);
//        mCrimesMap.put(c.getId(), c);
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void deleteCrime (Crime c){

//        mCrimes.remove(c);
//        mCrimesMap.remove(c.getId()); //Проверить, насколько это корректно
    }

    private Cursor queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, // columns - с null выбираются все столбцы
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return cursor;
    }
}
