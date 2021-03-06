package com.app.navigationdrawerwithfragment.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.app.navigationdrawerwithfragment.R;
import com.app.navigationdrawerwithfragment.fragment.Fifth;
import com.app.navigationdrawerwithfragment.fragment.First;
import com.app.navigationdrawerwithfragment.fragment.Fourth;
import com.app.navigationdrawerwithfragment.fragment.Second;
import com.app.navigationdrawerwithfragment.fragment.Sixth;
import com.app.navigationdrawerwithfragment.fragment.Third;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        startFragment(First.class, false);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,
                R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    actionBarDrawerToggle.onDrawerSlide(drawerLayout, 1);
                } else {
                    super.onDrawerClosed(drawerView);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                    showArrow(1, 0);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.first:
                startFragment(First.class, false);
                break;
            case R.id.second:
                startFragment(Second.class, false);
                break;
            case R.id.third:
                startFragment(Third.class, false);
                break;
            case R.id.fourth:
                startFragment(Fourth.class, false);
                break;
            case R.id.fifth:
                startFragment(Fifth.class, false);
                break;
            case R.id.sixth:
                startFragment(Sixth.class, false);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startFragment(Class fragmentClass, boolean b) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fm = getSupportFragmentManager();
        if (b){
            fm.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            fm.beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();
        }
    }

    public void showArrow(int start, int end) {
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                Log.e("Offset : ", String.valueOf(slideOffset));
                actionBarDrawerToggle.onDrawerSlide(drawerLayout, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        // You can change this duration to more closely match that of
        // the default animation.
        anim.setDuration(250);
        anim.start();
    }

    public void showBackArrow() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
