package com.med.inssetandroidcloud2022.randomUser.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.med.inssetandroidcloud2022.R
import com.med.inssetandroidcloud2022.databinding.ActivityNotificationBinding
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserUi

class CustomNotificationManager(private val context: Context, private val randomUserUi: RandomUserUi) {

    companion object {
        const val TAG = "CustomNotificationManager"
        private const val CHANNEL_ID = "demo_purpose"
        private const val CHANNEL_NAME = "demo purpose channel id"
        private const val CHANNEL_DESCRIPTION = "This channel will received only demo notification"
        private const val REQUEST_CODE = 94043
        private const val NOTIFICATION_ID = 42
    }

    /** Notification manager*/
    private val mNotificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        if (!channelNotificationExists()) {
            createNowPlayingChannel()
        }
    }

    fun createNotificationCompatBuilder() {
        val notificationCompat = NotificationCompat.Builder(context, CHANNEL_ID)
            .setAutoCancel(true)
            .setContentTitle("Nouveau user ajouté!")
            .setContentText("notification content")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("User " + randomUserUi.email)
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, notificationCompat.build())
        }
    }

    /**
     * Check if channel is already created
     */
    private fun channelNotificationExists() = mNotificationManager.getNotificationChannel(CHANNEL_ID) != null

    /**
     * Create the cancel id for notification
     */
    private fun createNowPlayingChannel() {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        mNotificationManager.createNotificationChannel(notificationChannel)
    }
}