package com.example.fitnesskit.ui.main.rv_training

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.databinding.RvItemListTrainingBinding
import com.example.fitnesskit.domain.data.Lesson
import com.example.fitnesskit.domain.data.Trainer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemListTrainingBinding.bind(itemView)

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale("ru"))

    fun bind(training: Lesson, trainer: Trainer) {
        val date = training.date
        val dateTime = LocalDate.parse(date, formatter)
        binding.tvDateCalendar.text = dateTime.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))

        binding.viewColor.setBackgroundColor(Color.parseColor(training.color))
        binding.tvStartTime.text = training.startTime
        binding.tvEndTime.text = training.endTime
        binding.tvNameTraining.text = training.name
        binding.tvNameTrainer.text = trainer.full_name
        binding.tvGeo.text = training.place
    }

}