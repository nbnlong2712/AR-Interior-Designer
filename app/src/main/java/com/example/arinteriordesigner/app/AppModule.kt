package com.example.arinteriordesigner.app

import android.content.Context
import androidx.room.Room
import com.example.arinteriordesigner.data.AppDatabase
import com.example.arinteriordesigner.data.dao.ProjectDao
import com.example.arinteriordesigner.data.repositoryimpl.AuthRepositoryImpl
import com.example.arinteriordesigner.data.repositoryimpl.ProjectRepositoryImpl
import com.example.arinteriordesigner.domain.repository.AuthRepository
import com.example.arinteriordesigner.domain.repository.ProjectRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    fun providesProjectDao(db: AppDatabase): ProjectDao {
        return db.projectDao()
    }

    @Provides
    @Singleton
    fun providesProjectRepo(projectDao: ProjectDao): ProjectRepository {
        return ProjectRepositoryImpl(projectDao)
    }
}