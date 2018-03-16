package com.vicky7230.flux.ui.home.discover

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by vicky on 1/1/18.
 */
class DiscoverItemOffsetDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State?
    ) {
        outRect.left = offset
        outRect.right = offset
        outRect.bottom = offset
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = offset

    }

}