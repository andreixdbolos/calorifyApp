package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class CircularProgressView extends View {

    private int progress = 0;
    private Paint paint = new Paint();
    private ValueAnimator animator;

    public CircularProgressView(Context context) {
        super(context);
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height);
        int strokeWidth = 20;
        int centerX = width / 2;
        int centerY = height / 2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.LTGRAY);
        canvas.drawCircle(centerX, centerY, diameter / 2 - strokeWidth / 2, paint);

        paint.setColor(Color.RED);
        RectF oval = new RectF(centerX - diameter / 2 + strokeWidth / 2, centerY - diameter / 2 + strokeWidth / 2,
                centerX + diameter / 2 - strokeWidth / 2, centerY + diameter / 2 - strokeWidth / 2);
        canvas.drawArc(oval, -90, 360 * progress / 100, false, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setProgressWithAnimation(int progress) {
        animator = ValueAnimator.ofInt(0, progress);
        animator.setDuration(3000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircularProgressView.this.progress = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }
}
