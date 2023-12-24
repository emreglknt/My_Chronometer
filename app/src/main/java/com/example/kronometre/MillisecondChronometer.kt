package com.example.kronometre
import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer

class MillisecondChronometer(context: Context, attrs: AttributeSet) : Chronometer(context, attrs) {
    private var timeWhenStopped = 0L
    private val handler = Handler()
    private val updateTask = object : Runnable {
        override fun run() {
            val elapsedMillis = SystemClock.elapsedRealtime() - base
            text = formatTime(elapsedMillis)
            handler.postDelayed(this, 1)
        }
    }

    init {
        base = SystemClock.elapsedRealtime()
    }

    fun startChronometer() {
        base = SystemClock.elapsedRealtime() + timeWhenStopped
        handler.post(updateTask)
    }

    fun pauseChronometer() {
        timeWhenStopped = base - SystemClock.elapsedRealtime()
        handler.removeCallbacks(updateTask)
    }

    fun resetChronometer() {
        pauseChronometer()
        base = SystemClock.elapsedRealtime()
        text = formatTime(0)
    }

    private fun formatTime(millis: Long): String {
        val totalSecs = millis / 1000
        val minutes = (totalSecs / 60).toInt()
        val seconds = (totalSecs % 60).toInt()
        val milliseconds = (millis % 1000).toInt()
        return String.format("%02d:%02d:%02d", minutes, seconds, milliseconds)
    }
}
