package com.vsahin.moneycim.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vsahin.moneycim.Alarm.AlarmActivity;
import com.vsahin.moneycim.BuildConfig;
import com.vsahin.moneycim.R;
import com.vsahin.moneycim.View.AddAndEditSpending.AddAndEditSpendingFragment;
import com.vsahin.moneycim.View.SpendingList.SpendingListFragment;
import com.vsahin.moneycim.ChartList.chartlist;
import com.vsahin.moneycim.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    InputMethodManager imm;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);

        //Starting hide for protect state when rotate screen
        fab.hide();

        //Add this fragment just at start and dont add to backstack
        if (getFragmentBackStackCount() == 0) {
            showRootFragment(SpendingListFragment.newInstance());
            fab.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.about:


// 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics

                builder.setMessage("STM Is an application for spending tracking money " +
                        "this application is graduation project for uqu University."+"\n\n-TEAM MEMBERS \n\n" +
                        "# SAED SAAD\n" +
                        "# FHAD ALTWELY\n" +
                        "# RYADE ALHRBY\n" +
                        "# ABEDALELH ALSHREF\n"  +
                        "\nAPP VERSION : " +
                        BuildConfig.VERSION_NAME)
                        .setTitle("about")
                        .setIcon(R.drawable.stmproject);

// 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();

                dialog.show();
                return true;
            case R.id.chart:
                Intent intent = new Intent(this, chartlist.class);
                startActivity(intent);

                return true;
            case R.id.exit:
               finish();

                return true;
            case R.id.setting:
                Intent intent1 = new Intent(this, AlarmActivity.class);
                startActivity(intent1);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showRootFragment(Fragment fragment) {
        appBarLayout.setExpanded(true);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {

        int backStackCount = getFragmentBackStackCount();

        //if backstack == 1 it means this is last fragment so show fab
        switch (backStackCount) {
            case 1:
                fab.show();
                break;
        }

        super.onBackPressed();
    }

    @OnClick(R.id.fab)
    public void openAddSpendingFragment() {
        showFragment(AddAndEditSpendingFragment.newInstance());

    }


    public void showFragment(Fragment nextFragment) {
        //be sure to not load same fragment
        if (isLastFragmentInBackstack(nextFragment)) {
            return;
        }

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, nextFragment)
                .addToBackStack(nextFragment.getClass().getName())
                .commit();

        fab.hide();
        appBarLayout.setExpanded(false);
    }

    public boolean isLastFragmentInBackstack(Fragment fragment) {
        String currentFragmentName;
        String nextFragmentName = fragment.getClass().getName();

        //if count is 0 it means there isnt any fragment in backstack
        int count = getFragmentBackStackCount();
        if (count != 0) {
            currentFragmentName = getLastFragmentNameInBackStack();
            if (currentFragmentName.equals(nextFragmentName)) {
                return true;
            }
        }
        return false;
    }

    public String getLastFragmentNameInBackStack() {
        return fragmentManager.getBackStackEntryAt(getFragmentBackStackCount() - 1).getName();
    }

    public int getFragmentBackStackCount() {
        return fragmentManager.getBackStackEntryCount();
    }

    public void hideKeyboard(View v) {
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
