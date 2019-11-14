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

public class Racket extends PhysicalObject implements Drawable {

    private Paint paint = new Paint();
    private RectF rect = new RectF();

    public Racket(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        rect.set(x, y, x + width, y + height);
        canvas.drawRoundRect(rect, height/2, height/2, paint);
    }
}
