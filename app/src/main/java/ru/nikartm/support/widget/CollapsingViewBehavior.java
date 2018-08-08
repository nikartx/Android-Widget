package ru.nikartm.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import ru.nikartm.support.R;

/**
 * CollapsingViewBehavior to expand or collapse view {@link View}
 * Collapsed view must be tied to the specified place. For example to {@link android.widget.Space}
 * or {@link View} in Toolbar {@link android.support.v7.widget.Toolbar}
 *
 * @author Ivan V on 08.08.2018.
 * @version 1.0
 */
public class CollapsingViewBehavior extends CoordinatorLayout.Behavior<View> {

    private int[] view;
    private int[] targetView;

    private final static int X = 0;
    private final static int Y = 1;
    private final static int WIDTH = 2;
    private final static int HEIGHT = 3;

    private int targetPlaceId;

    public CollapsingViewBehavior() {}

    public CollapsingViewBehavior(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CollapsingViewBehavior);
            targetPlaceId = a.getResourceId(R.styleable.CollapsingViewBehavior_target_place, 0);
            a.recycle();
        }

        if (targetPlaceId == 0) {
            throw new IllegalStateException("target_place attribute must be specified on view for CollapsingViewBehavior");
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        initTargetView(parent);
        initBehaviorView(child);

        AppBarLayout appBar = (AppBarLayout) dependency;
        int range = appBar.getTotalScrollRange();
        float factor = -appBar.getY() / range;

        int left = (int) (factor * (targetView[X]- view[X] - targetView[WIDTH])) - (int) (factor * view[Y] / 2);
        int top = (int) (factor * (targetView[Y] - view[Y]));
        int width = view[WIDTH] + (int) (factor * (targetView[WIDTH] - view[WIDTH]));
        int height = view[HEIGHT] + (int) (factor * (targetView[HEIGHT] - view[HEIGHT]));
        animateView(child, left, top, width, height);
        return true;
    }

    private void animateView(View child, int left, int top, int width, int height) {
        child.getLayoutParams().width = width;
        child.getLayoutParams().height = height;
        child.requestLayout();
        child.setTranslationX(left);
        child.setTranslationY(top);
    }

    private void initBehaviorView(View child) {
        if (view != null) { return; }
        view = new int[4];

        view[X] = (int) child.getX();
        view[Y] = (int) child.getY();
        view[WIDTH] = child.getWidth();
        view[HEIGHT] = child.getHeight();
    }

    private void initTargetView(CoordinatorLayout parent) {
        if (targetView != null) { return; }
        targetView = new int[4];

        View target = parent.findViewById(targetPlaceId);
        if (target == null) {
            throw new IllegalStateException("target view not found");
        }
        targetView[WIDTH] = target.getWidth();
        targetView[HEIGHT] = target.getHeight();
        targetView[X] = (int) target.getX();
        targetView[Y] = (int) target.getY();
    }

}
