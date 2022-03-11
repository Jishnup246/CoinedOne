package com.example.coinedone;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Color;
import android.view.animation.DecelerateInterpolator;

public class CircularProgressBar extends View {

    private int mViewWidth;
    private int mViewHeight;

    private final float mStartAngle = 180;      // Always start from top (default is: "3 o'clock on a watch.")
    private float mSweepAngle = 0;              // How long to sweep from mStartAngle

    private int studyTimePercent=0;
    private int classTimePercent=0;
    private int freeTimePercent=0;

    private float classTimeSweepAngle = 0;
    private float studyTimeSweepAngle = 0;
    private float freeTimeSweepAngle = 0;



    private float mMaxSweepAngle = 360;         // Max degrees to sweep
    private int mStrokeWidth = 40;              // Width of outline
    private int mAnimationDuration = 1000;       // Animation duration for progress change
    private int mMaxProgress = 100;             // Max progress to use
    private boolean mDrawText = true;           // Set to true if progress text should be drawn
    private boolean mRoundedCorners = true;     // Set to true if rounded corners should be applied to outline ends
    private int mProgressColor = Color.BLACK;   // Outline color
    private int mTextColor = Color.BLACK;       // Progress text color

    private final Paint mPaint;                  // Allocate paint outside onDraw to avoid unnecessary object creation

    private final Paint classTimePaint;
    private final Paint studyTimePaint;
    private final Paint freeTimePaint;
    private  final Paint totalText_Paint;

    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


/* This defines the custom draw paint for circular progress */
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        classTimePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        studyTimePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        freeTimePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        totalText_Paint=new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurments();
        drawOutlineArc(canvas);


    }

    private void initMeasurments() {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
    }

    /* This fuction will draw circular progress bar */
    /* for each catagory time draw seperately */

    private void drawOutlineArc(Canvas canvas) {

        final int diameter = Math.min(mViewWidth, mViewHeight);
        final float pad = (float)(mStrokeWidth / 2.0);
        final RectF outerOval = new RectF(pad, pad, diameter - pad, diameter - pad);

        classTimePaint.setColor(Color.rgb(37, 150, 190));
        classTimePaint.setStrokeWidth(mStrokeWidth);
        classTimePaint.setAntiAlias(false);
        classTimePaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        classTimePaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(outerOval, mStartAngle, classTimeSweepAngle, false, classTimePaint);



        studyTimePaint.setColor(Color.rgb(255, 158, 87));
        studyTimePaint.setStrokeWidth(mStrokeWidth);
        studyTimePaint.setAntiAlias(false);
        studyTimePaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        studyTimePaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(outerOval, mStartAngle+classTimeSweepAngle, studyTimeSweepAngle, false, studyTimePaint);

        freeTimePaint.setColor(Color.rgb(97, 241, 123));
        freeTimePaint.setStrokeWidth(mStrokeWidth);
        freeTimePaint.setAntiAlias(false);
        freeTimePaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        freeTimePaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(outerOval, mStartAngle+classTimeSweepAngle+studyTimeSweepAngle, freeTimeSweepAngle, false, freeTimePaint);
    }


    private int calcProgressFromSweepAngle(float sweepAngle) {
        return (int) ((sweepAngle * mMaxProgress) / mMaxSweepAngle);
    }

    private float calcSweepAngleFromProgress(int progress) {
        return (mMaxSweepAngle / mMaxProgress) * progress;
    }




    public void setProgress() {
        ValueAnimator animator_class = ValueAnimator.ofFloat(classTimeSweepAngle, calcSweepAngleFromProgress(classTimePercent));
        animator_class.setInterpolator(new DecelerateInterpolator());
        animator_class.setDuration(mAnimationDuration);
        animator_class.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                classTimeSweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator_class.start();

        ValueAnimator animator_study = ValueAnimator.ofFloat(studyTimeSweepAngle, calcSweepAngleFromProgress(studyTimePercent));
        animator_study.setInterpolator(new DecelerateInterpolator());
        animator_study.setDuration(mAnimationDuration);
        animator_study.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                studyTimeSweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator_study.start();

        ValueAnimator animator_free = ValueAnimator.ofFloat(freeTimeSweepAngle, calcSweepAngleFromProgress(freeTimePercent));
        animator_free.setInterpolator(new DecelerateInterpolator());
        animator_free.setDuration(mAnimationDuration);
        animator_free.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                freeTimeSweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator_free.start();


    }


    /* Call this function to set values of each time progress */

    public void setProgressValue(int studytime,int classtime,int freetime)
    {
        float totaltime=studytime+classtime+freetime;
       if(totaltime==0)
        {
            studyTimePercent=0;
            classTimePercent=0;
            freeTimePercent=0;
        }
        else {
            studyTimePercent=(int) (studytime/totaltime*100);
            classTimePercent=(int) (classtime/totaltime*100);
            freeTimePercent=(int) (freetime/totaltime*100);
        }
        setProgress();


    }

}

