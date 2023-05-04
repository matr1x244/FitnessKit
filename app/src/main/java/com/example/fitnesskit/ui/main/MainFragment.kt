package com.example.fitnesskit.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesskit.databinding.FragmentMainBinding
import com.example.fitnesskit.ui.main.rv_training.AdapterTrainingRV
import com.example.fitnesskit.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainFragment : Fragment() {

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
    ): View {
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
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgress.observe(viewLifecycleOwner) { inProgress ->
            animationListLesson()
            binding.rvViewListTraining.isVisible = !inProgress
            binding.progressBar.isVisible = inProgress
        }
    }

    private fun animationListLesson() {
        ObjectAnimator.ofFloat(binding.rvViewListTraining, View.ALPHA, 0.1f, 1.0f)
            .setDuration(1000)
            .start()
    }

    private fun loadNewTraining() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.reposListTraining.collect {
                adapterTraining.setData(it.lessons, it.trainers)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}