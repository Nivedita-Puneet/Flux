package com.vicky7230.flux.ui.home

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by vicky on 1/1/18.
 */
class ItemOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State?
    ) {
        if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left = offset / 2
            outRect.right = offset
        }
        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right = offset / 2
            outRect.left = offset
        }

        outRect.bottom = offset
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = offset
        if (parent.getChildAdapterPosition(view) == 1)
            outRect.top = offset
    }
}