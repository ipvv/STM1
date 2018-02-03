package com.vsahin.moneycim.ChartList;

import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.vsahin.moneycim.R;

import butterknife.ButterKnife;

public class chartlist extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartlist);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.getTabAt(0).setIcon(R.drawable.piechart_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.piechart_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.piechart_icon);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chartlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    tabchart1 tab1 = new tabchart1();

                    return tab1;
                case 1:
                    tabchart2 tab2 = new tabchart2();
                    return tab2;
                case 2:
                    tabchart3 tab3 = new tabchart3();
                    return tab3;
                default:
                    return null;


            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GCYHVJ";
                case 1:
                    return "UJYGH";
                case 2:
                    return "YIBKNL";

            }
            return null;
        }
    }
}
