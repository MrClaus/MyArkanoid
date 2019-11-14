package com.example.gifo.myarkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.gifo.myarkanoid.engine.Render;
import com.example.gifo.myarkanoid.engine.Scene;
import com.example.gifo.myarkanoid.engine.object.type.Ball;
import com.example.gifo.myarkanoid.engine.object.type.Brick;
import com.example.gifo.myarkanoid.engine.object.type.Racket;
import com.example.gifo.myarkanoid.engine.object.type.Wall;

/**
 * Created by gifo on 13.11.2019.
 */

public class GameLayer extends View {

    private Paint paint;
    private int touchX, touchY;
    private boolean isStart = false;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            touchX = (int) event.getRawX();
            touchY = (int) event.getRawY();
            System.out.println(touchX + " / " + touchY);
            return true;
        }
    };

    public GameLayer(Context context) {
        super(context);
        setOnTouchListener(touchListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getWidth()!=0 && getHeight()!=0) {
            init();
        }
    }

    // Глобальные объекты игры
    Scene gameScene;    // игровая сцена
    Render render;      // рендер
    Racket racket;      // ракетка

    private void init() {
        isStart = true;

        // Для вывода текста на экран
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(100);

        gameScene = new Scene();

        Ball ball = new Ball(540, 1600, 16);
        gameScene.ballBorderHide = getHeight();
        gameScene.addBall(ball);
        ball.run(4, 0.25);

        racket = new Racket(50, 1800, 300, 32);
        gameScene.addRacket(racket);

        Wall wall1 = new Wall(0, 0, 30, (int) (getHeight()*0.95));
        Wall wall2 = new Wall(getWidth()-30, 0, 30, (int) (getHeight()*0.95));
        Wall wall3 = new Wall(30, 0, getWidth()/3 - 30, 30);
        Wall wall4 = new Wall(2*getWidth()/3, 0, getWidth()/3 - 30, 30);
        Wall wall5 = new Wall(getWidth()/3, 0, 30, 200);
        Wall wall6 = new Wall(2*getWidth()/3 - 30, 0, 30, 200);
        Wall wall7 = new Wall(getWidth()/3 + 30, 170, getWidth()/3 - 60, 30);
        gameScene.addWall(wall1);
        gameScene.addWall(wall2);
        gameScene.addWall(wall3);
        gameScene.addWall(wall4);
        gameScene.addWall(wall5);
        gameScene.addWall(wall6);
        gameScene.addWall(wall7);

        int nPlace = 10; // количество столбцов кирппичиков
        int mPlace = 11; // количество строк кирпичеков

        for (int i=0; i<mPlace; i++) {
            for (int j=0; j< nPlace; j++) {
                int x = 100 + j*(getWidth()-200)/nPlace;
                int y = (int) (getHeight()*0.2f + i*(getWidth()-200)/(2*nPlace));
                int width = (getWidth()-200)/nPlace - 20;
                int height = (getWidth()-200)/(2*nPlace) - 20;
                Brick brick = new Brick(x, y, width, height);
                gameScene.addBrick(brick);
            }
        }

        render = new Render(gameScene);
    }

    // Цикличная прорисовка сцены
    @Override
    protected void onDraw(Canvas canvas) {
        if (isStart) {
            racket.setPositionX(touchX - racket.getWidth() / 2);
            render.render(canvas);
            canvas.drawText(gameScene.toString(), getWidth()/2 - 24, 120, paint);
            if (gameScene.isLose || gameScene.isWin) System.exit(0);
        }
        invalidate();
    }
}
