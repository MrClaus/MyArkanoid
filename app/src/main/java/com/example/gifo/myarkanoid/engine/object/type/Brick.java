package com.example.gifo.myarkanoid.engine.object.type;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.gifo.myarkanoid.engine.object.Drawable;
import com.example.gifo.myarkanoid.engine.object.PhysicalObject;

/**
 * Created by gifo on 13.11.2019.
 */

public class Brick extends PhysicalObject implements Drawable {

    private Paint paint = new Paint();
    private RectF rect = new RectF();

    public Brick(int x, int y, int width, int height) {
        super(x, y, width, height);
        rect.set(x, y, x + width, y + height);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect, 4, 4, paint);
    }
}
