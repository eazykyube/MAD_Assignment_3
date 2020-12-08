package com.example.android.lasttorture.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TypeDao {

    @Query("SELECT * from type_table ORDER BY name")
    fun getTypes(): LiveData<List<TypeDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(typeDbModel: TypeDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(typeDbModel: List<TypeDbModel>)

    @Query("DELETE FROM type_table")
    fun deleteAll()

}