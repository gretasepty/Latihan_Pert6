package binar.greta.latihan_pert6.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDB : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE: NoteDB? = null
        fun getInstance(context: Context): NoteDB? {
            if (INSTANCE == null) {
                synchronized(NoteDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDB::class.java, "Note.db"
                    ).build()
                }

            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}