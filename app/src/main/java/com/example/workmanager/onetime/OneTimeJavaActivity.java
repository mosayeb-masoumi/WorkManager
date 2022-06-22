package com.example.workmanager.onetime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.workmanager.R;
import com.example.workmanager.periodical.PeriodicalJavaActivity;
import com.example.workmanager.worker.NotificationJavaWorker;

public class OneTimeJavaActivity extends AppCompatActivity {


    Button btn_onetime_java ;
    TextView txt_onetime_java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time_java);

        btn_onetime_java = findViewById(R.id.btn_onetime_java);
        txt_onetime_java = findViewById(R.id.txt_onetime_java);



        OneTimeWorkRequest workRequest =  new OneTimeWorkRequest.Builder(NotificationJavaWorker.class).build();
        WorkManager workManager = WorkManager.getInstance(OneTimeJavaActivity.this);

        btn_onetime_java.setOnClickListener(view -> workManager.enqueue(workRequest));

        // to monitor the request
        workManager.getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null) {
                            Log.d("periodicWorkRequest", "Status changed to : " + workInfo.getState());
                            String result = "periodicWorkRequest Status changed to : " + workInfo.getState();
                            txt_onetime_java.setText(result);
                        }
                    }
                });

    }
}