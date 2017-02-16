package com.weather.forecast.design.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.weather.forecast.R;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.ButterKnife;
import static java.lang.Math.abs;


public class WeatherGraph extends View {

    @BindColor(R.color.colorAccent) int colorAccent;
    @BindColor(R.color.colorPrimaryDark) int colorPrimaryDark;

    ArrayList<PointF> points;
    ArrayList<RectF> intervals;

    Paint point_style;
    Paint line_style;
    Paint rect_style;
    Paint fontPaint;

    OnGraphClick graphClick;

    public WeatherGraph(Context context) {
        super(context);


        initData();
    }

    public WeatherGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();


    }

    public WeatherGraph(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawARGB(80, 102, 204, 255);
        float height = this.getHeight();
        float step_x = this.getWidth() / (points.size());
        float step_y = (this.getHeight() * 0.8f -50)/ 24f;
        Path path = new Path();



        for (int i = 0; i < points.size(); i++) {
            PointF cur = points.get(i);
            RectF interval;
            if(cur.y > -200)
                interval = new RectF(step_x * cur.x, height - step_y * abs(cur.y)-50,
                    step_x * cur.x +step_x, height );
            else
                interval = new RectF(step_x * cur.x, height,
                        step_x * cur.x +step_x, height );

            intervals.add(interval);
            path.addRect(interval, Path.Direction.CW);

            canvas.drawPath(path, rect_style);
            canvas.drawText("" + points.get(i).y + "\u00B0",step_x * cur.x + step_x / 3f,
                    height - step_y * abs(cur.y)-50, fontPaint);

            canvas.drawText(3 * points.get(i).x+"0", step_x * cur.x + step_x / 4f, height-5, fontPaint);
        }


    }

    private void initData(){
        ButterKnife.bind(this);
        points = new ArrayList<>();
        points.add(new PointF(1,24));

        intervals = new ArrayList<>(8);


        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(24);
        fontPaint.setStyle(Paint.Style.STROKE);

        point_style = new Paint();
        point_style.setColor(colorAccent);
        point_style.setStrokeWidth(8);
        line_style =  new Paint();
        line_style.setColor(colorPrimaryDark);
        line_style.setStrokeWidth(2);

        rect_style =  new Paint();
        rect_style.setColor(colorPrimaryDark);
        rect_style.setAlpha(50);
        rect_style.setStrokeWidth(2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getX();

        for(int i=0;i<8;i++){
            if(intervals.get(i).intersect(event.getX(),event.getY(),event.getX(),event.getY())) {
                graphClick.click(i);
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setPoints(ArrayList points) {
        this.points = points;
    }


    public void setOnGraphClick(OnGraphClick onclick) {
        graphClick = onclick;
    }

    public static String roundFloat(float value){
        StringBuffer str_value = new StringBuffer(""+value);
        int dtId  = str_value.indexOf(".");
        return str_value.subSequence(0,dtId+2).toString();
    }


    public interface OnGraphClick{
        void click(int position);
    }

}
