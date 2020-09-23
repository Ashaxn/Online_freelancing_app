package com.example.onlinefreelaceapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.onlinefreelaceapp.OrderToBeReceivedCompleted;
import com.example.onlinefreelaceapp.OrderToBeReceivedProcessing;
import com.example.onlinefreelaceapp.OrderToBeReceivedReceived;
import com.example.onlinefreelaceapp.OrderstomeCompleted;
import com.example.onlinefreelaceapp.OrderstomeProcessing;
import com.example.onlinefreelaceapp.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterOrderToBeReceived extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tabtext_processing,R.string.tabtext_received, R.string.tabtext_completed};
    private final Context mContext;

    public SectionsPagerAdapterOrderToBeReceived(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new OrderToBeReceivedProcessing();
                break;
            case 1:
                fragment = new OrderToBeReceivedReceived();
                break;
            case 2:
                fragment = new OrderToBeReceivedCompleted();
                break;
        }

        return  fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}