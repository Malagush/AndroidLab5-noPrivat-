package com.example.event_handling;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.GestureDetector;
import androidx.core.view.GestureDetectorCompat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener
{

     TextView textView ;
    TextView textView1 ;
    TextView textView2 ;
    Button b1 ;

    private GestureDetectorCompat gDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.textV);
         textView1 = findViewById(R.id.textView);
         textView2 = findViewById(R.id.textView2);
        b1 = (Button) findViewById(R.id.button);

        b1.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);

        textView.setText("Выберите что то из меню");

        this.gDetector = new GestureDetectorCompat(this,this);
        gDetector.setOnDoubleTapListener(this);


        Button downSelected = (Button) findViewById(R.id.button);
        downSelected.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
             TextView textView = findViewById(R.id.textV);
                textView.setText("Держали кнопку");
                return true;
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        handleTouch(event);
        return super.onTouchEvent(event);
    }



    @Override
    public boolean onDown(MotionEvent event) {
        textView.setText ("Вниз");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        textView.setText("Отпущено после прокрутки");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        textView.setText("Жмем долго");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        textView.setText("Прокручиваем");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        textView.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        textView.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        textView.setText("Двойно таб");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        textView.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        textView.setText("onSingleTapConfirmed");
        return true;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id) {
            case R.id.ButtonClick1:
                setTitle("ButtonClick");
                b1.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                return true;
            case R.id.MotionEvent1:
                setTitle("MotionEvent");
                b1.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);

                return true;
            case R.id.CommonGestures1:
                setTitle("CommonGestures");
                b1.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void uttonclicked(View view) {
        TextView textView = findViewById(R.id.textV);
        textView.setText("Нажата 1 раз кнопка");

    }

    void handleTouch(MotionEvent m) {
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);

        int pointerCount = m.getPointerCount();

        for (int i = 0; i < pointerCount; i++)
        {
            int x = (int) m.getX(i);
            int y = (int) m.getY(i);
            int id = m.getPointerId(i);
            int action = m.getActionMasked();
            int actionIndex = m.getActionIndex();
            String actionString;


            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                    actionString = "Нажал";
                    break;
                case MotionEvent.ACTION_UP:
                    actionString = "Отпустил";
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    actionString = "Второй нажал";
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    actionString = "Второй отпустил";
                    break;
                case MotionEvent.ACTION_MOVE:
                    actionString = "Ведем";
                    break;
                default:
                    actionString = "";
            }

            String touchStatus = "Action: " + actionString + " Index: " +
                    actionIndex + " ID: " + id + " X: " + x + " Y: " + y;
            if (id == 0)
                textView1.setText(touchStatus);
            else
                textView2.setText(touchStatus);
        }
    }

}