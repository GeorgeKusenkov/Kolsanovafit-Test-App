package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.kolsanovafit.feature.training.list.domain.model.Workout

class WorkoutAdapter: AsyncListDifferDelegationAdapter<Workout>(WorkoutDiffCallback()) {
    init {
        delegatesManager.addDelegate(workoutAdapterDelegate())
    }
}

