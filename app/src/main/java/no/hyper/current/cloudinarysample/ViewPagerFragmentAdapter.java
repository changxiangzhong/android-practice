package no.hyper.current.cloudinarysample;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by xiangzhc on 06/06/16.
 */
class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private int childCount;
    private Context ctx;
    private TabLayout tabIcons;

    public ViewPagerFragmentAdapter(Context ctx, FragmentManager fm, int childCount) {
        super(fm);
        Log.v(getClass().getSimpleName(), "childCount = " + childCount);
        this.childCount = childCount;
        this.ctx = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        Log.v(getClass().getSimpleName(), "childCount = " + childCount);

        if (position < getCount() - 1) {
            return MainFragment.getInstance(position);
        } else {
            return ViewPagerFragment.getInstance();
        }
    }

    @Override
    public int getCount() {
        return childCount;
    }

    public void setTabIcons(TabLayout tabIcons) {
        for (int i = 0; i < tabIcons.getTabCount(); i++) {
            tabIcons.getTabAt(i).setIcon(android.R.drawable.ic_menu_preferences);
        }
    }
}
