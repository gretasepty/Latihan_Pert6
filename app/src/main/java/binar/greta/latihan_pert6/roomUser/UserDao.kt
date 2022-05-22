package binar.greta.latihan_pert6.roomUser

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : User) : Long
}