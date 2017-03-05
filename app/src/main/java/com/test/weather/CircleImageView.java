package com.test.weather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by virus on 02.03.2017.
 */

public class CircleImageView extends ImageView
{
    public CircleImageView(Context context){
        super(context);
    }
    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //создаем круг
        final float halfWidth = canvas.getWidth()/2;
        final float halfHeight = canvas.getHeight()/2;
        final float radius = Math.max(halfWidth, halfHeight);
        final Path path = new Path();
        path.addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW);

        //обрезаем
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

}