package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kolsanovafit.feature.training.list.domain.model.Workout

class WorkoutDiffCallback: DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean =
        oldItem == newItem
}