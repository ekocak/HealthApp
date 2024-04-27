package com.ekremkocak.healthapp.data.model

import com.ekremkocak.healthapp.data.model.DailyFitnessModel

data class WeeklyFitnessModel(
    val dailyFitnessList: List<DailyFitnessModel>
)