package no.hyper.current.cloudinarysample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(this, getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        adapter.setTabIcons(tabLayout);

    }

}

