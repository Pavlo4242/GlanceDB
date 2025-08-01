 
package com.glance.guolindev.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView

/**
 * Custom HorizontalScrollView with scroll listener function.
 * We can call [setScrollObserver] to set a observer, and get notified when HorizontalScroller is scrolled.
 *
 * @author guolin
 * @since 2020/10/2
 */
class HorizontalScroller(context: Context, attrs: AttributeSet? = null) : HorizontalScrollView(context, attrs)  {

    private lateinit var scrollObserver: (Float) -> Unit

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (::scrollObserver.isInitialized) {
            scrollObserver(l.toFloat())
        }
    }

    /**
     * Set a observer, and get notified when HorizontalScroller is scrolled.
     */
    fun setScrollObserver(observer: (Float) -> Unit) {
        scrollObserver = observer
    }

    /**
     * deal with the motion event to scroll, while passing the event downwards
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    /**
     * do not intercept touch event so that child scrollable view can also receive event to scroll
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean = false

}