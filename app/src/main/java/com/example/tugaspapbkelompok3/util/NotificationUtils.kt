package com.example.tugaspapbkelompok3.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.tugaspapbkelompok3.MainActivity
import com.example.tugaspapbkelompok3.R

val NOTIFICATION_ID = 1

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context){
    val unfinishedEditIntent = Intent(applicationContext, MainActivity::class.java)
    val unfinishedEditPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        unfinishedEditIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.unfinished_edit_channel_id)
    )
        .setSmallIcon(R.drawable.user)
        .setContentTitle("Edit Contact Unfinished")
        .setContentText(messageBody)
        .setContentIntent(unfinishedEditPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    notify(NOTIFICATION_ID, builder.build())
}