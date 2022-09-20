package com.example.musicmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicmvvm.data.entities.classic.ResultClassicItem
import com.example.musicmvvm.data.entities.pop.ResultPopItem

@Database(entities = [ResultPopItem::class], version = 1, exportSchema = true)
abstract class PopAppDatabase : RoomDatabase(){
    abstract fun popDAO(): PopDAO

    companion object{
        @Volatile private var INSTANCE: PopAppDatabase?= null

        fun getPopDatabase(context: Context) : PopAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PopAppDatabase::class.java, "PopDataBase2")
                .fallbackToDestructiveMigration()
                .build()
    }

}