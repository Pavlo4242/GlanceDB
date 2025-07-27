 
package com.glance.guolindev

import android.content.Context

/**
 * Global singleton class to provide necessary data.
 *
 * @author guolin
 * @since 2020/8/15
 */
object Glance {

    /**
     * Global application context.
     */
    lateinit var context: Context

    fun initialize(_context: Context) {
        context = _context.applicationContext
    }

}