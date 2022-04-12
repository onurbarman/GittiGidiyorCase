package com.onurbarman.gittigidiyorcase.utils

import android.view.View


/**
 * Extension function to handle view clicks safely.
 * @param onSafeClick
 */
fun View.setOnSafeClickListener(
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

/**
 * Extension function to handle view clicks safely with a time interval.
 * @param interval time of interval
 * @param onSafeClick callback/Lambda
 */
fun View.setOnSafeClickListener(
    interval: Int,
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener(interval) { v ->
        onSafeClick(v)
    })
}