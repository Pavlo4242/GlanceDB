 
package com.glance.guolindev.logic.model

/**
 * Data class represents a column in a table.
 *
 * @author guolin
 * @since 2020/9/12
 */
data class Column(val name: String, val type: String, val isPrimaryKey: Boolean) {

    /**
     * The default width of column is 100.
     */
    var width = 100

}