package com.enesselcuk.loginprotodatastore.uı.activity.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val loginReposImpl: LoginReposImpl) :
    ViewModel() {

    fun saveToUser(nameSurname: String, email: String, password: String) {
        viewModelScope.launch {
            loginReposImpl.saveToDatabase(nameSurname, email, password)
        }
    }

}