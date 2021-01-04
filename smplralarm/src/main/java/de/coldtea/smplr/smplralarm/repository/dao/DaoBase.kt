package de.coldtea.smplr.smplralarm.repository.dao

import androidx.room.*

interface DaoBase<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)

}

@Transaction
suspend inline fun <reified T> DaoBase<T>.insertOrUpdate(item: T) {
    if (insert(item) != -1L) return
    update(item)
}