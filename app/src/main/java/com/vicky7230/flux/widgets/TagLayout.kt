package com.vicky7230.flux.widgets

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


/**
 * Created by vicky on 24/3/18.
 */

class TagLayout(context: Context) : ViewGroup(context, null, 0) {

    constructor(context: Context, attrs: AttributeSet) : this(context)


    var deviceWidth: Int = 0

    private fun init(context: Context) {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val deviceDisplay = Point()
        display.getSize(deviceDisplay)
        this.deviceWidth = deviceDisplay.x
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        val childLeft = paddingLeft
        val childTop = paddingTop
        val childRight = measuredWidth - paddingRight
        val childWidth = childRight - childLeft
        val childHeight = measuredHeight - paddingBottom - childTop
        var maxHeight = 0
        var curLeft = childLeft
        var curTop = childTop
        var i = 0
        while (i < count) {
            val child = getChildAt(i)
            if (child.visibility != 8) {
                child.measure(View.MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY))
                val curWidth = child.measuredWidth
                val curHeight = child.measuredHeight
                if (curLeft + curWidth >= childRight) {
                    curLeft = childLeft
                    curTop += maxHeight
                    maxHeight = 0
                }
                child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight)
                if (maxHeight < curHeight) {
                    maxHeight = curHeight
                }
                curLeft += curWidth
                i++
            } else {
                return
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount
        var maxHeight = 0
        var maxWidth = 0
        var childState = 0
        var mLeftWidth = 0
        var rowCount = 0
        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility != 8) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                maxWidth += Math.max(maxWidth, child.measuredWidth)
                mLeftWidth += child.measuredWidth
                if (mLeftWidth / this.deviceWidth > rowCount) {
                    maxHeight += child.measuredHeight
                    rowCount++
                } else {
                    maxHeight = Math.max(maxHeight, child.measuredHeight)
                }
                childState = View.combineMeasuredStates(childState, child.measuredState)
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth, suggestedMinimumWidth), widthMeasureSpec, childState), View.resolveSizeAndState(Math.max(maxHeight, suggestedMinimumHeight), heightMeasureSpec, childState shl 16))
    }


}