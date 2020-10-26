package com.donteco.elcledcontroller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ColorBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
//        intent?.extras?.getInt("STATUS")?.let {
//            when (it) {
//                0 -> LedController.setColor(LedController.green)
//
//                1 -> LedController.setColor(LedController.red)
//
//                2 -> LedController.setColor(LedController.yellow)
//
//                3 -> LedController.setColor(LedController.blue)
//
//            }
//        }
        intent?.extras?.getString("COLOR")?.let {
            LedController.setClosestColor(ColorUtils.parseJsonColor(it))
        }
    }
}