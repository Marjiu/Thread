package wen.chuan.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    //找到UI工人的經紀人，這樣才能派遣工作  (找到顯示畫面的UI Thread上的Handler)
    private Handler mUI_Handler = new Handler();
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
    private HandlerThread mThread;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThread = new HandlerThread("name");                //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread.start();                                            //讓Worker待命，等待其工作 (開啟Thread)
        mThreadHandler=new Handler(mThread.getLooper());            //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler.post(r1);                                    //請經紀人指派工作名稱 r，給工人做

    }
    private Runnable r1=new Runnable () {
        public void run() {
            Log.d("test", "1118 New Thread Runing..");
            //請經紀人指派工作名稱 r，給工人做
            mUI_Handler.postDelayed(r2, 2000);
            mUI_HandlerMSG.sendEmptyMessage(MSG_UPLOAD_OK);
        }
    };

    //工作名稱 r2 的工作內容
    private Runnable r2=new Runnable () {

        public void run() {

            // TODO Auto-generated method stub
            //.............................
            //顯示畫面的動作
            TextView tv = findViewById(R.id.TextView);
            tv.setText("Got it");
        }

    };
    final int MSG_UPLOAD_OK = 1;
    final int MSG_UPLOAD_ERR = 2;
    private Handler mUI_HandlerMSG = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSG_UPLOAD_OK:
                    TextView tv = findViewById(R.id.TextView);
                    tv.setText("Got Message");

                    break;
                case MSG_UPLOAD_ERR:
                    TextView tv1 = findViewById(R.id.TextView);
                    tv1.setText("Message err");

                    break;
            }

        }

    };}






