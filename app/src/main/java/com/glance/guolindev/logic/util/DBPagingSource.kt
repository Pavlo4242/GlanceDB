 
package com.glance.guolindev.logic.util

import android.database.sqlite.SQLiteDatabase
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.glance.guolindev.logic.model.Column
import com.glance.guolindev.logic.model.Row
import java.lang.Exception

/**
 * We need to use a DBPagingSource and inherits from PagingSource to implements the paging function with paging3 library.
 *
 * @author guolin
 * @since 2020/9/17
 */
class DBPagingSource(private val dbHelper: DBHelper,
                     private val db: SQLiteDatabase,
                     private val table: String, private
                     val columns: List<Column>) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {
            val page = params.key ?: 0 // set page 0 as default
            val rowData = dbHelper.loadDataInTable(db, table, page, columns)
            val prevKey = if (page > 0) page - 1 else null
            val nextKey = if (rowData.isNotEmpty()) page + 1 else null
            LoadResult.Page(rowData, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Row>): Int? = null

}