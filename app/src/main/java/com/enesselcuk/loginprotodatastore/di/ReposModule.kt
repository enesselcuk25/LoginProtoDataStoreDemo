package com.enesselcuk.loginprotodatastore.di

import androidx.datastore.core.DataStore
import com.enesselcuk.loginprotodatastore.LoginPreferences
import com.enesselcuk.loginprotodatastore.data.repos.LoginReposImpl
import com.enesselcuk.loginprotodatastore.repos.LoginRepos
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object ReposModule {

    @Provides
    fun providesRepository(
        dataStore: DataStore<LoginPreferences>
    ) = LoginReposImpl(dataStore)

}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun loginRepos(reposImpl: LoginReposImpl): LoginRepos
}
