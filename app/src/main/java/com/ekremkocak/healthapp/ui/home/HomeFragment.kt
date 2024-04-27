package com.ekremkocak.healthapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ekremkocak.healthapp.R
import com.ekremkocak.healthapp.databinding.FragmentHomeBinding
import com.ekremkocak.healthapp.viewmodel.home.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val fitnessViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fitnessViewModel.getDailyFitnessData(requireContext()).observe(viewLifecycleOwner, Observer { DailyFitness->
            binding.stepCount.text = DailyFitness.stepCount.toString()
            binding.tvBurnedCalories.text = DailyFitness.caloriesBurned.toString()
        })

        binding.cvSteps.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_navigation_home_to_statisticsFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}