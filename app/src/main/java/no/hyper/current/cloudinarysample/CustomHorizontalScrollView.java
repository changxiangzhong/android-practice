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

    public boolean canScroll(int dx) {
        boolean ret;
        int range = computeHorizontalScrollRange();
        int offset = computeHorizontalScrollOffset();
        int extent = computeHorizontalScrollExtent();

        boolean rightEndReached = range == offset + extent;
        boolean leftEndReached = offset == 0;

        if (dx < 0 && rightEndReached) {
            ret = false;
        } else  if (dx > 0 && leftEndReached) {
            ret = false;
        } else {
            ret = true;
        }

        Log.v(getClass().getSimpleName(), "canScroll(), dx = " + dx + ", rightEndReached = " + rightEndReached +
                ", leftEndReached = " + leftEndReached + ", canScroll = " + ret);

        return ret;
    }

}
