package binar.greta.latihan_pert6.room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note : Note) : Long
    @Query("SELECT * FROM Note")
    fun getNote() : List<Note>
    @Delete
    fun deleteNote(note : Note) : Int
    @Update
    fun updateNote(note : Note) : Int

}