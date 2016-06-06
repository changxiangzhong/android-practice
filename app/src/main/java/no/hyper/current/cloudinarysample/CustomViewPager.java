package no.hyper.current.cloudinarysample;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xiangzhc on 06/06/16.
 */
public class CustomViewPager extends ViewPager {
    private GestureDetectorCompat detector;

    public CustomViewPager(Context context) {
        super(context);
        onCreate(context);
    }


    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    private void onCreate(Context context) {
        GestureListener listener = new GestureListener(CustomViewPager.class.getSimpleName());
        detector = new GestureDetectorCompat(context, listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (detector.onTouchEvent(ev)) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        Log.v(getClass().getSimpleName(), "canScroll(), view = " + v.getClass().getSimpleName());
        if (v instanceof CustomHorizontalScrollView) {
            CustomHorizontalScrollView hScrollView = (CustomHorizontalScrollView) v;
            return hScrollView.canScroll(dx);
        } else {
            return super.canScroll(v, checkV, dx, x, y);
        }
    }
}
