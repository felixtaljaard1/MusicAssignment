package com.example.musicmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicmvvm.data.entities.classic.ResultClassicItem

@Dao
interface ClassicDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<ResultClassicItem>)

    @Query("SELECT * FROM Classic_Result")
    fun getAllClassic() : LiveData<List<ResultClassicItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(classic: ResultClassicItem)

    @Query("SELECT * FROM Classic_Result WHERE artistId= :id")
    fun getClassic(id: Int) : LiveData<ResultClassicItem>
}