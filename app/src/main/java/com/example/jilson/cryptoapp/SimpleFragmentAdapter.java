package com.example.jilson.cryptoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

final class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private Context context;

    public SimpleFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /*new fragment is returned according to the position*/
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new SubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("fragment_id",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    /*This method provides text for curresponding tab positions*/
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return context.getString(R.string.inr);
        }if(position == 1) {
            return context.getString(R.string.bitcoin);
        }if(position == 2){
            return context.getString(R.string.ether);
        } else {
            return context.getString(R.string.ripple);
        }
    }
}
