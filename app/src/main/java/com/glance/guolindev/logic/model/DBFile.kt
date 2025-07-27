 
package com.glance.guolindev.logic.model

import java.util.*

/**
 * Data class represents db files.
 *
 * @author guolin
 * @since 2020/8/24
 */
data class DBFile(val name: String, val path: String, val internal: Boolean, val modifyTime: Date) {
    fun isRoomDatabase(): Boolean {
        return name.contains("translator_database") || 
               name.contains("room") || 
               name.contains("app_database")
    }
}
