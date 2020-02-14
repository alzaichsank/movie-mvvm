package id.alik.movie_mvvm.common.utils

import android.util.Log
import id.alik.movie_mvvm.BuildConfig

object Logger {

    var logEnabled: Boolean = true
    private const val TAG = "CleanLogger"

    fun debug(message: String) {
        if(BuildConfig.DEBUG) {
            debug(TAG, message)
        }
    }

    fun debug(tag: String, message: String) {
        if(BuildConfig.DEBUG) {
            if (logEnabled) {
                Log.d(tag, ">> $message")
            }
        }
    }

    fun error(message: String, throwable: Throwable) {
        if(BuildConfig.DEBUG) {
            error(TAG, message, throwable)
        }
    }

    fun error(tag: String, message: String, throwable: Throwable) {
        if(BuildConfig.DEBUG) {
            if (logEnabled) {
                Log.e(tag, ">> $message", throwable)
            }
        }
    }

}