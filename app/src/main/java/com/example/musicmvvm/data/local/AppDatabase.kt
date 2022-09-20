package com.example.musicmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicmvvm.data.entities.rock.Result


@Database(entities = [Result::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase(){
    abstract fun rockDAO(): RockDAO

    companion object{
        @Volatile private var INSTANCE: AppDatabase?= null

        fun getDatabase(context: Context) : AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "RockDataBase1")
                .fallbackToDestructiveMigration()
                .build()
    }
}