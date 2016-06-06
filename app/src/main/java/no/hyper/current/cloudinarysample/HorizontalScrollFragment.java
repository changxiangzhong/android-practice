package no.hyper.current.cloudinarysample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiangzhc on 06/06/16.
 */
public class HorizontalScrollFragment extends Fragment {
    public static Fragment getInstance() {
        return new HorizontalScrollFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_horizontal_scroll, container, false);
        return root;
    }

}
