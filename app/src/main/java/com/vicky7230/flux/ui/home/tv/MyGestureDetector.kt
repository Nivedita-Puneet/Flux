package com.vicky7230.flux.ui.home.tv

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by vicky on 21/3/18.
 */
class MyGestureDetector : GestureDetector.OnGestureListener {

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        //TODO
        //println("LOOONGGGGGGGGGGGGG.............")
    }

}