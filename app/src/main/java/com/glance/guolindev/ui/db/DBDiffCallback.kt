 
package com.glance.guolindev.ui.db

import androidx.recyclerview.widget.DiffUtil
import com.glance.guolindev.logic.model.DBFile

/**
 * DiffUtil callback implementation to help recyclerview know how to converts the old list into the new list.
 *
 * @author guolin
 * @since 2020/8/28
 */
class DBDiffCallback(private val oldList: List<DBFile>, private val newList: List<DBFile>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].path == newList[newItemPosition].path
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}