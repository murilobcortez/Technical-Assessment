package com.example.comics.di

import com.example.comics.util.data.Constants.MOVIES_API_BASE_URL
import com.example.comics.data.datasource.remote.RemoteDataSource
import com.example.comics.data.datasource.remote.RemoteDataSourceImpl
import com.example.comics.data.datasource.remote.service.Service
import com.example.comics.data.repository.RepositoryImpl
import com.example.comics.domain.repository.Repository
import com.example.comics.domain.usecase.GetMoviesUseCase
import com.example.comics.presentation.MoviesViewModel
import com.example.comics.util.data.RetrofitFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitFactory.create(MOVIES_API_BASE_URL) }
    single { get<retrofit2.Retrofit>().create(Service::class.java) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<Repository> { RepositoryImpl(get()) }
}

val domainModule = module {
    factory { GetMoviesUseCase(get()) }
}

val presentationModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }

    viewModelOf(::MoviesViewModel)
}

val modules = module {
    includes(dataModule, domainModule, presentationModule)
}