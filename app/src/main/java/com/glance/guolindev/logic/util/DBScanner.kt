 
package com.glance.guolindev.logic.util

import com.glance.guolindev.Glance
import com.glance.guolindev.extension.isValidDBFile
import com.glance.guolindev.logic.model.DBFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * A utility scanner to scan internal and external storage of current app. Find all db files.
 *
 * @author guolin
 * @since 2020/8/15
 */
class DBScanner {

    /**
     * Scan all db files of the current app. Including internal storage and external storage.
     * @return A db list contains all db files under the app.
     */
    suspend fun scanAllDBFiles() = withContext(Dispatchers.Default) {
        val dbList = ArrayList<DBFile>()
        val dataDir = Glance.context.filesDir.parentFile
        if (dataDir != null) {
            scanDBFilesUnderSpecificDir(dataDir, true, dbList)
        }
        val externalDataDir = Glance.context.getExternalFilesDir("")?.parentFile
        if (externalDataDir != null) {
            scanDBFilesUnderSpecificDir(externalDataDir, false, dbList)
        }
        // Prioritize Room database files
        dbList.sortWith(compareBy(
            { !it.isRoomDatabase() }, // Room DBs first
            { it.modifyTime } // Then by modify time
        ))
        dbList
    }

    /**
     * Scan all the files under specific directory recursively.
     * @param dir
     *          Base directory to scan.
     * @param internal
     *          Indicates this is internal storage or external storage. True means internal, false means external.
     * @param dbList
     *          A db list contains all db files under the specific dir.
     */
    private fun scanDBFilesUnderSpecificDir(dir: File, internal: Boolean, dbList: ArrayList<DBFile>) {
        val listFiles = dir.listFiles()
        if (listFiles != null) {
            for (file in listFiles) {
                if (file.isDirectory) {
                    scanDBFilesUnderSpecificDir(file, internal, dbList)
                } else if (file.isValidDBFile() && !file.name.contains("journal")) {
                    dbList.add(DBFile(file.name, file.path, internal, Date(file.lastModified())))
                }
            }
        }
    }
}
