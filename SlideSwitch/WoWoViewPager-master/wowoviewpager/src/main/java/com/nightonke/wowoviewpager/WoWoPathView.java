package com.nightonke.wowoviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Weiping Huang at 18:38 on 2016/3/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 * WoWoPathView helps to create a path animation, including the image-head.
 */

public class WoWoPathView extends View {

    private boolean dashPath;
    private float dashSegmentLength;
    private float dashPaddingLength;
    private boolean dynamicalPath;
    private float dynamicalPathSpeed;
    private int pathColor;
    private float pathWidth;
    private int headImageRes;
    private float headImageWidth;
    private float headImageHeight;

    private Paint paint;
    private Path path;
    private Path partialPath;
    private float progress = 0f;
    private float pathLength = 0f;
    private PathMeasure pathMeasure;
    private float dynamicalPathPhase = 0f;
    private PathEffect pathEffect;

    private Bitmap bitmap;
    private int bitmapOffsetX, bitmapOffsetY;
    private float[] bitmapPosition;
    private float[] bitmapTan;
    private Matrix matrix;

    public WoWoPathView(Context context) {
        super(context);
        init(context, null);
    }

    public WoWoPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WoWoPathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initPaint();
        initImage();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WoWoPathView, 0, 0);
        if (typedArray == null) return;
        try {
            dashPath = typedArray.getBoolean(R.styleable.WoWoPathView_wowo_dashPath, context.getResources().getBoolean(R.bool.default_dashPath));
            dashSegmentLength = typedArray.getDimension(R.styleable.WoWoPathView_wowo_dashPathSegmentLength, context.getResources().getDimension(R.dimen.default_dashPathSegmentLength));
            dashPaddingLength = typedArray.getDimension(R.styleable.WoWoPathView_wowo_dashPathPaddingLength, context.getResources().getDimension(R.dimen.default_dashPathPaddingLength));
            dynamicalPath = typedArray.getBoolean(R.styleable.WoWoPathView_wowo_dynamicPath, context.getResources().getBoolean(R.bool.default_dynamicPath));
            dynamicalPathSpeed = typedArray.getDimension(R.styleable.WoWoPathView_wowo_dynamicPathSpeed, context.getResources().getDimension(R.dimen.default_dynamicPathSpeed));
            pathWidth = typedArray.getDimension(R.styleable.WoWoPathView_wowo_pathWidth, context.getResources().getDimension(R.dimen.default_pathWidth));
            pathColor = typedArray.getColor(R.styleable.WoWoPathView_wowo_pathColor, ContextCompat.getColor(context, R.color.default_pathColor));
            headImageRes = typedArray.getResourceId(R.styleable.WoWoPathView_wowo_headImageSrc, context.getResources().getInteger(R.integer.default_headImageSrc));
            headImageWidth = typedArray.getDimension(R.styleable.WoWoPathView_wowo_headImageWidth, context.getResources().getDimension(R.dimen.default_headImageWidth));
            headImageHeight = typedArray.getDimension(R.styleable.WoWoPathView_wowo_headImageHeight, context.getResources().getDimension(R.dimen.default_headImageHeight));
        } finally {
            typedArray.recycle();
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(pathColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(pathWidth);
        paint.setAntiAlias(true);

        setPath(new Path());

        if (dashPath) {
            pathEffect = new DashPathEffect(new float[]{dashSegmentLength, dashPaddingLength}, dynamicalPathPhase);
            paint.setPathEffect(pathEffect);
        }
    }

    private void initImage() {
        if (headImageRes != 0) {
            bitmap = BitmapFactory.decodeResource(getResources(), headImageRes);
            if (headImageWidth != 0 || headImageHeight != 0) bitmap = getResizedBitmap(bitmap, headImageWidth, headImageHeight);
            bitmapOffsetX = bitmap.getWidth() / 2;
            bitmapOffsetY = bitmap.getHeight() / 2;
            bitmapPosition = new float[2];
            bitmapTan = new float[2];
            matrix = new Matrix();
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private Bitmap getResizedBitmap(Bitmap bm, float newWidth, float newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = 1, scaleHeight = 1;
        if (newWidth != 0) {
            scaleWidth = newWidth / width;
            if (newHeight != 0) scaleHeight = newHeight / height;
            else scaleHeight = scaleWidth;
        } else {
            if (newHeight != 0) scaleWidth = scaleHeight = newHeight / height;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Help wanted.
            // In android version lower than lollipop,
            // we cannot draw the path by this way.
            // And as a result, dash-line cannot be achieved.
            canvas.save();
            partialPath.reset();
            pathMeasure.getSegment(0.0f, pathLength * progress, partialPath, true);
            canvas.drawPath(partialPath, paint);

            if (dynamicalPath) {
                pathEffect = new DashPathEffect(new float[]{dashSegmentLength, dashPaddingLength}, dynamicalPathPhase);
                dynamicalPathPhase += dynamicalPathSpeed;
                paint.setPathEffect(pathEffect);
            }
        } else {
            PathEffect pathEffect = new DashPathEffect(new float[]{pathLength, pathLength}, (pathLength - pathLength * progress));
            paint.setPathEffect(pathEffect);

            canvas.save();
            canvas.translate(getPaddingLeft(), getPaddingTop());
            canvas.drawPath(path, paint);
        }

        if (headImageRes != 0) {
            pathMeasure.getPosTan(pathLength * progress, bitmapPosition, bitmapTan);
            matrix.reset();
            float degrees = (float) (Math.atan2(bitmapTan[1], bitmapTan[0]) * 180.0 / Math.PI);
            matrix.postRotate(degrees, bitmapOffsetX, bitmapOffsetY);
            matrix.postTranslate(bitmapPosition[0] - bitmapOffsetX, bitmapPosition[1] - bitmapOffsetY);
            canvas.drawBitmap(bitmap, matrix, null);
        }

        canvas.restore();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && dynamicalPath) invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);

        int measuredWidth, measuredHeight;

        if(widthMode == MeasureSpec.AT_MOST)
            throw new IllegalStateException("WoWoPathView cannot have a WRAP_CONTENT property");
        else
            measuredWidth = widthSize;

        if(heightMode == MeasureSpec.AT_MOST)
            throw new IllegalStateException("WoWoPathView cannot have a WRAP_CONTENT property");
        else
            measuredHeight = heightSize;

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    public void setProcess(float process) {
        this.progress = process;
        // Check method onDraw to find out why
        if (!dynamicalPath || Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) invalidate();
    }

    /**
     * Set the path.
     *
     * @param path The new path
     */
    public void setPath(Path path) {
        this.path = path;
        partialPath = new Path();
        pathMeasure = new PathMeasure(this.path, false);
        pathLength = pathMeasure.getLength();
    }

    /**
     * Get the path.
     *
     * @return The path
     */
    public Path getPath() {
        return path;
    }

    /**
     * Create an empty path
     *
     * @return WoWoPathView itself
     */
    public WoWoPathView newPath() {
        setPath(new Path());
        return this;
    }

    /**
     * Set the beginning of the next contour to the point (x,y).
     *
     * @param x The x-coordinate of the start of a new contour
     * @param y The y-coordinate of the start of a new contour
     * @return WoWoPathView itself
     */
    public WoWoPathView pathMoveTo(float x, float y) {
        path.moveTo(x, y);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Set the beginning of the next contour to the point (x,y).
     *
     * @param x The x-coordinate of the start of a new contour
     * @param y The y-coordinate of the start of a new contour
     * @return WoWoPathView itself
     */
    public WoWoPathView pathMoveTo(double x, double y) {
        path.moveTo((float) x, (float) y);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a line from the last point to the specified point (x,y).
     * If no moveTo() call has been made for this contour, the first point is
     * automatically set to (0,0).
     *
     * @param x The x-coordinate of the end of a line
     * @param y The y-coordinate of the end of a line
     * @return WoWoPathView itself
     */
    public WoWoPathView pathLineTo(float x, float y) {
        path.lineTo(x, y);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a line from the last point to the specified point (x,y).
     * If no moveTo() call has been made for this contour, the first point is
     * automatically set to (0,0).
     *
     * @param x The x-coordinate of the end of a line
     * @param y The y-coordinate of the end of a line
     * @return WoWoPathView itself
     */
    public WoWoPathView pathLineTo(double x, double y) {
        path.lineTo((float) x, (float) y);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a quadratic bezier from the last point, approaching control point
     * (x1,y1), and ending at (x2,y2). If no moveTo() call has been made for
     * this contour, the first point is automatically set to (0,0).
     *
     * @param x1 The x-coordinate of the control point on a quadratic curve
     * @param y1 The y-coordinate of the control point on a quadratic curve
     * @param x2 The x-coordinate of the end point on a quadratic curve
     * @param y2 The y-coordinate of the end point on a quadratic curve
     * @return WoWoPathView itself
     */
    public WoWoPathView pathQuadTo(float x1, float y1, float x2, float y2) {
        path.quadTo(x1, y1, x2, y2);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a quadratic bezier from the last point, approaching control point
     * (x1,y1), and ending at (x2,y2). If no moveTo() call has been made for
     * this contour, the first point is automatically set to (0,0).
     *
     * @param x1 The x-coordinate of the control point on a quadratic curve
     * @param y1 The y-coordinate of the control point on a quadratic curve
     * @param x2 The x-coordinate of the end point on a quadratic curve
     * @param y2 The y-coordinate of the end point on a quadratic curve
     * @return WoWoPathView itself
     */
    public WoWoPathView pathQuadTo(double x1, double y1, double x2, double y2) {
        path.quadTo((float) x1, (float) y1, (float) x2, (float) y2);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a cubic bezier from the last point, approaching control points
     * (x1,y1) and (x2,y2), and ending at (x3,y3). If no moveTo() call has been
     * made for this contour, the first point is automatically set to (0,0).
     *
     * @param x1 The x-coordinate of the 1st control point on a cubic curve
     * @param y1 The y-coordinate of the 1st control point on a cubic curve
     * @param x2 The x-coordinate of the 2nd control point on a cubic curve
     * @param y2 The y-coordinate of the 2nd control point on a cubic curve
     * @param x3 The x-coordinate of the end point on a cubic curve
     * @param y3 The y-coordinate of the end point on a cubic curve
     * @return WoWoPathView itself
     */
    public WoWoPathView pathCubicTo(float x1, float y1, float x2, float y2, float x3, float y3) {
        path.cubicTo(x1, y1, x2, y2, x3, y3);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    /**
     * Add a cubic bezier from the last point, approaching control points
     * (x1,y1) and (x2,y2), and ending at (x3,y3). If no moveTo() call has been
     * made for this contour, the first point is automatically set to (0,0).
     *
     * @param x1 The x-coordinate of the 1st control point on a cubic curve
     * @param y1 The y-coordinate of the 1st control point on a cubic curve
     * @param x2 The x-coordinate of the 2nd control point on a cubic curve
     * @param y2 The y-coordinate of the 2nd control point on a cubic curve
     * @param x3 The x-coordinate of the end point on a cubic curve
     * @param y3 The y-coordinate of the end point on a cubic curve
     * @return WoWoPathView itself
     */
    public WoWoPathView pathCubicTo(double x1, double y1, double x2, double y2, double x3, double y3) {
        path.cubicTo((float) x1, (float) y1, (float) x2, (float) y2, (float) x3, (float) y3);
        pathMeasure = new PathMeasure(path, false);
        pathLength = pathMeasure.getLength();
        return this;
    }

    public boolean isDashPath() {
        return dashPath;
    }

    /**
     * Whether the path is a dash path.
     *
     * @param dashPath Yes/No
     * @return WoWoPathView itself
     */
    public WoWoPathView dashPath(boolean dashPath) {
        this.dashPath = dashPath;
        if (dashPath) {
            dynamicalPathPhase = 0;
            pathEffect = new DashPathEffect(new float[]{dashSegmentLength, dashPaddingLength}, dynamicalPathPhase);
            paint.setPathEffect(pathEffect);
        }
        if (!dynamicalPath) invalidate();
        return this;
    }

    public float getDashSegmentLength() {
        return dashSegmentLength;
    }

    /**
     * The length of segment in the dash path.
     *
     * @param dashSegmentLength Length
     * @return WoWoPathView itself
     */
    public WoWoPathView dashSegmentLength(float dashSegmentLength) {
        this.dashSegmentLength = dashSegmentLength;
        if (dashPath) {
            pathEffect = new DashPathEffect(new float[]{dashSegmentLength, dashPaddingLength}, dynamicalPathPhase);
            paint.setPathEffect(pathEffect);
            if (!dynamicalPath) invalidate();
        }
        return this;
    }

    public float getDashPaddingLength() {
        return dashPaddingLength;
    }

    /**
     * The length of padding in the dash path.
     *
     * @param dashPaddingLength Length
     * @return WoWoPathView itself
     */
    public WoWoPathView dashPaddingLength(float dashPaddingLength) {
        this.dashPaddingLength = dashPaddingLength;
        if (dashPath) {
            pathEffect = new DashPathEffect(new float[]{dashSegmentLength, dashPaddingLength}, dynamicalPathPhase);
            paint.setPathEffect(pathEffect);
            if (!dynamicalPath) invalidate();
        }
        return this;
    }

    public boolean isDynamicalPath() {
        return dynamicalPath;
    }

    /**
     * Whether the path is animating all the time.
     *
     * @param dynamicalPath Yes/No
     * @return WoWoPathView itself
     */
    public WoWoPathView dynamicalPath(boolean dynamicalPath) {
        this.dynamicalPath = dynamicalPath;
        if (!this.dynamicalPath) dynamicalPathPhase = 0;
        invalidate();
        return this;
    }

    public float getDynamicalPathSpeed() {
        return dynamicalPathSpeed;
    }

    /**
     * The speed of the dynamical path.
     *
     * @param dynamicalPathSpeed Speed, in pixel/invalidate
     * @return WoWoPathView itself
     */
    public WoWoPathView dynamicalPathSpeed(float dynamicalPathSpeed) {
        this.dynamicalPathSpeed = dynamicalPathSpeed;
        dynamicalPathPhase = 0;
        return this;
    }

    public int getPathColor() {
        return pathColor;
    }

    /**
     * The color of the path.
     *
     * @param pathColor Color
     * @return WoWoPathView itself
     */
    public WoWoPathView pathColor(int pathColor) {
        this.pathColor = pathColor;
        if (!dynamicalPath) invalidate();
        return this;
    }

    public float getPathWidth() {
        return pathWidth;
    }

    /**
     * Width of path, in pixel.
     *
     * @param pathWidth Width, in pixel
     * @return WoWoPathView itself
     */
    public WoWoPathView pathWidth(float pathWidth) {
        this.pathWidth = pathWidth;
        if (!dynamicalPath) invalidate();
        return this;
    }

    public int getHeadImageRes() {
        return headImageRes;
    }

    /**
     * Set the resource of the head-image.
     *
     * @param headImageRes Resource of the image
     * @return WoWoPathView itself
     */
    public WoWoPathView headImageRes(int headImageRes) {
        this.headImageRes = headImageRes;
        initImage();
        if (!dynamicalPath) invalidate();
        return this;
    }

    public float getHeadImageWidth() {
        return headImageWidth;
    }

    /**
     * Set the width of the head-image.
     *
     * @param headImageWidth Width, in pixel
     * @return WoWoPathView itself
     */
    public WoWoPathView headImageWidth(float headImageWidth) {
        this.headImageWidth = headImageWidth;
        initImage();
        if (!dynamicalPath) invalidate();
        return this;
    }

    public float getHeadImageHeight() {
        return headImageHeight;
    }

    /**
     * Set the height of the head-image.
     *
     * @param headImageHeight Height, in pixel
     * @return WoWoPathView itself
     */
    public WoWoPathView headImageHeight(float headImageHeight) {
        this.headImageHeight = headImageHeight;
        initImage();
        if (!dynamicalPath) invalidate();
        return this;
    }
}
