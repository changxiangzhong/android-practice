package no.hyper.current.cloudinarysample.scrollInScroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.hyper.current.cloudinarysample.R;

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
