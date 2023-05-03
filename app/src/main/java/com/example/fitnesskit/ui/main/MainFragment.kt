package com.example.fitnesskit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesskit.databinding.FragmentMainBinding
import com.example.fitnesskit.ui.main.rv_training.AdapterTrainingRV
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainFragment() : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel(named("main_view_model"))
    private val adapterTraining = AdapterTrainingRV()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewsShowListTraining()
        loadNewTraining()
    }

    private fun viewsShowListTraining() {
        viewModel.onShowList()
        binding.rvViewListTraining.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvViewListTraining.adapter = adapterTraining
    }

    private fun loadNewTraining() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.repos.collect() {
                adapterTraining.setData(it.lessons, it.trainers)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}