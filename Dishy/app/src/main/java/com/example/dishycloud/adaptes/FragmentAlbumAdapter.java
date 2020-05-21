package com.example.dishycloud.adaptes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dishycloud.fragments.MyAlbumFragment;
import com.example.dishycloud.fragments.SavedAlbumFragment;

public class FragmentAlbumAdapter extends FragmentStatePagerAdapter {
    private static int NUM_PAGE = 2;

    public FragmentAlbumAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SavedAlbumFragment.newInstance();
            case 1:
                return MyAlbumFragment.newInstance();
            default:
                return SavedAlbumFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }
}
