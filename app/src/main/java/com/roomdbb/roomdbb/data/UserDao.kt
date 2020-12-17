package com.roomdbb.roomdbb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.roomdbb.roomdbb.model.User

@Dao
interface UserDao {
    // if data is repate then it will not add in DB thats why user belows Line
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}