package no.hyper.current.cloudinarysample;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by xiangzhc on 06/06/16.
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    private GestureDetectorCompat detector;

    public CustomHorizontalScrollView(Context context) {
        super(context);
        onCreate(context);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);
    }

    private void onCreate(Context context) {
        GestureListener listener = new GestureListener(CustomHorizontalScrollView.class.getSimpleName());
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
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        Log.v(getClass().getSimpleName(), "clampedX = " + clampedX);
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (clampedX) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }
}
