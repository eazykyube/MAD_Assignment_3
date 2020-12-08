package com.example.android.lasttorture.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "type_table")
data class TypeDbModel(
        @PrimaryKey @ColumnInfo(name = "name") val name: String
): Parcelable