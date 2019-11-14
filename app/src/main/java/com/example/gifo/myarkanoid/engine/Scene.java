package com.example.gifo.myarkanoid.engine;

import com.example.gifo.myarkanoid.engine.object.PhysicalObject;
import com.example.gifo.myarkanoid.engine.object.type.Ball;
import com.example.gifo.myarkanoid.engine.object.type.Brick;
import com.example.gifo.myarkanoid.engine.object.type.Racket;
import com.example.gifo.myarkanoid.engine.object.type.Wall;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by gifo on 13.11.2019.
 */

public class Scene {

    private int score = 0, target = 0;
    public int ballBorderHide = 1900;
    public boolean isWin, isLose;

    private ArrayList<PhysicalObject> objects = new ArrayList<>();
    private ArrayList<Racket> rackets = new ArrayList<>();
    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    // Добавляет ракетку на игровую сцену
    public void addRacket(Racket o) {
        objects.add(o);
        rackets.add(o);
    }

    // Добавляет кирпичики на игровую сцену
    public void addBrick(Brick o) {
        objects.add(o);
        bricks.add(o);
        target++;
    }

    // Добавляет мячи на игровую сцену
    public void addBall(Ball o) {
        objects.add(o);
        balls.add(o);
    }

    // Добавляет стены на игровую сцену
    public void addWall(Wall o) {
        objects.add(o);
        walls.add(o);
    }

    // Возвращает объекты игровой сцены
    public ArrayList<PhysicalObject> getScene() {
        return objects;
    }

    // Удаляет объекты сцены по ссылке
    public void removeObject(PhysicalObject obj) {
        objects.remove(obj);
    }

    // Полная очистка игровой сцены
    public void clean() {
        objects.clear();
    }

    // Исполнение игрового процесса на сцене
    protected void execute() {

        // Если ракетка или мяч при попытке запуска игры не были инициализированы - выдаём ошибку
        if ((rackets.size() == 0) || (balls.size() == 0 && score == 0))
            throw new RuntimeException("Objects (Ball or Racket) are missing!");
        else {
            if (balls.size() != 0) {
                boolean ballCollision = false;

                // Проверка коллизий мячей с объектами сцены
                for (Ball ball : balls) {

                    // Делаем обход по столкновениям с ракетками
                    for (Racket racket : rackets)
                        if (ball.collision(racket)) ballCollision = true;

                    // Делаем обход по столкновениям со стенами
                    for (Wall wall : walls)
                        if (ball.collision(wall)) ballCollision = true;

                    // Делаем обход по столкновениям с кирпичиками
                    Iterator<Brick> it = bricks.iterator();
                    while (it.hasNext()) {
                        Brick brick = it.next();
                        if (ball.collision(brick)) {

                            // Плюсуем очки и удаляем кирпичики со сцены, если было попадание
                            ballCollision = true;
                            removeObject(brick);
                            it.remove();
                            score++;
                        }
                    }
                }

                // Пересчёт координат мяча
                if (!ballCollision) {
                    for (Ball ball : balls)
                        ball.rePosition();
                }

                // Удаляем мячики со сцены, которые упали ниже допустимой границы
                Iterator<Ball> it = balls.iterator();
                while (it.hasNext()) {
                    Ball o = it.next();
                    if (o.getPositionY() > ballBorderHide) {
                        removeObject(o);
                        it.remove();
                    }
                }

                // Вы выиграли
                if (score == target) isWin = true;
            } else {

                // Вы проиграли
                isLose = true;
            }
        }
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }
}
