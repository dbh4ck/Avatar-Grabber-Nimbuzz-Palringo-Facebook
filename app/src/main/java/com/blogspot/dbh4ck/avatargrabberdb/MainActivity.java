package com.blogspot.dbh4ck.avatargrabberdb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blogspot.dbh4ck.avatargrabberdb.Dialogs.dbDialog;
import com.blogspot.dbh4ck.avatargrabberdb.Fragments.FacebookFragment;
import com.blogspot.dbh4ck.avatargrabberdb.Fragments.NimbuzzFragment;
import com.blogspot.dbh4ck.avatargrabberdb.Fragments.PalringoFragment;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private dbDialog InfoDialog;
    private Boolean exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("Nimbuzz"));
        tabLayout.addTab(tabLayout.newTab().setText("Palringo"));
        tabLayout.addTab(tabLayout.newTab().setText("Facebook"));

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(viewPager != null){
            viewPager.setCurrentItem(tab.getPosition());
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            InfoDialog = new dbDialog(this);
            InfoDialog.show();
            InfoDialog.setCanceledOnTouchOutside(false);
            //return true;
        }

        else if (id == R.id.exit){
            System.exit(1);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

    }

    private class Pager extends FragmentStatePagerAdapter {
        int tabCount;

        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    NimbuzzFragment tab1 = new NimbuzzFragment();
                    return tab1;
                case 1:
                    PalringoFragment tab2 = new PalringoFragment();
                    return tab2;
                case 2:
                    FacebookFragment tab3 = new FacebookFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return tabCount;
        }

        //Overriden method getPageTitle to get the title of tabs
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "Nimbuzz";
                case 1:
                    return "Palringo";
                case 2:
                    return "Facebook";

            }
            return null;
        }
    }


}
