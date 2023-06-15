package com.docotel.ikhsansyahrizal.second.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.docotel.ikhsansyahrizal.second.data.api.retrofit.NewsApiService
import com.docotel.ikhsansyahrizal.second.data.repository.local.LocalRepositoryImp
import com.docotel.ikhsansyahrizal.second.data.repository.local.LocalRepository
import com.docotel.ikhsansyahrizal.second.data.repository.remote.RemoteRepository
import com.docotel.ikhsansyahrizal.second.data.repository.remote.RemoteRepositoryImp
import com.docotel.ikhsansyahrizal.second.datasource.local.LocalDataSource
import com.docotel.ikhsansyahrizal.second.datasource.local.LocalDataSourceImp
import com.docotel.ikhsansyahrizal.second.datasource.remote.RemoteDataSource
import com.docotel.ikhsansyahrizal.second.datasource.remote.RemoteDataSourceImp
import com.docotel.ikhsansyahrizal.second.helper.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(newsApiService: NewsApiService): RemoteDataSource {
        return RemoteDataSourceImp(newsApiService)
    }


    @Provides
    @Singleton
    fun provideRemoteRepository(remoteDataSource: RemoteDataSource): RemoteRepository {
        return RemoteRepositoryImp(remoteDataSource)

    }


    @Provides
    @Singleton
    fun provideLocalDataSource(sharedPreferences: SharedPreferences): LocalDataSource {
        return LocalDataSourceImp(sharedPreferences)
    }


    @Provides
    @Singleton
    fun provideLocalRepository(localDataSource: LocalDataSource): LocalRepository {
        return LocalRepositoryImp(localDataSource)
    }


}