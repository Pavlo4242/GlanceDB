 
package com.glance.guolindev.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.glance.guolindev.R
import com.glance.guolindev.extension.dp
import com.glance.guolindev.logic.model.Row

/**
 * Custom view to represent a table cell. Draw the border(only left border is needed) for the cell when necessary.
 *
 * @author guolin
 * @since 2020/9/27
 */
class TableCellView(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {

    /**
     * Use this paint to draw border of table.
     */
    private val borderPaint = Paint()

    /**
     * Record the first timestamp this view is clicked.
     */
    internal var firstClickTimeStamp = 0L

    /**
     * Keep the column index of current table row.
     */
    var columnIndex = -1

    /**
     * Keep the row data of current table row.
     */
    var row: Row? = null
        set(value) {
            field = value
            if (columnIndex != -1) { // This means we always need to set columnIndex before we set row.
                text = value?.let {
                    it.dataList[columnIndex].value
                } ?: ""
            }
        }

    init {
        borderPaint.color = ContextCompat.getColor(context, R.color.glance_library_table_border)
        borderPaint.strokeWidth = 1f.dp
    }

    override fun onDraw(canvas: Canvas) {
        if (columnIndex != -1 && columnIndex != 0) {
            // We don't draw the left border if it's first cell.
            canvas.drawLine(0f, 0f, 0f, height.toFloat(), borderPaint)
        }
        super.onDraw(canvas)
    }

}