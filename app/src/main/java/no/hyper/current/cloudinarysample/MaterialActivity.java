package no.hyper.current.cloudinarysample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;

/**
 * Created by xiangzhc on 02/06/16.
 */
public class MaterialActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        tabLayout = (TabLayout) findViewById(R.id.tab_container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        MainFragmentAdapter adapter = new MainFragmentAdapter(this, getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        adapter.setTabIcons(tabLayout);

    }

    private static class MainFragmentAdapter extends FragmentPagerAdapter {
        private int childCount;
        private Context ctx;
        private TabLayout tabIcons;

        public MainFragmentAdapter(Context ctx, FragmentManager fm, int childCount) {
            super(fm);
            Log.v(getClass().getSimpleName(), "childCount = " + childCount);
            this.childCount = childCount;
            this.ctx = ctx;
        }

        @Override
        public Fragment getItem(int position) {
            Log.v(getClass().getSimpleName(), "childCount = " + childCount);
            return MainFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return childCount;
        }

        public void setTabIcons(TabLayout tabIcons) {
            for(int i = 0; i < tabIcons.getTabCount(); i++) {
                tabIcons.getTabAt(i).setIcon(android.R.drawable.ic_menu_preferences);
            }
        }
    }
}

