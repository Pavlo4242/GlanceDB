 
package com.glance.guolindev.ui.table

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glance.guolindev.Glance
import com.glance.guolindev.R
import com.glance.guolindev.logic.model.Resource
import com.glance.guolindev.logic.model.Table
import com.glance.guolindev.logic.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * TableViewModel holds view data of TableActivity and provide api to specific table operations.
 *
 * @author guolin
 * @since 2020/9/4
 */
class TableViewModel(private val repository: DatabaseRepository) : ViewModel() {

    /**
     * The LiveData variable to observe db file list.
     */
    val tablesLiveData: LiveData<Resource<List<Table>>>
        get() = _tablesLiveData

    private val _tablesLiveData = MutableLiveData<Resource<List<Table>>>()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _tablesLiveData.value = Resource.error(throwable.message
                ?: Glance.context.getString(R.string.glance_library_uncaught_exception_happened))
    }

    /**
     * Get all tables in a specific db file represented by the [dbPath] parameter.
     */
    fun getAllTablesInDB(dbPath: String) = viewModelScope.launch(handler) {
        _tablesLiveData.value = Resource.loading()
        _tablesLiveData.value = Resource.success(repository.getSortedTablesInDB(dbPath))
    }

    /**
     * When the lifecycle of TableViewModel finished, we close the opened database.
     */
    override fun onCleared() {
        closeDatabase()
    }

    /**
     * Close the opened database.
     */
    private fun closeDatabase() = viewModelScope.launch(handler) {
        repository.closeDatabase()
    }

}