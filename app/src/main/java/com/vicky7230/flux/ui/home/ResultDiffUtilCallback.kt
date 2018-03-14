package com.vicky7230.flux.ui.home

import android.support.v7.util.DiffUtil
import com.vicky7230.flux.data.network.model.results.Result


/**
 * Created by vicky on 3/1/18.
 */

class ResultDiffUtilCallback(
    private val oldRecipeList: MutableList<Result>?,
    private val newRecipeList: MutableList<Result>?
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldRecipeList?.size!!
    }

    override fun getNewListSize(): Int {
        return newRecipeList?.size!!
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipeList?.get(oldItemPosition)?.id == newRecipeList?.get(newItemPosition)?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipeList?.get(oldItemPosition) == newRecipeList?.get(newItemPosition)
    }
}