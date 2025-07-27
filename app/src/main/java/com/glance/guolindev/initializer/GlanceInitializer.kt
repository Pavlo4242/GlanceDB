 
package com.glance.guolindev.initializer

import android.content.Context
import androidx.startup.Initializer
import com.glance.guolindev.Glance

/**
 * Customize initializer to initialize Glance.
 *
 * @author guolin
 * @since 2020/8/15
 */
class GlanceInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Glance.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}