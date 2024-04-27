package com.ekremkocak.healthapp.ui.statistics

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ekremkocak.healthapp.R
import com.ekremkocak.healthapp.data.model.WeeklyFitnessModel
import com.ekremkocak.healthapp.databinding.FragmentHomeBinding
import com.ekremkocak.healthapp.databinding.FragmentStatisticsBinding
import com.ekremkocak.healthapp.viewmodel.home.HomeViewModel
import com.ekremkocak.healthapp.viewmodel.statistics.StatisticsViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineDataSet

class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private val fitnessViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fitnessViewModel.getWeeklyFitnessData(requireContext()).observe(viewLifecycleOwner, Observer { WeeklyFitness->
            loadChart(WeeklyFitness)
        })
        super.onViewCreated(view, savedInstanceState)
    }


    private fun loadChart(WeeklyFitness: WeeklyFitnessModel) {
        binding.progressChart.description.isEnabled = false
        binding.progressChart.setTouchEnabled(false)
        binding.progressChart.setDrawGridBackground(false)

        val caloriesDataSet = BarDataSet(mutableListOf(), "Calories")
        val stepsDataSet = BarDataSet(mutableListOf(), "Steps")
        //stepsDataSet.color = ContextCompat.getColor(rootView.context, R.color.calories)
        val distanceDataSet = LineDataSet(mutableListOf(), "Distance")
        //MOCK
        for(i in 0 until 10){
            val caloriesEntry = BarEntry(i.toFloat(), i*10f)
            val stepsEntry = BarEntry(i.toFloat(), i*20f)
            val distanceEntry = BarEntry(i.toFloat(), i*30f)

            caloriesDataSet.addEntry(caloriesEntry)
            stepsDataSet.addEntry(stepsEntry)
            distanceDataSet.addEntry(distanceEntry)
        }

        WeeklyFitness.dailyFitnessList.forEachIndexed { index, fitnessData ->
            val caloriesEntry = BarEntry(index.toFloat(), fitnessData.caloriesBurned.toFloat())
            val stepsEntry = BarEntry(index.toFloat(), fitnessData.stepCount.toFloat())
            val distanceEntry = BarEntry(index.toFloat(), fitnessData.distance)

            caloriesDataSet.addEntry(caloriesEntry)
            stepsDataSet.addEntry(stepsEntry)
            distanceDataSet.addEntry(distanceEntry)
        }

        val data = BarData()
        data.addDataSet(caloriesDataSet)
        //data.addDataSet(stepsDataSet)
       // data.addDataSet(distanceDataSet)

        binding.progressChart.data = data

        // Set X-axis properties
        val xAxis = binding.progressChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.labelCount = 7
        //xAxis.valueFormatter = DayAxisValueFormatter(binding.progressChart)

        // Set Y-axis properties
        val yAxisLeft = binding.progressChart.axisLeft
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.isEnabled = false
        yAxisLeft.axisMinimum = 0f

        val yAxisRight = binding.progressChart.axisRight
        yAxisRight.isEnabled = true
        yAxisLeft.setDrawGridLines(true)

        val barData = binding.progressChart.barData
        barData.barWidth = 0.6f

        binding.progressChart.legend.isEnabled = true
        binding.progressChart.setDrawBorders(true)
        binding.progressChart.setBorderColor(Color.WHITE)
        binding.progressChart.setBorderWidth(3f)

        binding.progressChart.notifyDataSetChanged()
        binding.progressChart.invalidate()
    }


}