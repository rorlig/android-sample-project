package com.example.android.rssfeed;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.*;
import android.widget.*;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.rssfeed.adapter.Sectionizer;
import com.example.android.rssfeed.adapter.SimpleSectionAdapter;

import java.util.ArrayList;
import java.util.List;


public class RssfeedActivity extends FragmentActivity {
        //drawer
        private DrawerLayout mDrawerLayout;
        //list
        private ListView mDrawerList;

        //toggle the drawer
        private ActionBarDrawerToggle mDrawerToggle;

        //title
        private CharSequence mDrawerTitle;
        private CharSequence mTitle;
        private String[] mPlanetTitles;

    private ViewPager mViewPager;
        private TabAdapter mTabsAdapter;
        private DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
        private String TAG = "RssfeedActivity";

    // Unchanged
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            ActionBar ab = getActionBar();
//            ab.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.
            mDemoCollectionPagerAdapter =
                    new DemoCollectionPagerAdapter(
                            getSupportFragmentManager());

            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPager.setAdapter(mDemoCollectionPagerAdapter);


            // 1. Your data source
            List<Item> items = getItems();


            ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.list_item_simple, items);
            SimpleSectionizer simpleSectionizer  = new SimpleSectionizer();
            // 5. Wrap your adapter within the SimpleSectionAdapter
            SimpleSectionAdapter<Item> sectionAdapter = new SimpleSectionAdapter<Item>(this,
                    itemAdapter, R.layout.section_header, R.id.title, simpleSectionizer);

//        6. Set the adapter to your ListView
//        setListAdapter(sectionAdapter);




            mTitle = mDrawerTitle = getTitle();
//            mPlanetTitles = getResources().getStringArray(R.array.planets_array);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);

            // set a custom shadow that overlays the main content when the drawer opens
//            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            // set up the drawer's list view with items and click listener
            mDrawerList.setAdapter(sectionAdapter);
