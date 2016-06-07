package no.hyper.current.cloudinarysample.scrollInScroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import no.hyper.current.cloudinarysample.R;

/**
 * Created by xiangzhc on 06/06/16.
 */
public class MainFragment extends Fragment {
    private static final String PARAM_INDEX = "PARAM_INDEX";

    private RecyclerView recyclerView;
    private int index;

    public static Fragment getInstance(int position) {
        Bundle b = new Bundle();
        b.putInt(PARAM_INDEX, position);
        Fragment f = new MainFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt(PARAM_INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.random_list);
        root.setBackgroundColor(new Random().nextInt());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setAdapter(new RandomStringListAdapter(index));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    private static class RandomStringListAdapter extends RecyclerView.Adapter<VH> {
        private Random r;
        private int itemCount;

        public RandomStringListAdapter(int itemCount) {
            r = new Random();
            if (itemCount == 0) {
                this.itemCount = 7;
            } else {
                this.itemCount = r.nextInt(97) + 3;
            }
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            holder.tv.setText(String.valueOf(r.nextDouble()));
        }

        @Override
        public int getItemCount() {
            return itemCount;
        }
    }

    private static class VH extends RecyclerView.ViewHolder {
        public TextView tv;
        public VH(View itemView) {
            super(itemView);
            this.tv = (TextView) itemView;
        }
    }
}
