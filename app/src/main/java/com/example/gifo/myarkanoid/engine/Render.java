package com.example.gifo.myarkanoid.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gifo.myarkanoid.engine.object.Drawable;
import com.example.gifo.myarkanoid.engine.object.PhysicalObject;

/**
 * Created by gifo on 13.11.2019.
 */

public class Render {

    private Scene scene;
    private Paint paint = new Paint();

    public Render(Scene scene) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        this.scene = scene;
    }

    // Задаёт цвет очистки экрана
    public void clearColor(int color) {
        paint.setColor(color);
    }

    // Отрисовка игровой сцены
    public void render(Canvas canvas) {
        if (!scene.isWin && !scene.isLose) {
            scene.execute();
            draw(canvas);
        }
    }

    private void draw(Canvas canvas) {
        canvas.drawPaint(paint);
        for (PhysicalObject object : scene.getScene()) {
            ((Drawable) object).draw(canvas);
        }
    }
}
