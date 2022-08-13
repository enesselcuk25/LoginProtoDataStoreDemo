package com.enesselcuk.loginprotodatastore.uı.activity.signUp


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.loginprotodatastore.R
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val loginReposImpl: LoginReposImpl,
    private val application: Application
) :
    ViewModel() {

    private val _readUser: MutableStateFlow<UiStateSignUp> =
        MutableStateFlow(UiStateSignUp(isSuccess = false))
    val readUser: StateFlow<UiStateSignUp> = _readUser

    fun saveToUser(nameSurname: String, email: String, password: String) {
        viewModelScope.launch {
            when {
                nameSurname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(isSuccess = true)
                    }
                    loginReposImpl.saveToDatabase(nameSurname, email, password)
                }
                nameSurname.isEmpty() && email.isEmpty() && password.isEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.name_email_password),
                            isSuccess = false
                        )
                    }
                }
                email.isEmpty() && password.isEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.email_password),
                            isSuccess = false
                        )
                    }
                }
                nameSurname.isEmpty() -> {
                    _readUser.update { uiState ->
                        uiState.copy(
                            isError = application.resources.getString(R.string.name),
                            isSuccess = false
                        )
                    }
                }
            }
        }
    }

}


