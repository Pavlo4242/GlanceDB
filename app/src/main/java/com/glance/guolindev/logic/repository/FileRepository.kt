 
package com.glance.guolindev.logic.repository

import android.content.Context
import com.glance.guolindev.Glance
import com.glance.guolindev.logic.model.DBFile
import com.glance.guolindev.logic.util.DBScanner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val GLANCE_DB_CACHE = "glance_library_db_cache"

private const val GLANCE_CACHED_DATABASES = "glance_library_cached_databases"

/**
 * DBRepository to communicate with ViewModels and .db files layer back end logic handler.
 *
 * @author guolin
 * @since 2020/8/25
 */
class FileRepository(private val dbScanner: DBScanner) {

    /**
     * Load db files from cache. This will show the cached db files on ui immediately.
     */
    suspend fun loadCachedDbFiles(): List<DBFile> = withContext(Dispatchers.Default) {
        val prefs = Glance.context.getSharedPreferences(GLANCE_DB_CACHE, Context.MODE_PRIVATE)
        val cachedDatabases = prefs.getString(GLANCE_CACHED_DATABASES, null)
        if (cachedDatabases != null) {
            val listType = object : TypeToken<ArrayList<DBFile>>(){}.type
            val dbList: List<DBFile> = Gson().fromJson(cachedDatabases, listType)
            dbList
        } else {
            emptyList()
        }
    }

    /**
     * Scan all db files of the current app. Including internal storage and external storage.
     * Use Flow to emits the db files once find one.
     * @return Flow object to collect and get each db file.
     */
    suspend fun scanAllDBFiles() = dbScanner.scanAllDBFiles()

    /**
     * Save the latest db list into cache.
     */
    suspend fun cacheDbFiles(dbList: List<DBFile>) = withContext(Dispatchers.Default) {
        val editor = Glance.context.getSharedPreferences(GLANCE_DB_CACHE, Context.MODE_PRIVATE).edit()
        editor.putString(GLANCE_CACHED_DATABASES, Gson().toJson(dbList))
        editor.apply()
    }

}