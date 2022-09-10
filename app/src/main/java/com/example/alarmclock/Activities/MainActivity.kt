package com.example.alarmclock.Activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.alarmclock.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    private var isBackToCloseApp: Boolean = true
    private var isFromListClockToCreateClock: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.itemListLock -> {
                    setCurrentFragment(ListClockFragment())

                    true
                }
                R.id.itemCountDown -> {
                    setCurrentFragment(CountDownFragment())

                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }*/

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.setBackgroundResource(R.drawable.bg_bottom_nav)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun hideBottomNavigation(isFromListClockToCreateClock: Boolean) {
        bottomNavigationView.visibility = View.GONE
        isBackToCloseApp = false
        this.isFromListClockToCreateClock = isFromListClockToCreateClock
    }

    fun showBottomNavigation() {
        bottomNavigationView.setVisibility(View.VISIBLE)
        isBackToCloseApp = true
    }

}