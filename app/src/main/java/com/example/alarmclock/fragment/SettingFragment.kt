package com.example.alarmclock.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alarmclock.Activities.MainActivity
import com.example.alarmclock.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingFragment: Fragment() {
    lateinit var intentNew: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var FACEBOOK_URL = "https://www.facebook.com/PhanAnhcs2501"
    var FACEBOOK_PAGE_ID = "751931421605113"

    //method to get the right URL to use in the intent
    private fun getFacebookPageURL(context: Context): String? {
        val packageManager: PackageManager = context.packageManager
        return try {
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$FACEBOOK_URL"
            } else { //older versions of fb app
                "fb://page/$FACEBOOK_PAGE_ID"
            }
        } catch (e: NameNotFoundException) {
            FACEBOOK_URL //normal web url
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        txtOpenVnFace.setOnClickListener {
            val intent = requireContext().packageManager.getLaunchIntentForPackage("com.example.facebookclone")
            if(intent != null) {
                startActivity(intent)
            } else {
                intentNew = Intent(Intent.ACTION_VIEW)
                intentNew.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intentNew.data = Uri.parse("market://detail?id=" + "com.whatsapp")
                startActivity(intentNew)
            }
        }

        txtOpenFacebook.setOnClickListener {
            //startActivity(getOpenFacebookIntent())
            openFacebookProfile(requireActivity())
        }

        txtOpenFacebookNew.setOnClickListener {
            startActivity(newFacebookIntent(requireActivity().packageManager,"https://www.facebook.com/PhanAnhHaUI"))
        }

        txtOpenYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/")
            intent.setPackage("com.google.android.youtube")
            val manager: PackageManager = requireActivity().packageManager
            val info = manager.queryIntentActivities(intent, 0)
            if (info.size > 0) {
                requireActivity().startActivity(intent)
            } else {
                //No Application can handle your intent
            }
        }

    }

    fun getOpenFacebookIntent(): Intent? {
        return try {
            requireActivity().packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/751931421605113"))
        } catch (e: Exception) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/PhanAnhcs2501"))
        }
    }

    private fun newFacebookIntent(pm: PackageManager, url: String): Intent? {
        var uri = Uri.parse(url)
        try {
            val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=$url")
            }
        } catch (ignored: NameNotFoundException) {
        }
        return Intent(Intent.ACTION_VIEW, uri)
    }

    private fun openFacebookProfile(activity: Activity) {
        val facebookIntent = Intent(Intent.ACTION_VIEW)
        val facebookUrl: String = getFacebookPageURL(activity).toString()
        facebookIntent.data = Uri.parse(facebookUrl)
        activity.startActivity(facebookIntent)
    }
}