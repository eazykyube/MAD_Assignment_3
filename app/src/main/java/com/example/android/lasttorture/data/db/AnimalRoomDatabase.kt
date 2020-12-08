package com.example.android.lasttorture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimalDbModel::class, TypeDbModel::class, BreedDbModel::class], version = 3)
abstract class AnimalRoomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    abstract fun typeDao() : TypeDao

    abstract fun breedDao() : BreedDao

}