//                new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mPlanetTitles));
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

            // enable ActionBar app icon to behave as action to toggle nav drawer
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);

            // ActionBarDrawerToggle ties together the the proper interactions
            // between the sliding drawer and the action bar app icon
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,                  /* host Activity */
                    mDrawerLayout,         /* DrawerLayout object */
                    R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                    R.string.drawer_open,  /* "open drawer" description for accessibility */
                    R.string.drawer_close  /* "close drawer" description for accessibility */
            ) {
                public void onDrawerClosed(View view) {
                    getActionBar().setTitle(mTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                    getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            if (savedInstanceState == null) {
                selectItem(0);
            }





//            mViewPager = new ViewPager(this);
//            mViewPager.setId(R.id.pager);
//
//            mTabsAdapter = new TabAdapter(this, mViewPager);
//            mTabsAdapter.addTab(ab.newTab().setText("").setIcon(getResources().getDrawable(R.drawable.ic_action_refresh)), Fragment1.class, null);
//            mTabsAdapter.addTab(ab.newTab().setText("").setIcon(getResources().getDrawable(R.drawable.ic_action_refresh)), Fragment1.class, null);
//            mTabsAdapter.addTab(ab.newTab().setText("").setIcon(getResources().getDrawable(R.drawable.ic_action_refresh)), Fragment1.class, null);
//            mTabsAdapter.addTab(ab.newTab().setText("").setIcon(getResources().getDrawable(R.drawable.ic_action_refresh)), Fragment1.class, null);
//            mTabsAdapter.addTab(ab.newTab().setText("").setIcon(getResources().getDrawable(R.drawable.ic_action_refresh)), Fragment1.class, null);

//            ActionBar.Tab tab = ab.newTab()
//                    .setText( R.string.frag1 )
//                    .setTabListener(
//                            new MyTabListener( this,
//                                    Fragment1.class.getName() ) );
//            ab.addTab( tab );
//
//            tab = ab.newTab()
//                    .setText( R.string.frag2 )
//                    .setTabListener(
//                            new MyTabListener( this,
//                                    Fragment2.class.getName() ) );
//            ab.addTab( tab );


//            for (int i = 0; i < 3; i++) {
//                ab.addTab(
//                        ab.newTab()
//                                .setText("Tab " + (i + 1))
//                                .setTabListener(tabListener));
//            }


//            mViewPager.setOnPageChangeListener(
//                    new ViewPager.SimpleOnPageChangeListener() {
//                        @Override
//                        public void onPageSelected(int position) {
//                            // When swiping between pages, select the
//                            // corresponding tab.
//                            Log.d(TAG, "viewpage swipe");
//                            getActionBar().setSelectedNavigationItem(position);
//                        }
//                    }
//            );
//        }

        }



    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
//        Fragment fragment = new PlanetFragment();
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);
//
//        android.app.FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
//        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    /*
   * Sectionizer supplies labels based on the distance from the current city.
   */
    class SimpleSectionizer implements Sectionizer<Item> {

        public SimpleSectionizer() {
        }

        @Override
        public String getSectionTitleForItem(Item item) {
            String sectionTitle = "Unknown";

            return item.getHeader();
        }
    }


    /*
      * This is your data source. In reality, this could come from a SQLite database,
      * a remote server or a flat-file.
      */
    private List<Item> getItems() {
        List<Item> items = new ArrayList<Item>();

//        items.add(new Item("Profile", Item.HEADER));
        items.add(new Item("My Profile", "Profile" , Item.NORMAL));
        items.add(new Item("Rental History","Profile", Item.NORMAL));
        items.add(new Item("Saved Apartments", "Profile", Item.NORMAL));
//        items.add(new Item("Setting", Item.HEADER));
        items.add(new Item("Invite Friends", "Setting", Item.NORMAL));
        items.add(new Item("Privacy Settings","Setting", Item.NORMAL));
//        items.add(new Item("Info", Item.HEADER));
        items.add(new Item("About", "Info", Item.NORMAL));
        items.add(new Item("Feedback","Info", Item.NORMAL));
        items.add(new Item("Privacy and Terms of Service", "Info", Item.NORMAL));
        return items;
    }


    // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // When the tab is selected, switch to the
                // corresponding page in the ViewPager.
                Log.d(TAG, "tab selected ActionBar::Tablistener");
//                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };
        //NEW
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.mainmenu, menu);
            return true;
        }

        /* Called whenever we call invalidateOptionsMenu() */
        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            // If the nav drawer is open, hide action items related to the content view
            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            menu.findItem(R.id.action_search).setVisible(!drawerOpen);  //search
            return super.onPrepareOptionsMenu(menu);
        }

        //NEW
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            switch (item.getItemId()) {
                case R.id.action_search:
                    Toast.makeText(this, "Action search selected", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.action_settings:
                    Toast.makeText(this, "Action Settings selected", Toast.LENGTH_SHORT)
                            .show();
                    break;

                default:
                    Toast.makeText(this, "Default action selected", Toast.LENGTH_SHORT)
                            .show();
                    return super.onOptionsItemSelected(item);
            }

            return true;
        }


//    private class MyTabListener
//            implements ActionBar.TabListener {
//        private android.support.v4.app.Fragment mFragment;
//        private final Activity mActivity;
//        private final String mFragName;
//
//        public MyTabListener( Activity activity,
//                              String fragName ){
//            mActivity = activity;
//            mFragName = fragName;
//        }
//
//        @Override
//        public void onTabReselected( ActionBar.Tab tab,
//                                     FragmentTransaction ft ){
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void onTabSelected( ActionBar.Tab tab,
//                                   FragmentTransaction ft ){
////            mFragment = Fragment.instantiate( mActivity,
////                    mFragName );
////            ft.add( android.R.id.content, mFragment );
//            mViewPager.setCurrentItem(tab.getPosition());
//        }
//
//        @Override
//        public void onTabUnselected( ActionBar.Tab tab,
//                                     FragmentTransaction ft ){
//            ft.remove( mFragment );
//            mFragment = null;
//        }
//    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int i) {
            android.support.v4.app.Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            // For this contrived example, we have a 100-object collection.
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DemoObjectFragment extends android.support.v4.app.Fragment {

        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }



    /*
     * A custom adapter that extends the ArrayAdapter<T>.
     */
    class ItemAdapter extends ArrayAdapter<Item> {
        private List<Item> items;

        public ItemAdapter(Context context, int textViewResourceId,
                           List<Item> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder holder = null;

            if(view == null) {
                view = View.inflate(RssfeedActivity.this,
                        R.layout.list_item_simple, null);

                holder = new Holder();
                holder.cityTextView = (TextView) view.findViewById(R.id.item);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }

            // Set properties
            Item item = items.get(position);
            holder.cityTextView.setText(item.getTitle());

            return view;
        }
    }

    /*
     * Holder for the custom adapter.
     */
    static class Holder {
        public TextView cityTextView;
//        public TextView geoPointTextView;
    }

}
