 
package com.glance.guolindev.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.glance.guolindev.R
import com.glance.guolindev.extension.dp

/**
 * Custom view to represent a row of the table. Draw the top and bottom border for the row when necessary.
 *
 * @author guolin
 * @since 2020/9/27
 */
class TableRowLayout(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    /**
     * Use this paint to draw border of table.
     */
    private val borderPaint = Paint()

    init {
        setWillNotDraw(false) // Layout may not call onDraw(), so we need to disable that.
        borderPaint.color = ContextCompat.getColor(context, R.color.glance_library_table_border)
        borderPaint.strokeWidth = 1f.dp
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f, height.toFloat() - 0.5f, width.toFloat(), height.toFloat() - 0.5f, borderPaint)
        super.onDraw(canvas)
    }

}