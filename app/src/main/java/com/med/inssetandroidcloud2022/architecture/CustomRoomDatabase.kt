package com.med.inssetandroidcloud2022.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.med.inssetandroidcloud2022.randomUser.dao.RandomUserDao
import com.med.inssetandroidcloud2022.randomUser.model.RandomUserRoom

@Database(
    entities = [
        RandomUserRoom::class
    ],
    version = 3,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun randomUserDao() : RandomUserDao
}