 
package com.glance.guolindev.logic.model

/**
 * Use this bean to update database and observe update result.
 * @author guolin
 * @since 2021/8/3
 */
class UpdateBean(
    val table: String,
    val row: Row,
    val position: Int,
    val columnIndex: Int,
    val updateValue: String
)