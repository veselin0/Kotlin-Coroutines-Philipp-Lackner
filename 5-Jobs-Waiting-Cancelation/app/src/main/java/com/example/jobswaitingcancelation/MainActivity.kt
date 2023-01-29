package com.example.jobswaitingcancelation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5) {
//                Log.d(TAG, "Coroutine is still running...")
//                delay(1000L)
            Log.d(TAG, "Starting long running calculation...")
            withTimeout(3000L) {
                for (i in 30..40) {
                    if (isActive) {
                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
                    }
                }
            }
            Log.d(TAG, "Ending long running calculation...")
        }

//        runBlocking {
////            job.join()
//            delay(2000L)
//            job.cancel(/* We can pass a cancellation exception here */)
//            Log.d(TAG, "Main Tread is continuing...")
//        }

//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG, "Job Canceled!")
//        }

    }

    //    Recursive Fibonacci function:
    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}