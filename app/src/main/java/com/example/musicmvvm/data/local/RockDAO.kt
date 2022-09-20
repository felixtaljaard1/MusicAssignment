package com.example.musicmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicmvvm.data.entities.rock.Result

@Dao
interface RockDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<Result>)

    @Query("SELECT * FROM Rock_Result")
    fun getAllRock() : LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(rock: Result)

    @Query("SELECT * FROM Rock_Result WHERE artistId= :id")
    fun getRock(id: Int) : LiveData<Result>
}