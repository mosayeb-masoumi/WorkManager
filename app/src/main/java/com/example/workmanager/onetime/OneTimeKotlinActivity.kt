package com.example.workmanager.onetime

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.workmanager.R
import com.example.workmanager.worker.NotificationKotlinWorker
import kotlinx.android.synthetic.main.activity_one_time_kotlin.*

class OneTimeKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_time_kotlin)


        val workRequest = OneTimeWorkRequest.Builder(NotificationKotlinWorker::class.java).build()
        val workManager = WorkManager.getInstance(this)

        btn_onetime_kotlin.setOnClickListener {
            workManager.enqueue(workRequest)
        }

        // to monitor the request


        // to monitor the request
        workManager.getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer<WorkInfo?> { workInfo ->
                if (workInfo != null) {
                    Log.d("periodicWorkRequest", "Status changed to : " + workInfo.state)
                    val result = "periodicWorkRequest Status changed to : " + workInfo.state
                    txt_onetime_kotlin.text = result
                }
            })


    }
}