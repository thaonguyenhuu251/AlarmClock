package com.example.alarmclock.CreateAlarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.alarmclock.Activities.MainActivity
import com.example.alarmclock.R
import kotlinx.android.synthetic.main.fragment_create_alarm.*

class CreateAlarmFragment : Fragment() {
    var timePicker: TimePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_alarm, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideBottomNavigation(false)
        imgBack.setOnClickListener {
            backAndShowBottomNav()
        }
        onBackPressEvent()
    }
    private fun onBackPressEvent() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backAndShowBottomNav()
                }
            })
    }

    private fun backAndShowBottomNav() {
        Navigation.findNavController(requireView()).navigateUp()
        (requireActivity() as MainActivity).showBottomNavigation()
    }

}