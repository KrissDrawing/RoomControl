package com.kriss.roomcontrol

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.*
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Suppress("DEPRECATION")
class AlertService : Service() {

    val CHANNEL_ID = "channel 1"
    private lateinit var ringtone: Ringtone
    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setDataBaseValues()
        createNotificationChannel()
        playAlarm()
        /*val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        mediaPlayer = MediaPlayer.create(this, R.raw.morning).apply{
            setAudioAttributes(audioAttributes)
            setVolume(1f,1f)

            //setAudioStreamType(AudioManager.STREAM_ALARM)
            //setVolume(500f,500f)
            //start()
        }*/
        displayNotification()


        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        //mediaPlayer?.stop()
        ringtone.stop()
    }

    private fun playAlarm() {
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notification)
        if(!ringtone.isPlaying) {
            ringtone.play()
        }
    }

    private fun displayNotification(){
        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        /*val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_spa)
            .setContentTitle("Wake up bro")
            .setContentText("It's time... it's time")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
*/

        val snoozeIntent = Intent(this, MainActivity::class.java).apply {
            //action = ACTION_SNOOZE
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_spa)
            .setContentTitle("Wake up bro")
            .setContentText("It's time... it's time")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_spa, "SNOOZE",
                snoozePendingIntent)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }

    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "nazwa kanalu" //(R.string.channel_name)
            val descriptionText = "opis kanalu" // getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setDataBaseValues(){
        val database = FirebaseDatabase.getInstance().reference.child("LED_CONTROL")

        database.child("TURN").setValue(getString(R.string.turn_on_lights))
        database.child("LEDSCENE").setValue("morning")
        database.child("BRIGHTNESS").setValue(100)
    }



}