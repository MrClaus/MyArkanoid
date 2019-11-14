package com.example.gifo.myarkanoid.engine.object.type;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.gifo.myarkanoid.engine.object.Drawable;
import com.example.gifo.myarkanoid.engine.object.PhysicalObject;

/**
 * Created by gifo on 13.11.2019.
 */

public class Ball extends PhysicalObject implements Drawable {

    private Paint paint = new Paint();

    public Ball(int x, int y, int radius) {
        super(x - radius, y - radius, 2*radius, 2*radius);
    }

    @Override
    public boolean collision(PhysicalObject obj) {
        if (!super.collision(obj)) return false;
        else {

            // Описываем поведение мяча при столкновениях
            velocityAngle = velocityAngle + Math.PI;
            rePosition(velocityAngle);
            velocityAngle = Math.toDegrees(velocityAngle- Math.PI);
            if (velocityAngle<0) velocityAngle += 360;
            collisionEdge(obj);

            if (isRight || isLeft) {
                velocityAngle = 360 - velocityAngle;
                velocityAngle = Math.toRadians(velocityAngle);
            }
            else if (isTop || isBottom) {
                if (isBottom && (obj instanceof Racket)) {

                    // Описываем особое поведени при столкновении с ракеткой
                    float k = (x + width/2 - obj.getPositionX())/(obj.getWidth());
                    k = (k <= 0.5) ? -1 + 2*k : 2*(k - 0.5f);
                    velocityAngle = (k>=0) ? Math.toRadians(k*70) : Math.toRadians(360 + k*70);

                } else {
                    if (velocityAngle > 180) velocityAngle = 540 - velocityAngle;
                    else velocityAngle = 180 - velocityAngle;
                    velocityAngle = Math.toRadians(velocityAngle);
                }
            }
            rePosition(velocityAngle);
            return true;
        }
    }

    /*
     * Вспомогательные функции пересчета позиции Мяча:
     * - rePosition() - пересчёт позиции мяча с учетом угла движения
     * - collisionEdge() - определение стороны столкновения мяча с объектами
    */
    public void rePosition() {
        rePosition(velocityAngle);
    }

    public void rePosition(double angle) {
        x = (float) (x + velocity * Math.sin(angle));
        y = (float) (y - velocity * Math.cos(angle));
    }

    private void collisionEdge(PhysicalObject obj) {
        isBottom = isTop = isRight = isLeft = false;
        if (y + height <= obj.getPositionY()) isBottom = true;
        if (y >= obj.getPositionY() + obj.getHeight()) isTop = true;
        if (x + width <= obj.getPositionX()) isRight = true;
        if (x >= obj.getPositionX() + obj.getWidth()) isLeft = true;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x + width/2, y + height/2, width/2, paint);
    }
}
