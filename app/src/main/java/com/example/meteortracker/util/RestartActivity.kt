package com.example.meteortracker.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat

fun restartActivity(context: Context) {
    if (context is Activity) {
        val intent = Intent(context, context::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )

        val options = ActivityOptionsCompat.makeCustomAnimation(
            context,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        ).toBundle()

        context.startActivity(intent, options)
        context.finish()
    }
}
