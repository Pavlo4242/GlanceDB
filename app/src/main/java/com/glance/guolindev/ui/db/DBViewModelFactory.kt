 
package com.glance.guolindev.ui.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.glance.guolindev.logic.util.ServiceLocator

/**
 * The ViewModel Factory to create DBViewModel instance and pass a DBRepository instance as parameter which provided by ServiceLocator.
 *
 * @author guolin
 * @since 2020/9/4
 */
class DBViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DBViewModel(ServiceLocator.provideDBRepository()) as T
    }

}