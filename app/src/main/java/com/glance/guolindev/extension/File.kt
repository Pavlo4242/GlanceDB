 
package com.glance.guolindev.extension

import com.glance.guolindev.logic.model.DBFile
import java.io.File
import java.io.FileReader
import java.lang.Exception

/**
 * File extension methods.
 * @author guolin
 * @since 2020/8/24
 */

/**
 * Check the file represented by DBFile exists or not.
 */
fun DBFile.exists() = File(path).exists()

/**
 * Check this file is valid SQLite db file or not.
 */
fun File.isValidDBFile() = try {
    val reader = FileReader(this)
    val buffer = CharArray(16)
    reader.read(buffer, 0, 16)
    val str = String(buffer)
    reader.close()
    str == "SQLite format 3\u0000"
} catch (e: Exception) {
    e.printStackTrace()
    false
}

/**
 * Check the file represented by DBFile is valid SQLite db file or not.
 */
fun DBFile.isValidDBFile() = try {
    val file = File(path)
    file.isValidDBFile()
} catch (e: Exception) {
    e.printStackTrace()
    false
}