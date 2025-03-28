package com.example.asteroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.asteroid.model.WindData;

import java.util.List;

public class WindRoseView extends View {

    private Paint circlePaint;
    private Paint linePaint;
    private Paint trianglePaint;

    private float centerX;
    private float centerY;
    private float radius;

    private List<WindData> windDataList;

    public WindRoseView(Context context) {
        super(context);
        init();
    }

    public WindRoseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#CCCCCC"));
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(3f);
        linePaint.setAntiAlias(true);
        trianglePaint = new Paint();
        trianglePaint.setColor(Color.parseColor("#66B2FF"));
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setAntiAlias(true);
    }

    public void setWindDataList(List<WindData> windDataList) {
        this.windDataList = windDataList;
        invalidate(); // Redibuja la vista
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;
        radius = Math.min(getWidth(), getHeight()) / 2f;

        drawCircle(canvas);
        drawCrossLines(canvas);
        drawWindTriangles(canvas);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius, circlePaint);
    }

    private void drawCrossLines(Canvas canvas) {
        canvas.drawLine(centerX - radius, centerY, centerX + radius, centerY, linePaint); // Horizontal
        canvas.drawLine(centerX, centerY - radius, centerX, centerY + radius, linePaint); // Vertical
    }

    private void drawWindTriangles(Canvas canvas) {
        if (windDataList == null || windDataList.isEmpty()) return;

        int maxCt = 1;
        for (WindData wd : windDataList) {
            if (wd.getCount() > maxCt) maxCt = wd.getCount();
        }

        float base = radius / 15f;

        for (WindData wd : windDataList) {
            float angle = wd.getCompassDegrees();
            float length = (wd.getCount() / (float) maxCt) * radius;

            canvas.save();
            canvas.rotate(angle, centerX, centerY);

            Path triangle = new Path();
            triangle.moveTo(centerX, centerY); // punta en el centro
            triangle.lineTo(centerX - base, centerY - length); // lado izquierdo
            triangle.lineTo(centerX + base, centerY - length); // lado derecho
            triangle.close();

            canvas.drawPath(triangle, trianglePaint);
            canvas.restore();
        }
    }
}
