package com.donteco.elcledcontroller

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

object RootCommander {
    fun adbcommand(command: String): String? {
        var process: Process? = null
        var os: DataOutputStream? = null
        var excresult = ""
        try {
            process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process.outputStream)
            os.writeBytes(
                """
                $command
                
                """.trimIndent()
            )
            os.writeBytes("exit\n")
            os.flush()
            val `in` = BufferedReader(
                InputStreamReader(
                    process.inputStream
                )
            )
            val stringBuffer = StringBuffer()
            var line: String? = null
            while (`in`.readLine().also { line = it } != null) {
                stringBuffer.append("$line ")
            }
            excresult = stringBuffer.toString()
            os.close()
            // System.out.println(excresult);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return excresult
    }
}