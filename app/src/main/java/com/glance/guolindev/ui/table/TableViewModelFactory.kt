 
package com.glance.guolindev.ui.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glance.guolindev.logic.util.ServiceLocator

/**
 * The ViewModel Factory to create TableViewModel instance and pass a DatabaseRepository instance as parameter which provided by ServiceLocator.
 *
 * @author guolin
 * @since 2020/9/4
 */
class TableViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TableViewModel(ServiceLocator.provideTableRepository()) as T
    }

}