package saedc.example.com.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import saedc.example.com.Alarm.AlarmActivity;
import saedc.example.com.BuildConfig;
import saedc.example.com.R;
import saedc.example.com.View.AddAndEditSpending.AddAndEditSpendingFragment;
import saedc.example.com.View.SpendingList.SpendingListFragment;
import saedc.example.com.ChartList.chartlist;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(saedc.example.com.View.MainActivity.this);

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(this,"hi6rjtehdsgxyhwusjrh",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"hiiuw5ystegyhui6oeiujdrhhdi",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, chartlist.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
