package com.chetan.movietime.common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rebel.demo.common.Navigator

/**
 * Created by Chetan on 2020-03-04.
 */

/**
 * Base activity which contains all the common code related to navigation.
 */
private const val ACTIVITY_NAVIGATION_TIME_OUT: Long = 100

abstract class BaseActivity : AppCompatActivity() {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupLiveDataComponents()
    }

    /**
     * Every activity extends this class and implements the abstract method @see #setupLiveDataComponents()
     * In the implementation the live data elements are to be observed
     */
    abstract fun setupLiveDataComponents()


    /**
     * Method to resolve activity navigation from the Navigator object
     */
    protected fun resolveNavigation(navigationUtils: Navigator?) {
        if (navigationUtils != null) {
            val intent: Intent
            when (navigationUtils.getNavigationAction()) {
                Navigator.NavigationAction.StartActivity -> {
                    intent = Intent(this, navigationUtils.getDestination())
                    if (navigationUtils.getBundle() != null) {
                        intent.putExtras(navigationUtils.getBundle()!!)
                    }
                    startActivity(intent)
                }
                Navigator.NavigationAction.StartActivityFinishCurrent -> {
                    intent = Intent(this, navigationUtils.getDestination())
                    if (navigationUtils.getBundle() != null) {
                        intent.putExtras(navigationUtils.getBundle()!!)
                    }
                    startActivity(intent)
                    allowFinish()
                }
                Navigator.NavigationAction.StartActivityForResult -> {
                    intent = Intent(this, navigationUtils.getDestination())
                    if (navigationUtils.getBundle() != null) {
                        intent.putExtras(navigationUtils.getBundle()!!)
                    }
                    startActivityForResult(intent, navigationUtils.getRequestCode())
                }
                Navigator.NavigationAction.SendResult -> {
                    intent = Intent()
                    if (navigationUtils.getDataToSend() != null) {
                        for (key in navigationUtils.getDataToSend()!!.keys) {
                            intent.putExtra(key, navigationUtils.getDataToSend()!!.get(key))
                        }
                    }
                    if (navigationUtils.isDelayedExecution()) {
                        Handler().postDelayed({ allowFinish(intent) }, ACTIVITY_NAVIGATION_TIME_OUT)
                    } else {
                        allowFinish(intent)
                    }
                }
                Navigator.NavigationAction.FinishCurrent -> if (navigationUtils.isDelayedExecution()) {
                    Handler().postDelayed({ allowFinish() }, ACTIVITY_NAVIGATION_TIME_OUT)
                } else {
                    allowFinish()
                }
                Navigator.NavigationAction.ImplicitIntent -> startActivityForResult(
                    navigationUtils.getImplicitIntent(),
                    navigationUtils.getRequestCode()
                )
                Navigator.NavigationAction.StartActivityWithCloseAll -> {
                    intent = Intent(this, navigationUtils.getDestination())
                    if (navigationUtils.getBundle() != null) {
                        intent.putExtras(navigationUtils.getBundle()!!)
                    }
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }

    private fun allowFinish() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun allowFinish(intent: Intent) {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun showMsg(message: String?) {
        if (message != null) {
            Snackbar.make(
                window.decorView.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}