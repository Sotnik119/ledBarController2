package com.donteco.elcledcontroller

import android.graphics.Color
import android.util.Log
import androidx.core.graphics.red
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

object LedController {

    enum class LedColors(val command: String, val color: Int) {
        RED("0x04", Color.rgb(255, 0, 0)),
        GREEN("0x05", Color.rgb(0, 255, 0)),
        BLUE("0x06", Color.rgb(0, 0, 255)),
        WHITE("0x07", Color.rgb(255, 255, 255)),
        ORANGE("0x08", Color.rgb(255, 180, 0)),
        YELLOW("0x0c", Color.rgb(255, 255, 0)),
        YELLOW_GREEN("0x10", Color.rgb(200, 255, 0)),
        GREEN_YELLOW("0x14", Color.rgb(170, 255, 0)),
        SUPER_LIGHT_BLUE("0x09", Color.rgb(0, 255, 255)),
        SUPER_LIGHT_BLUE2("0x0d", Color.rgb(0, 215, 255)),
        SUPER_LIGHT_BLUE3("0x11", Color.rgb(0, 193, 255)),
        SUPER_LIGHT_BLUE4("0x15", Color.rgb(0, 180, 255)),
        PURPLE1("0x0a", Color.rgb(58, 0, 255)),
        PURPLE2("0x0e", Color.rgb(83, 0, 255)),
        PURPLE3("0x12", Color.rgb(120, 0, 255)),
        PURPLE4("0x16", Color.rgb(120, 85, 255)),
    }

    private const val enableLed = "0x03"
    const val red = "0x04"
    const val green = "0x05"
    const val blue = "0x06"
    const val yellow = "0x0c"
    const val orange = "0x08"

    fun setClosestColor(color: Int) {
        setColor(ColorUtils.findClosestColor(color).command)
    }

    private fun enableLed() {
        RootCommander.adbcommand(getStringCommand(enableLed))
    }

    private fun setColor(code: String) {
        enableLed()
        Timer("", false).schedule(200) {
            RootCommander.adbcommand(getStringCommand(code))
        }
    }


    private fun getStringCommand(code: String): String {
        Log.d("color code", code)
        return "echo w ${code.trim()} > ./sys/devices/platform/led_con_h/zigbee_reset"
    }
}