package com.example.workmanager.periodical;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.workmanager.R;
import com.example.workmanager.worker.NotificationJavaWorker;

import java.util.concurrent.TimeUnit;

public class PeriodicalJavaActivity extends AppCompatActivity {


    WorkManager workManager;
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodical_java);

        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);


        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).setRequiredNetworkType(NetworkType.UNMETERED).build();

        final PeriodicWorkRequest periodicWorkRequest1 = new PeriodicWorkRequest.Builder(NotificationJavaWorker.class, 6000, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .setInitialDelay(6000, TimeUnit.MILLISECONDS)
                .build();

        workManager = WorkManager.getInstance(PeriodicalJavaActivity.this);
        btn.setOnClickListener(view ->
                workManager.enqueue(periodicWorkRequest1)
        );



        // to monitor the request
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null) {
                            Log.d("periodicWorkRequest", "Status changed to : " + workInfo.getState());

                            String result = "periodicWorkRequest Status changed to : " + workInfo.getState();
                            txt.setText(result);
                        }
                    }
                });

    }
}