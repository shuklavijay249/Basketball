package com.shuklavijay249.di

import android.content.Context
import com.shuklavijay249.data.datasource.LocalDataSource
import com.shuklavijay249.data.repository.ScheduleRepositoryImpl
import com.shuklavijay249.data.repository.TeamRepositoryImpl
import com.shuklavijay249.domain.repository.ScheduleRepository
import com.shuklavijay249.domain.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource =
        LocalDataSource(context)

    @Provides
    fun provideScheduleRepository(localDataSource: LocalDataSource): ScheduleRepository =
        ScheduleRepositoryImpl(localDataSource)

    @Provides
    fun provideTeamRepository(localDataSource: LocalDataSource): TeamRepository =
        TeamRepositoryImpl(localDataSource)
}
