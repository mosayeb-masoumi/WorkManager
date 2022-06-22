package com.example.workmanager.periodical

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.workmanager.R
import com.example.workmanager.worker.NotificationKotlinWorker
import kotlinx.android.synthetic.main.activity_periodical_kotlin.*
import java.util.concurrent.TimeUnit

class PeriodicalKotlinActivity : AppCompatActivity() {

    var workManager: WorkManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodical_kotlin)


        val constraints = Constraints.Builder().setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED).build()

        val workRequest = PeriodicWorkRequest.Builder(NotificationKotlinWorker::class.java, 6000, TimeUnit.MILLISECONDS)
            .setConstraints(constraints)
            .setInitialDelay(6000, TimeUnit.MILLISECONDS)
            .build()



        workManager = WorkManager.getInstance(this@PeriodicalKotlinActivity)

        btn_perodical_kotlin.setOnClickListener {
            workManager!!.enqueue(workRequest)
        }


        // to monitor the request


        // to monitor the request
        workManager!!.getWorkInfoByIdLiveData(workRequest.getId())
            .observe(this) { workInfo ->
                if (workInfo != null) {
                    Log.d("periodicWorkRequest", "Status changed to : " + workInfo.state)
                    val result = "periodicWorkRequest Status changed to : " + workInfo.state
                    txt_perodical_kotlin.setText(result)
                }
            }


    }
}