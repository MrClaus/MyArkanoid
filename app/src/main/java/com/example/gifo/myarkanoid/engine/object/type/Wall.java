package com.example.gifo.myarkanoid.engine.object.type;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.gifo.myarkanoid.engine.object.Drawable;
import com.example.gifo.myarkanoid.engine.object.PhysicalObject;

/**
 * Created by gifo on 13.11.2019.
 */

public class Wall extends PhysicalObject implements Drawable {

    private Paint paint = new Paint();
    private Rect rect = new Rect();

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        rect.set(x, y, x + width, y + height);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
    }
}
