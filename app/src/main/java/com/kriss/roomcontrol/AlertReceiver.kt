package com.kriss.roomcontrol

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Vibrator

class AlertReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
    }
}