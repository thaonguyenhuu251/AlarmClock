package com.example.alarmclock.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.alarmclock.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list_clock.*


class ListClockFragment() : Fragment(), View.OnClickListener {
    lateinit var createAlarm: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_clock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAlarm = view.findViewById (R.id.btnCreate);
        createAlarm.setOnClickListener(this);
        initView()
    }

    private fun initView() {
        btnCreate.setBackgroundResource(R.drawable.ic_baseline_add)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String){

        }

    }

    override fun onClick(view: View?) {
        Navigation.findNavController(view!!).navigate(R.id.action_list_Clock_to_create_Clock);
    }
}

