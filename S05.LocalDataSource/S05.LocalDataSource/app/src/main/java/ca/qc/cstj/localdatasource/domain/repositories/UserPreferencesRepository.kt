package ca.qc.cstj.localdatasource.domain.repositories

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ca.qc.cstj.localdatasource.core.dataStore
import ca.qc.cstj.localdatasource.domain.models.UserPreferences
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(private val context: Context) {
    object PreferencesKeys{
        val USERNAME = stringPreferencesKey("username")
        val IS_DARK_MODE_ON = booleanPreferencesKey("isDarkModeOn")
        //Pour le tp Attention ici c'est une réponse
        //val QUANTITY_ELEMENT = floatPreferencesKey("element1") x5
    }
    val userPreference = context.dataStore.data.map {
        val name = it[PreferencesKeys.USERNAME] ?: ""
        val isDarkMode = it[PreferencesKeys.IS_DARK_MODE_ON] ?: false
        return@map UserPreferences(name,isDarkMode)
    }
    suspend fun saveName(name: String){
        context.dataStore.edit {
            it[PreferencesKeys.USERNAME] = name
        }
    }
    suspend fun saveDarkMode(isDark: Boolean){
        context.dataStore.edit {
            it[PreferencesKeys.IS_DARK_MODE_ON] = isDark
        }
    }

}