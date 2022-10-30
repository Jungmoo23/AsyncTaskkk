package com.example.asynctaskkk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private final String TAG ="MainActivity";
    ProgressBar progressBar;
    Button btn1;
    Test t = new Test();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.ProgressBarr);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.cancel(true);
            }
        });


        t.execute();

    }

    private class Test extends AsyncTask<Void,Integer,Void>{

        private int comInt=0;

        @Override
        protected Void doInBackground(Void... voids) {
            progressBar.setProgress(0);
            for(int i=98; i<101; i++){
                Log.d(TAG, "onPostExecute: "+i);
                publishProgress(i);

                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){e.printStackTrace();}
            }

            return null;
        }
        protected void onProgressUpdate(Integer ... values) {
            comInt=values[0].intValue();
            progressBar.setProgress(comInt);

        }

        protected void onPostExecute(Void result) {
            progressBar.setProgress(0);
            Log.d(TAG, "onPostExecute: "+result);
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(comInt);
        }
    }

}