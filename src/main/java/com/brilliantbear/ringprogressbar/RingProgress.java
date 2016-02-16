package com.brilliantbear.ringprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Bear on 2016/2/16.
 */
public class RingProgress extends View {
    private static final String TAG = "RINGPROGRESS";

    private final int DEFAULT_FINISHED_COLOR = Color.RED;
    private final int DEFAULT_UNFINISHED_COLOR = Color.GRAY;
    private final int DEFAULT_FINISHED_STROKE_WIDTH = 10;
    private final int DEFAULT_UNFINISHED_STROKE_WIDTH = 10;
    private final int DEFAULT_INNER_TEXT_COLOR = Color.BLACK;
    private final int DEFAULT_INNER_TEXT_SIZE = 18;
    private final int DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT;
    private final int DEFAULT_MAX = 100;
    private final int DEFAULT_STARTING_DEGREE = 0;
    private final int DEFAULT_MIN_SIZE = 100;

    public int getFinishedColor() {
        return finishedColor;
    }

    public void setFinishedColor(int finishedColor) {
        this.finishedColor = finishedColor;
        invalidate();
    }

    public int getUnfinishedColor() {
        return unfinishedColor;
    }

    public void setUnfinishedColor(int unfinishedColor) {
        this.unfinishedColor = unfinishedColor;
        invalidate();
    }

    public int getInnerTextColor() {
        return innerTextColor;
    }

    public void setInnerTextColor(int innerTextColor) {
        this.innerTextColor = innerTextColor;
        invalidate();
    }

    public float getInnerTextSize() {
        return innerTextSize;
    }

    public void setInnerTextSize(float innerTextSize) {
        this.innerTextSize = innerTextSize;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress > getMax() ? getMax() : progress;
        invalidate();
    }

    public float getFinishedStrokeWidth() {
        return finishedStrokeWidth;
    }

    public void setFinishedStrokeWidth(float finishedStrokeWidth) {
        this.finishedStrokeWidth = finishedStrokeWidth;
        invalidate();
    }

    public float getUnfinishedStrokeWidth() {
        return unfinishedStrokeWidth;
    }

    public void setUnfinishedStrokeWidth(float unfinishedStrokeWidth) {
        this.unfinishedStrokeWidth = unfinishedStrokeWidth;
        invalidate();
    }

    public String getInnerText() {
        return innerText;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
        invalidate();
    }

    public int getStartingDegree() {
        return startingDegree;
    }

    public void setStartingDegree(int startingDegree) {
        this.startingDegree = startingDegree;
        invalidate();
    }


    public int getInnerBackgroundColor() {
        return innerBackgroundColor;
    }

    public void setInnerBackgroundColor(int innerBackgroundColor) {
        this.innerBackgroundColor = innerBackgroundColor;
        invalidate();
    }


    private int finishedColor;
    private int unfinishedColor;
    private int innerTextColor;
    private float innerTextSize;
    private int max;
    private int progress;
    private float finishedStrokeWidth;
    private float unfinishedStrokeWidth;
    private String innerText;
    private int startingDegree;
    private TextPaint innerTextPaint;
    private Paint finishedPaint;
    private Paint unfinishedPaint;
    private Paint innerCirclePaint;
    private int innerBackgroundColor;

    private RectF finishedOuterRect = new RectF();
    private RectF unfinishedOuterRect = new RectF();

    public RingProgress(Context context) {
        this(context, null);
    }

