package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.adapter

import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kolsanovafit.feature.training.list.R
import com.kolsanovafit.feature.training.list.databinding.WorkoutItemBinding
import com.kolsanovafit.feature.training.list.domain.model.Workout
import com.kolsanovafit.feature.training.list.domain.model.WorkoutType
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.WorkoutListFragmentDirections


fun workoutAdapterDelegate() = adapterDelegateViewBinding<Workout, Workout, WorkoutItemBinding>(
    { layoutInflater, root -> WorkoutItemBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.run {

            titleText.text = item.title
            typeIcon.setImageResource(item.type.toDrawable())
            backgroundImage.setImageResource(item.type.toBack())
            descriptionText.isVisible = item.description.isNotBlank()
            descriptionText.text = item.description
            traineeDuration.text = item.duration


            root.setOnClickListener { view ->
                val action = WorkoutListFragmentDirections.actionListToDetails(
                    trainingId = item.id,
                    description = item.description
                )
                view.findNavController().navigate(action)
            }
        }
    }
}

fun WorkoutType.toDrawable(): Int = when (this) {
    WorkoutType.TRAINING -> R.drawable.type_1_run
    WorkoutType.LIVE     -> R.drawable.type_2_live
    WorkoutType.COMPLEX  -> R.drawable.type_3_compex
    WorkoutType.UNKNOWN  -> R.drawable.type_4_default
}

fun WorkoutType.toBack(): Int = when (this) {
    WorkoutType.TRAINING -> R.drawable.img_back_1
    WorkoutType.LIVE     -> R.drawable.img_back_2
    WorkoutType.COMPLEX  -> R.drawable.img_back_3
    WorkoutType.UNKNOWN  -> R.drawable.img_back_3
}