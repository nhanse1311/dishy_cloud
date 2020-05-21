package com.example.dishycloud.adaptes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dishycloud.fragments.EditOneFragment;
import com.example.dishycloud.fragments.EditThreeFragment;
import com.example.dishycloud.fragments.EditTwoFragment;

public class FragmentEditRecipeAdapter extends FragmentStatePagerAdapter {
    private static int NUMBER_PAGE = 3;

    public FragmentEditRecipeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EditOneFragment.newInstance();
            case 1:
                return EditTwoFragment.newInstance();
            case 2:
                return EditThreeFragment.newInstance();
            default:
                return EditOneFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUMBER_PAGE;
    }
}
