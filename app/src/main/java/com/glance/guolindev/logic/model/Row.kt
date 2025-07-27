 
package com.glance.guolindev.logic.model

/**
 * Data class represents a row in a table.
 *
 * @author guolin
 * @since 2020/9/12
 */
data class Row(val lineNum: Int, val dataList: List<Data>)

data class Data(var value: String, val columnName: String, val columnType: String, val isPrimaryKey: Boolean)