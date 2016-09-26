package com.example.kranthi.handlersthreads;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler handler;

    String text;
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        editText = (EditText) findViewById(R.id.editText);
        text = editText.getText().toString();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle data = msg.getData();
                String result = data.getString("key");
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(),"Message received",Toast.LENGTH_LONG).show();
            }
        };
    }

    public void fetchData(View view) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        /*pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setIndeterminate(true);
        pd.setProgress(0);*/
        pd.show();
        //final int totalProgressTime = 100;

        new Thread(new Runnable() {
            @Override
            public void run() {

                /*int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        Thread.sleep(200);
                        jumpTime += 5;
                        pd.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }*/
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msgobject = Message.obtain();
                Bundle b = new Bundle();
                b.putString("key",editText.getText().toString() );
                msgobject.setData(b);
                handler.sendMessage(msgobject);
                //handler.sendEmptyMessage(0);
                pd.dismiss();


            }



        /*pd.dismiss();*/
        }).start();
    }
}
