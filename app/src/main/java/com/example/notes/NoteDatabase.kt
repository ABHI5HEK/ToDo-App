package com.example.notes

import android.content.Context
import android.icu.lang.UCharacter
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Notes::class), version = 1,exportSchema = false)
public abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object{
        //singleton prevents multiple instances of database opening
        @Volatile
        private var INSTANCE: NoteDatabase?=null

        fun getDatabase(context: Context):NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
