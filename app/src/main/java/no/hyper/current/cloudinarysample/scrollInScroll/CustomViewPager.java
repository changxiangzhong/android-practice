package no.hyper.current.cloudinarysample.scrollInScroll;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

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

}
