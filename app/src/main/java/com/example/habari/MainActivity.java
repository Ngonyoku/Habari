package com.example.habari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.habari.Adapters.MainViewPagerAdapter;
import com.example.habari.Fragments.ContactsFragment;
import com.example.habari.Fragments.DialFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager mainViewPager;
    private MainViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tool_bar);
        mainViewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        viewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), 0);

        viewPagerAdapter.addFragment(new DialFragment(), ""); //Dial Fragment
        viewPagerAdapter.addFragment(new ContactsFragment(), ""); //Contacts Fragment

        mainViewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(mainViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dialpad);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person);
        setSupportActionBar(toolbar);

    }
}
