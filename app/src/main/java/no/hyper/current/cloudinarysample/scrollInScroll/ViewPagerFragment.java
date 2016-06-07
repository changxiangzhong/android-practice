package no.hyper.current.cloudinarysample.scrollInScroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hyper.current.cloudinarysample.R;

/**
 * Created by xiangzhc on 06/06/16.
 */
public class ViewPagerFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static Fragment getInstance() {
        return new ViewPagerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_pager, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) root.findViewById(R.id.tab_container);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getContext(), getChildFragmentManager(), 4);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.setTabIcons(tabLayout);
    }
}
