package com.kriss.roomcontrol

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.bumptech.glide.request.target.NotificationTarget

class AlertReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)

        context.startService(Intent(context, AlertService::class.java))

    }


}