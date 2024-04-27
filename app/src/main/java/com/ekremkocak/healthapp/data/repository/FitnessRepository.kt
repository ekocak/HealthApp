package com.ekremkocak.healthapp.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.ekremkocak.healthapp.data.model.DailyFitnessModel
import com.ekremkocak.healthapp.data.model.WeeklyFitnessModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface FitnessRepository {
    fun getDailyFitnessData(context: Context): MutableLiveData<DailyFitnessModel>
    fun getWeeklyFitnessData(context: Context): MutableLiveData<WeeklyFitnessModel>
    fun getGoogleAccount(context: Context): GoogleSignInAccount
}