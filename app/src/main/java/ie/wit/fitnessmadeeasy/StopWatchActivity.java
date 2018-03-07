package ie.wit.fitnessmadeeasy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ie.wit.fitnessmadeeasy.activities.Report;

public class StopWatchActivity extends NavUser {

    Button btnStart, btnPause, btnLap;
    TextView txtTimer;
    Handler customHandler = new Handler();
    LinearLayout container;
    View addView;

    long starTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L, updateTime=0L;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis()-starTime;
            updateTime = timeSwapBuff+timeInMilliseconds;
            int secs=(int) (updateTime/1000);
            int mins=secs/60;
            secs%=60;
            int milliseconds =(int) (updateTime%1000);
            txtTimer.setText(""+mins+":"+String.format("%2d", secs)+":"
                                +String.format("%3d", milliseconds));
            customHandler.postDelayed(this,0);

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnLap = (Button) findViewById(R.id.btnLap);
        txtTimer = (TextView) findViewById(R.id.timerValue);
        container = (LinearLayout) findViewById(R.id.container);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starTime = SystemClock.uptimeMillis();

                customHandler.postDelayed(updateTimerThread,0);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff=timeInMilliseconds;

                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        btnLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 addView = inflater.inflate(R.layout.row_count,null);
                TextView txtValue = (TextView)addView.findViewById(R.id.textContent);
                txtValue.setText(txtTimer.getText());
                container.addView(addView);

            }
        });



    }
}
