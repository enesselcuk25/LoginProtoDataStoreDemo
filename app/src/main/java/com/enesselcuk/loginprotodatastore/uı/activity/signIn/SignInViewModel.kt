package com.enesselcuk.loginprotodatastore.uı.activity.signIn

import android.app.Application
import androidx.lifecycle.*
import com.enesselcuk.loginprotodatastore.R
import com.enesselcuk.loginprotodatastore.data.UserLogin
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    loginReposImpl: LoginReposImpl,
    private val application: Application
) :
    ViewModel() {

    private val _readUser: MutableStateFlow<UiStateSignIn> =
        MutableStateFlow(UiStateSignIn(isSuccess = false))
    val readUser: StateFlow<UiStateSignIn> = _readUser

    val userLogin = loginReposImpl.readLoginPreference().map { loginPreferences ->
        UserLogin(loginPreferences)
    }.asLiveData()

    fun buttonClick(email: String, password: String) {
        userLogin.value?.login?.let {
            when {
                it.email == email && it.password == password -> {
                    _readUser.update { uiState ->
                        uiState.copy(isSuccess = true)
                    }
                }
                email.isEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.email_cont),
                            isSuccess = false
                        )
                    }
                }
                password.isEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.password_cont),
                            isSuccess = false
                        )
                    }
                }
                else -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.not_register),
                            isSuccess = false
                        )
                    }
                }
            }
        }
    }


}
