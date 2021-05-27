package com.myapplicationdev.android.problemstatement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ViewPager vPager;
    Button btnReadLater;
    ArrayList<Fragment> fragments;
    PagerAdapter adapter;
    SharedPreferences sharedPreferences;
    private RssReader rssReader = new RssReader((RssReader.RssCallback) this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadLater = findViewById(R.id.btnReadLater);
        vPager = findViewById(R.id.pager);
        fragments = new ArrayList<>();
        sharedPreferences = getSharedPreferences("L06 PS", Context.MODE_PRIVATE);

        FragmentManager fm = getSupportFragmentManager();
        fragments.add(new Frag1());
        fragments.add(new Frag2());
        fragments.add(new Frag3());

        adapter = new FactsFragmentPagerAdapter(fm, fragments);
        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(v -> {
            onPause();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("currpage", vPager.getCurrentItem());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int currPage = sharedPreferences.getInt("currpage", 0);
        vPager.setCurrentItem(currPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rssReader.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int max = vPager.getChildCount();
        switch (item.getItemId()) {
            case R.id.itemNext:
                if (vPager.getCurrentItem() < max - 1) {
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage, true);
                }
                return true;
            case R.id.itemPrevious:
                if (vPager.getCurrentItem() > 0) {
                    int previousPage = vPager.getCurrentItem() - 1;
                    vPager.setCurrentItem(previousPage, true);
                }
                return true;
            case R.id.itemRandom:
                Random random = new Random();
                vPager.setCurrentItem(random.nextInt(max), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}