    public RingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.RingProgress, defStyleAttr, 0);
        initAttributes(attributes);
        attributes.recycle();

        initPainters();
    }

    private void initPainters() {
        innerTextPaint = new TextPaint();
        innerTextPaint.setColor(innerTextColor);
        innerTextPaint.setTextSize(innerTextSize);
        innerTextPaint.setAntiAlias(true);

        finishedPaint = new Paint();
        finishedPaint.setColor(finishedColor);
        finishedPaint.setStyle(Paint.Style.STROKE);
        finishedPaint.setStrokeWidth(finishedStrokeWidth);
        finishedPaint.setAntiAlias(true);

        unfinishedPaint = new Paint();
        unfinishedPaint.setColor(unfinishedColor);
        unfinishedPaint.setStyle(Paint.Style.STROKE);
        unfinishedPaint.setStrokeWidth(unfinishedStrokeWidth);
        unfinishedPaint.setAntiAlias(true);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(innerBackgroundColor);
        innerCirclePaint.setAntiAlias(true);
    }

    private void initAttributes(TypedArray attributes) {
        finishedColor = attributes.getColor(R.styleable.RingProgress_finished_color, DEFAULT_FINISHED_COLOR);
        unfinishedColor = attributes.getColor(R.styleable.RingProgress_unfinished_color, DEFAULT_UNFINISHED_COLOR);
        innerTextColor = attributes.getColor(R.styleable.RingProgress_inner_text_color, DEFAULT_INNER_TEXT_COLOR);
        innerTextSize = attributes.getDimension(R.styleable.RingProgress_inner_text_size, DimensionUtils.sp2px
                (getResources(), DEFAULT_INNER_TEXT_SIZE));
        max = attributes.getInt(R.styleable.RingProgress_ring_max, DEFAULT_MAX);
        progress = attributes.getInt(R.styleable.RingProgress_ring_progress, 0);
        finishedStrokeWidth = attributes.getDimension(R.styleable.RingProgress_finished_stroke_width,
                DimensionUtils.dp2px(getResources(), DEFAULT_FINISHED_STROKE_WIDTH));
        unfinishedStrokeWidth = attributes.getDimension(R.styleable.RingProgress_unfinished_stroke_width,
                DimensionUtils.dp2px(getResources(), DEFAULT_UNFINISHED_STROKE_WIDTH));
        innerText = attributes.getString(R.styleable.RingProgress_inner_text);
        startingDegree = attributes.getInt(R.styleable.RingProgress_starting_degree, 0);
        innerBackgroundColor = attributes.getColor(R.styleable.RingProgress_background_color, DEFAULT_BACKGROUND_COLOR);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);

        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = (int) DimensionUtils.dp2px(getResources(), DEFAULT_MIN_SIZE);
            if (mode == View.MeasureSpec.AT_MOST) {
                result = Math.min(size, result);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth);

        finishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);
        unfinishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);

//        float innerCircleRadius = (getWidth() - Math.min(finishedStrokeWidth, unfinishedStrokeWidth) + Math.abs
//                (finishedStrokeWidth - unfinishedStrokeWidth)) / 2f;
        float innerCircleRadius = (getWidth() - delta) / 2;

//        Log.d(TAG, "width:" + getWidth());
//        Log.d(TAG, "height:" + getHeight());
//        Log.d(TAG, "R:" + innerCircleRadius);
//        Log.d(TAG, "finishedStrokeWidth:" + finishedStrokeWidth);
//        Log.d(TAG, "unfinishedStrokeWidth:" + unfinishedStrokeWidth);

        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, innerCircleRadius, innerCirclePaint);
        canvas.drawArc(finishedOuterRect, getStartingDegree(), getProgressAngle(), false, finishedPaint);
        canvas.drawArc(unfinishedOuterRect, getStartingDegree() + getProgressAngle(), 360 - getProgressAngle(),
                false, unfinishedPaint);

        String innerText = getInnerText();
        if (!TextUtils.isEmpty(innerText)) {
            float innerTextHeight = innerTextPaint.descent() + innerTextPaint.ascent();
            canvas.drawText(innerText, (getWidth() - innerTextPaint.measureText(innerText)) / 2, (getHeight() -
                    innerTextHeight) / 2, innerTextPaint);
        }
    }

    private float getProgressAngle() {
        return getProgress() / (float) getMax() * 360f;
    }
}
