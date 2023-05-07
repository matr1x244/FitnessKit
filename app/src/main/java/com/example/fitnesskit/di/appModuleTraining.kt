package com.example.fitnesskit.domain

import com.example.fitnesskit.data.web.RetrofitRequestImpl
import com.example.fitnesskit.data.web.TrainingAPI
import com.example.fitnesskit.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModuleTraining = module {

    val apiUrl = "https://olimpia.fitnesskit-admin.ru/schedule/"

    single<RepositoryTraining>(named("API_training")) { RetrofitRequestImpl(get()) }
    single<TrainingAPI> { get<Retrofit>().create(TrainingAPI::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(get(named("rx_java_adapter")))
            .addConverterFactory(get(named("gson_converter")))
            .build()
    }

    factory<Converter.Factory>(named("gson_converter")) { GsonConverterFactory.create() }
    factory<CallAdapter.Factory>(named("rx_java_adapter")) { RxJava3CallAdapterFactory.create() }

    viewModel(named("main_view_model")) { MainViewModel(get(named("API_training"))) }
}