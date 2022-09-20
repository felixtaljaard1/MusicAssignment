package com.example.musicmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicmvvm.data.entities.classic.ResultClassicItem

    @Database(entities = [ResultClassicItem::class], version = 1, exportSchema = true)
    abstract class ClassicAppDatabase : RoomDatabase(){
        abstract fun classicDAO(): ClassicDAO

        companion object{
            @Volatile private var INSTANCE: ClassicAppDatabase?= null

            fun getClassicDatabase(context: Context) : ClassicAppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
                }

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context, ClassicAppDatabase::class.java, "ClassicDataBase1")
                    .fallbackToDestructiveMigration()
                    .build()
        }

    }