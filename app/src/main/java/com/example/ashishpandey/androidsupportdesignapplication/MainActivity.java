package com.example.ashishpandey.androidsupportdesignapplication;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.ViewPagerOnTabSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener,ViewPager.OnPageChangeListener
{
    private static final long RIPPLE_DURATION = 250;
    DrawerLayout drawerLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    //CollapsingToolbarLayout tabLayout;
    NavigationView navigationView;

    FloatingActionButton fab;
    ViewPager viewPager;
    int drawerState = 0;

    private RelativeLayout root;
    private ImageView toolbarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (RelativeLayout)findViewById(R.id.mainLayout);
        toolbarIcon = (ImageView) findViewById(R.id.content_hamburger);
        setupNavigationView();
        setupToolbar();
        setUpViewPager();
        setupTablayout();
        //setupCollapsingToolbarLayout();
        setupFab();

        toolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerState==0) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    drawerState = 1;
                    setHomeButtonAnimation();
                }
                else if(drawerState==1)
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerState = 0;
                    setHomeButtonAnimation();
                }
            }
        });



    }



    private void setupNavigationView(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


    }
    private void  setUpViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //ImageView iv = (ImageView) item.getActionView().findViewById(item.getItemId());
        switch (item.getItemId()) {

            case android.R.id.home:
                if(drawerState==0) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    drawerState = 1;
                    setHomeButtonAnimation();
                }
                else if(drawerState==1)
                {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerState = 0;
                    setHomeButtonAnimation();
                }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        final MenuItem item = menu.findItem(R.id.action_refresh);
//
//        item.setActionView(R.layout.rotating_refresh);
//        ImageView refresh = (ImageView) item.getActionView().findViewById(R.id.refreshButton);
//
//        refresh.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(item);
//            }
//        });
//        return true;

        return super.onCreateOptionsMenu(menu);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        getSupportActionBar().setTitle(null);
        //ab.setHomeAsUpIndicator(R.drawable.menu_icon);
        //ab.setDisplayHomeAsUpEnabled(true);
        setAnimationForToolbar(toolbar);

//        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
//        root.addView(guillotineMenu);
//
//        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), findViewById(R.id.content_hamburger))
//                .setStartDelay(RIPPLE_DURATION)
//                .setActionBarViewForAnimation(toolbar)
//                .build();

    }

    private void setHomeButtonAnimation()
    {
        //LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //ImageView imageView = (ImageView)inflater.inflate(android.R.id.home,null);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        toolbarIcon.startAnimation(animation);
        if(drawerState==0)
        toolbarIcon.setBackground(getResources().getDrawable(R.drawable.ic_menu_90));
        else if(drawerState==1)
            toolbarIcon.setBackground(getResources().getDrawable(R.drawable.menu_icon));
    }

    private void setAnimationForToolbar(Toolbar toolbar){
        int noOfChild = toolbar.getChildCount();
        View view;

        toolbar.setAlpha(0);
        toolbar.setTranslationY(-300);

        toolbar.animate().setDuration(300).translationY(0).alpha(1);

/* For loop animates toolbar's child elements to give a nice parallax effect */
        for(int i = 0; i < noOfChild; i++ ){
            view = toolbar.getChildAt(i);
            view.setTranslationY(-300);
            view.animate().setStartDelay(200).setDuration(500).translationY(0);
        }
    }

    private void setupTablayout(){
        // tabLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolLayout);
       // tabLayout.setTitle("My Application");

        tabLayout = (TabLayout) findViewById(R.id.tabs);


        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
    }

    private void setupFab(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            showSnackBar("This is Snackbar",R.id.main_content);

        }
    }

    private void showSnackBar(String message,int id)
    {
        Snackbar
                .make(findViewById(id),
                        message,
                        Snackbar.LENGTH_LONG)
                .setAction("Action", this)
                .show(); // Do not forget to show!
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        menu.getItem(0).setChecked(true);


//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        menuItem.setChecked(true);
//                        drawerLayout.closeDrawers();
//                        return true;
//                    }
//                });
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TestFragment(), "Category 1");
        adapter.addFragment(new CheeseListFragment(), "Category 2");
        adapter.addFragment(new CheeseListFragment(), "Category 3");
        adapter.addFragment(new CheeseListFragment(), "Category 4");
        viewPager.setAdapter(adapter);
        //new TabLayout.ViewPagerOnTabSelectedListener(viewPager);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.navigation_item_1)
        {
            viewPager.setCurrentItem(0);
            showSnackBar("Category 1 Selected",R.id.main_content);

        }
        else if(menuItem.getItemId()==R.id.navigation_item_2)
        {
            viewPager.setCurrentItem(1);
            showSnackBar("Category 2 Selected",R.id.main_content);
        }
        else if(menuItem.getItemId()==R.id.navigation_item_3)
        {
            viewPager.setCurrentItem(2);
            showSnackBar("Category 3 Selected",R.id.main_content);
        }
        else if(menuItem.getItemId()==R.id.navigation_item_4)
        {
            viewPager.setCurrentItem(3);
            showSnackBar("Category 4Selected",R.id.main_content);
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        //tabLayout.setupWithViewPager(viewPager);

        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Menu menu = navigationView.getMenu();
        menu.getItem(tab.getPosition()).setChecked(true);
        viewPager.setCurrentItem(tab.getPosition());
        //setHomeButtonAnimation();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    static class Adapter extends FragmentPagerAdapter {
            private final List<Fragment> mFragments = new ArrayList<>();
            private final List<String> mFragmentTitles = new ArrayList<>();

            public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
