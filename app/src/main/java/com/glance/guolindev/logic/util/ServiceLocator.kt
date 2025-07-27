 
package com.glance.guolindev.logic.util

import com.glance.guolindev.logic.repository.FileRepository
import com.glance.guolindev.logic.repository.DatabaseRepository

/**
 * ServiceLocator to provide instances that no one should create.
 * Basically this work should be done by a DI library like hilt, but since we do not charge the Application class, so just keep it simple by a ServiceLocator.
 *
 * @author guolin
 * @since 2020/9/4
 */
object ServiceLocator {

    private val dbScanner = DBScanner()

    private val dbHelper = DBHelper()

    private val databaseRepository = DatabaseRepository(dbHelper)

    fun provideDBRepository() = FileRepository(dbScanner)

    fun provideTableRepository() = databaseRepository

}