 
package com.glance.guolindev.ui.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glance.guolindev.logic.util.ServiceLocator

/**
 * The ViewModel Factory to create DataViewModel instance and pass a DatabaseRepository instance as parameter which provided by ServiceLocator.
 *
 * @author guolin
 * @since 2020/9/4
 */
class DataViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(ServiceLocator.provideTableRepository()) as T
    }

}