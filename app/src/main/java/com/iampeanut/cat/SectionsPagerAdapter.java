package com.iampeanut.cat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by satjawatpanakarn on 5/2/2017 AD.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    int[] imageGif = {0, R.drawable.hurt,R.drawable.unstable,R.drawable.ich,R.drawable.ungi,0};

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return BasicInfomationFragment.newInstance();
        }
        return PlaceholderFragment.newInstance(ProblemList.getInstance().getProblemList().get(position - 1), ProblemList.getInstance().getQuestionList().get(position - 1), imageGif[position - 1]);
    }

    @Override
    public int getCount() {
        return ProblemList.getInstance().getProblemList().size() + 1;
    }
}