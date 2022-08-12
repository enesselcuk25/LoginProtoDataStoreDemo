package com.enesselcuk.loginprotodatastore.uı.activity.signIn

import androidx.lifecycle.*
import com.enesselcuk.loginprotodatastore.data.UserLogin
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(loginReposImpl: LoginReposImpl) :
    ViewModel() {

    val userLogin = loginReposImpl.readLoginPreference().map { loginPreferences ->
        UserLogin(loginPreferences)
    }.asLiveData()



}
