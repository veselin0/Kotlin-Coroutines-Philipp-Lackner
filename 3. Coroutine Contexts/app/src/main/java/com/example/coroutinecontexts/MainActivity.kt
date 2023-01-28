package com.example.coroutinecontexts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Starting new coroutine in thread ${Thread.currentThread().name}")
            val answer = doNetworkCall()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
                var dummyText = findViewById<TextView>(R.id.tvDummyText)
                 dummyText.text = answer
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}