package com.example.fitnesskit.ui.main.rv_training

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.R
import com.example.fitnesskit.domain.model.Lesson
import com.example.fitnesskit.domain.model.Trainer
import kotlinx.coroutines.flow.combine

class AdapterTrainingRV() : RecyclerView.Adapter<TrainingViewHolder>() {

    private var trainingList: MutableList<Lesson> = mutableListOf()
    private var trainerList: MutableList<Trainer> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListTraining: List<Lesson>, newListTrainer: List<Trainer>) {
        // по идее надо объеденить 2 списка в один и засетить.
        trainingList.clear()
        trainingList.addAll(newListTraining)
        trainerList.addAll(newListTrainer)
        trainingList.sortBy { it.date}
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_list_training, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(getItemLesson(position), getItemTrainer(position))
    }

    private fun getItemLesson(position: Int): Lesson = trainingList[position]

    private fun getItemTrainer(position: Int): Trainer = trainerList[position]

    override fun getItemCount() = trainerList.size // Длина списка который сетится
}