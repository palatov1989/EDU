package com.test.weather;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

public class AvatarBehavior extends CoordinatorLayout.Behavior<CircleImageView> {


    public AvatarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency)
    {
        modifyAvatarDependingDependencyState(child, dependency); return true;
    }


    public boolean modifyAvatarDependingDependencyState(CircleImageView child, View dependency) {
        child.setRotation(dependency.getBottom());
        return true;
    }
}
