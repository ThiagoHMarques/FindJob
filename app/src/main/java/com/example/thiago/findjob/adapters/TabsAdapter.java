package com.example.thiago.findjob.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.thiago.findjob.fragments.CadAlunoFragment;
import com.example.thiago.findjob.fragments.CadEmpresaFragment;

/**
 * Created by THIAGO on 26/09/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter{
    private Context mContext;
    private String[] titles = {"ALUNO","EMPRESA"};

    public TabsAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext = context;

    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;

        if(position == 0){
            frag = new CadAlunoFragment();

        }if(position == 1){
            frag = new CadEmpresaFragment();
        }

        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (titles[position]);
    }
}
