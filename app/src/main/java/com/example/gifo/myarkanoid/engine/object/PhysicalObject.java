package com.example.gifo.myarkanoid.engine.object;

/**
 * Created by gifo on 13.11.2019.
 */

public class PhysicalObject {

    protected float x, y, width, height;
    protected double velocity, velocityAngle;
    protected boolean isLeft, isRight, isTop, isBottom;

    public PhysicalObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Задаёт позицию объекта по осям Х и У
    public void setPosition(float x, float y) {
        setPositionX(x);
        setPositionY(y);
    }

    // Задаёт позицию по оси Х
    public void setPositionX(float x) {
        this.x = x;
    }

    // Задаёт позицию по оси У
    public void setPositionY(float y) {
        this.y = y;
    }

    // Возвращает позицию по оси Х
    public float getPositionX() {
        return x;
    }

    // Возвращает позицию по оси У
    public float getPositionY() {
        return y;
    }

    // Возвращает физическую ширину объекта
    public float getWidth() {
        return width;
    }

    // Возвращает физическую высоту объекта
    public float getHeight() {
        return height;
    }

    // Позволяет переопредять и задавать уникальное поведение объекта при коллизии
    public boolean collision(PhysicalObject obj) {
        boolean xCollision = false, yCollision = false;
        if ((x + width >= obj.x) && (x <= obj.x + obj.width)) xCollision = true;
        if ((y + height >= obj.y) && (y <= obj.y + obj.height)) yCollision = true;
        if (xCollision && yCollision) return true;
        else return false;
    }

    // Задаёт движение объекту
    public void run(double velocity, double velocityAngle) {
        this.velocity = velocity;
        this.velocityAngle = velocityAngle;
    }
}
