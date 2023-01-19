package com.med.inssetandroidcloud2022.randomUser.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRoom

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM random_user_table")
    fun selectAll() : LiveData<List<RandomUserRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chuckNorrisRoom: RandomUserRoom)

    @Query("DELETE FROM random_user_table")
    fun deleteAll()
}