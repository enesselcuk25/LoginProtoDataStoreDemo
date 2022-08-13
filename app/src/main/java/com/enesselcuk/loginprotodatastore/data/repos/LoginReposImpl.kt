package com.enesselcuk.loginprotodatastore.data.repos


import androidx.datastore.core.DataStore
import com.enesselcuk.loginprotodatastore.LoginPreferences
import com.enesselcuk.loginprotodatastore.repos.LoginRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException

class LoginReposImpl(private val dataStore: DataStore<LoginPreferences>) : LoginRepos {

    override fun readLoginPreference(
    ): Flow<LoginPreferences> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(LoginPreferences.getDefaultInstance())
            } else {
                throw  exception
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun saveToDatabase(nameSurname: String, email: String, password: String) {
        dataStore.updateData { loginPreferences ->
            loginPreferences.toBuilder().setNameLastName(nameSurname).setEmail(email)
                .setPassword(password).build()
        }
    }
}