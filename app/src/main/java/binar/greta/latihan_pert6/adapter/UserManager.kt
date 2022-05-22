package binar.greta.latihan_pert6.adapter

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context : Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object{
        val USERNAME = preferencesKey<String>("USER_USERNAME")
        val EMAIL = preferencesKey<String>("USER_EMAIL")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")

    }

    suspend fun saveData(username : String, email : String, password : String){
        dataStore.edit {
            it[USERNAME] = username
            it[EMAIL] = email
            it[PASSWORD] = password
        }
    }

    val userName : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }
    val userEmail : Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }
    val passWord : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    suspend fun hapusData(){
        dataStore.edit {
            it.clear()
        }
    }


}