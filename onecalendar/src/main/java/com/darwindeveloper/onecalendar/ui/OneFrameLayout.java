package com.darwindeveloper.onecalendar.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by DARWIN on 4/3/2017.
 */

public class OneFrameLayout extends FrameLayout {

    public OneFrameLayout(Context context) {
        super(context);
    }

    public OneFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private static final int mWidth = 500;
    private float mDisplacementX;
    // private float mLastMoveX;
    private float mDisplacementY;
    private float mInitialTx;
    private boolean mTracking;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                mDisplacementX = event.getRawX();
                mDisplacementY = event.getRawY();

                mInitialTx = getTranslationX();

                break;

            case MotionEvent.ACTION_MOVE:
                // get the delta distance in X and Y direction
                float deltaX = event.getRawX() - mDisplacementX;
                float deltaY = event.getRawY() - mDisplacementY;
                // updatePressedState(false);

                // set the touch and cancel event
                if ((Math.abs(deltaX) > ViewConfiguration.get(getContext())
                        .getScaledTouchSlop() * 2 && Math.abs(deltaY) < Math
                        .abs(deltaX) / 2)
                        || mTracking) {

                    mTracking = true;

                    if (getTranslationX() <= mWidth / 2
                            && getTranslationX() >= -(mWidth / 2)) {

                        setTranslationX(mInitialTx + deltaX);
                        break;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:

                if (mTracking) {
                    mTracking = false;
                    float currentTranslateX = getTranslationX();

                    if (currentTranslateX > mWidth / 4) {
                        onSwipeListener.rightSwipe();
                    } else if (currentTranslateX < -(mWidth / 4)) {
                        onSwipeListener.leftSwipe();
                    }

                    // comment this line if you don't want your frame layout to
                    // take its original position after releasing the touch
                    setTranslationX(0);
                    break;
                } else {
                    // handle click event
                    setTranslationX(0);
                }
                break;
        }
        return false;
    }


    public interface OnSwipeListener {
        void rightSwipe();

        void leftSwipe();
    }


    private OnSwipeListener onSwipeListener;

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }
}
