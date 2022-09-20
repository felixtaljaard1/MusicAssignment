package com.example.musicmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicmvvm.data.entities.pop.ResultPopItem

@Dao
interface PopDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<ResultPopItem>)

    @Query("SELECT * FROM Pop_Result")
    fun getAllPop() : LiveData<List<ResultPopItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(pop: ResultPopItem)

    @Query("SELECT * FROM Pop_Result WHERE artistId= :id")
    fun getPop(id: Int) : LiveData<ResultPopItem>
}