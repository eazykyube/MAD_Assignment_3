package com.example.android.lasttorture.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "breed_table")
data class BreedDbModel(
        @PrimaryKey @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "type") val type: String
): Parcelable