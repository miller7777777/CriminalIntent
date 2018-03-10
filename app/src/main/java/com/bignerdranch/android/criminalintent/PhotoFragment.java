package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;


public class PhotoFragment extends DialogFragment {
    private static final String ARG_PHOTO = "Photo";
    public static final String EXTRA_PHOTO = "com.bignerdranch.android.criminalintent.photo";
    private ImageView mImageView;

    public static PhotoFragment newInstance(File photoFile){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, photoFile);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        File photoFile = (File) getArguments().getSerializable(ARG_PHOTO);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_photo, null);

        mImageView = (ImageView) v.findViewById(R.id.full_size_crime_photo);

        if (photoFile == null || !photoFile.exists()) {
            mImageView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    photoFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);
        }

        return  new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Photo of crime:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create();
    }
}
