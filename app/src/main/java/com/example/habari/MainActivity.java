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

        viewPagerAdapter.addFragment(new DialFragment(), "Dial");
        viewPagerAdapter.addFragment(new ContactsFragment(), "Contacts");

        mainViewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
        setSupportActionBar(toolbar);
    }
}
