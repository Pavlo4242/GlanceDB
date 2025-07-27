 
package com.glance.guolindev.ui.table

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glance.guolindev.databinding.GlanceLibraryTableItemBinding
import com.glance.guolindev.extension.setExtraMarginForFirstAndLastItem
import com.glance.guolindev.logic.model.Table
import com.glance.guolindev.ui.data.DataActivity

/**
 * Adapter for the RecyclerView to show all tables in db file.
 *
 * @author guolin
 * @since 2020/9/10
 */
class TableAdapter(private val tableList: List<Table>) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    class ViewHolder(tableItemBinding: GlanceLibraryTableItemBinding) : RecyclerView.ViewHolder(tableItemBinding.root) {
        val tableNameText: TextView = tableItemBinding.tableNameText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val tableItemBinding = GlanceLibraryTableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(tableItemBinding)
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            val table = tableList[position]
            DataActivity.actionOpenTable(parent.context, table.name)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setExtraMarginForFirstAndLastItem(position == 0, position == tableList.size - 1)
        val table = tableList[position]
        holder.tableNameText.text = table.name
    }

    override fun getItemCount() = tableList.size

}