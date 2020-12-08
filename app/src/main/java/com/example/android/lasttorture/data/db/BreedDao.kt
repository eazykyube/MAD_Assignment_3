package com.example.android.lasttorture.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {

    @Query("SELECT * from breed_table WHERE type = :type")
    fun getBreeds(type: String): LiveData<List<BreedDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(breedDbModel: BreedDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breedDbModel: List<BreedDbModel>)

    @Query("DELETE FROM breed_table")
    fun deleteAll()

}