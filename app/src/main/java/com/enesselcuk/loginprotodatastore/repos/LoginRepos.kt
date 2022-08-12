package com.enesselcuk.loginprotodatastore.repos

import com.enesselcuk.loginprotodatastore.LoginPreferences
import kotlinx.coroutines.flow.Flow


interface LoginRepos {
    fun readLoginPreference(): Flow<LoginPreferences>
    suspend fun saveToDatabase(nameSurname: String, email: String, password: String)

}