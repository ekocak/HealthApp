package com.ekremkocak.healthapp.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekremkocak.healthapp.data.model.DailyFitnessModel
import com.ekremkocak.healthapp.data.model.WeeklyFitnessModel
import com.ekremkocak.healthapp.data.repository.FitnessRepository
import com.ekremkocak.healthapp.data.repository.FitnessRepositoryImpl
import com.ekremkocak.healthapp.data.repository.SharedPreferencesRepository
import com.ekremkocak.healthapp.data.repository.SharedPreferencesRepositoryImpl

class HomeViewModel : ViewModel() {

    val fitnessRepo: FitnessRepository = FitnessRepositoryImpl()
    val sharedPreferencesRepo: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    fun getDailyFitnessData(context: Context): LiveData<DailyFitnessModel> {
        var dailyFitnessLiveData = fitnessRepo.getDailyFitnessData(context)
        return dailyFitnessLiveData
    }

    fun getWeeklyFitnessData(context: Context): LiveData<WeeklyFitnessModel> {
        var weeklyFitnessLiveData = fitnessRepo.getWeeklyFitnessData(context)
        return weeklyFitnessLiveData
    }

    fun saveObjectiveSteps(context: Context, objectiveSteps: Int) {
        sharedPreferencesRepo.saveObjectiveSteps(context, objectiveSteps)
    }

    fun loadObjectiveSteps(context: Context): Int {
        return sharedPreferencesRepo.loadObjectiveSteps(context)
    }

}