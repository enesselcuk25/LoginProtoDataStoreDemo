package com.enesselcuk.loginprotodatastore.uı.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.enesselcuk.loginprotodatastore.data.UserLogin
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(loginReposImpl: LoginReposImpl) : ViewModel() {

    val readMail = loginReposImpl.readLoginPreference().map {
        UserLogin(it)
    }.asLiveData()


}