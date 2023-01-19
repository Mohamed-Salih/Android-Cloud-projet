package com.med.inssetandroidcloud2022.randomUser.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/** Object use for Ui */
data class RandomUserUi(
    val email: String,
    val avatarUrl: String,
): MyObjectForRecyclerView(label = email)

sealed class MyObjectForRecyclerView(label: String)

/** Object used in UI */
data class RandomUserHeader(
    val header: String
) : MyObjectForRecyclerView(label = header)

/** Object use for room */
@Entity(tableName = "random_user_table")
data class RandomUserRoom(
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for retrofit */
data class RandomUserRetrofit(
    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("avatar")
    val avatarUrl: String,
)