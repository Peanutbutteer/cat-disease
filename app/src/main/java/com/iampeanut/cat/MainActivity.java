package com.iampeanut.cat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private ProgressBar pbProgress;
    private Button btnNext;
    private Button btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        pbProgress.setMax(getProblemSize());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        pbProgress.setProgress(1);
        mViewPager.setOffscreenPageLimit(6);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Page
                pbProgress.setProgress(position + 1);
                if (position == getProblemSize() - 1) {
                    btnNext.setText("Finish");
                } else {
                    btnNext.setText("Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (getProblemSize() == 1) {
            btnPrevious.setVisibility(View.GONE);
            btnNext.setText("Finish");
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLastPage()) {
                    processSendData();
                } else {
                    nextPage();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstPage()) {
                    previousPage();
                }
            }
        });
    }

    private int getProblemSize() {
        return ProblemList.getInstance().getProblemList().size() + 1;
    }

    private void changePage(int item) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + item, true);
    }

    private void nextPage() {
        changePage(1);
    }

    private void previousPage() {
        changePage(-1);
    }

    private void processSendData() {
        HashMap<String, String> data = new HashMap<String, String>();
        boolean isCheck = false;

        for (int position = 0; position < mViewPager.getChildCount(); position++) {
            Fragment fragment = getActiveFragment(mViewPager, position);
            if (fragment instanceof PlaceholderFragment) {
                PlaceholderFragment problemListFragment = (PlaceholderFragment) fragment;
                List<Problem> problems = problemListFragment.getProblems();
                for (Problem problem : problems) {
                    if (problem.isProblem()) {
                        if (problem.getKey() != null) {
                            data.put(problem.getKey().toLowerCase(), problem.getValue().toLowerCase());
                            isCheck = true;
                        }
                    } else {
                        if (!data.containsKey(problem.getKey())) {
                            data.put(problem.getKey().toLowerCase(), "no");
                        }
                    }
                }
            }
            if (fragment instanceof BasicInfomationFragment) {
                BasicInfomationFragment basicInfomationFragment = ((BasicInfomationFragment) fragment);
                data.put("type", basicInfomationFragment.getSpecies());
                data.put("sex", basicInfomationFragment.getGender());
                data.put("age", basicInfomationFragment.getAge());
                data.put("weight", basicInfomationFragment.getWeight());
            }
        }
        if(isCheck) {
            startActivity(ResultActivity.getStartIntent(MainActivity.this, data));
            finish();
        } else {
            Toast.makeText(this, "ไม่เห็นเป็นอะไรเลย~~~", Toast.LENGTH_SHORT).show();
        }
    }

    public Fragment getActiveFragment(ViewPager container, int position) {
        String name = makeFragmentName(container.getId(), position);
        return getSupportFragmentManager().findFragmentByTag(name);
    }

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    private boolean isLastPage() {
        return mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1;
    }

    private boolean isFirstPage() {
        return mViewPager.getCurrentItem() == 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (isFirstPage()) {
            super.onBackPressed();
        } else {
            previousPage();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}