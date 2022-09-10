package com.example.alarmclock.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alarmclock.CreateAlarm.TimeService
import com.example.alarmclock.R
import kotlinx.android.synthetic.main.fragment_count_up.*
import kotlin.math.roundToInt

class CountUpFragment : Fragment() {
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_count_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serviceIntent = Intent(requireActivity().applicationContext, TimeService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(TimeService.TIMER_UPDATED))
        initView()
    }

    private var updateTime:BroadcastReceiver =object :BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimeService.TIMER_EXTRA,0.0)
            txtTime.text = getTimeString(time)
        }
    }

    private fun getTimeString(time: Double): String{
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes =resultInt % 86400 % 3600 / 60
        val seconds =resultInt % 86400 % 3600 % 60
        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int):
            String = String.format(" %02d : %02d : %02d ",hours,minutes,seconds)


    private fun initView() {
        imgStartStop.setOnClickListener {
            startStopTimer()
        }

        imgDelete.setOnClickListener {
            deleteTimer()
        }

        imgReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        deleteTimer()
        startTimer()
    }

    private fun deleteTimer() {
        stopTimer()
        time=0.0
        txtTime.text = getTimeString(time)
        imgPause.visibility = View.VISIBLE
    }

    private fun startStopTimer() {
        if(timerStarted){
            stopTimer()
        }else{
            startTimer()
        }
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimeService.TIMER_EXTRA,time)
        requireContext().startService(serviceIntent)
        imgPause.visibility = View.GONE
        timerStarted = true
    }

    private fun stopTimer() {
        requireContext().stopService(serviceIntent)
        imgPause.visibility = View.VISIBLE
        timerStarted = false
    }

}