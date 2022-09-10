package com.example.alarmclock.CreateAlarm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class TimeService : Service() {
    private val timer = Timer()
    override fun onBind(p0: Intent?): IBinder? = null

    companion object{
        const val TIMER_UPDATED ="timerUpdated"
        const val TIMER_EXTRA ="timerExtra"
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(TIMER_EXTRA,0.0)
        timer.scheduleAtFixedRate(TimerTask(time),0,1000)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimerTask(private var time: Double): java.util.TimerTask() {
        override fun run(){
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIMER_EXTRA,time)
            sendBroadcast(intent)
        }

    }
}