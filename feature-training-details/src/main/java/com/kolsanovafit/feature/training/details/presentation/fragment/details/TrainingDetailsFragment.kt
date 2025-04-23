package com.kolsanovafit.feature.training.details.presentation.fragment.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kolsanovafit.feature.training.details.databinding.FragmentTrainingDetailsBinding
import kotlin.getValue
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kolsanovafit.feature.training.details.data.api.RetrofitBuilder
import com.kolsanovafit.feature.training.details.data.repository.WorkoutVideoRepositoryImpl
import com.kolsanovafit.feature.training.details.domain.usecase.GetWorkoutVideoUseCase
import com.kolsanovafit.feature.training.details.presentation.state.WorkoutVideoState
import com.kolsanovafit.feature.training.details.presentation.fragment.details.viewmodel.WorkoutDetailsViewModel
import com.kolsanovafit.feature.training.details.presentation.fragment.details.viewmodel.TrainingDetailsViewModelFactory
import com.kolsanovafit.feature.training.details.presentation.mediaplayer.KolsaMediaPlayer
import kotlinx.coroutines.launch

/**
 * Фрагмент для отображения деталей тренировки с видеоматериалом.
 * Управляет ЖЦ и отображает информацию о тренировке.
 *
 * Обеспечивает работу с видео (в рамках стандартного ф-ла Media Player ¯\_(ツ)_/¯)
 */
class TrainingDetailsFragment : Fragment() {
    private var _binding: FragmentTrainingDetailsBinding? = null
    private val binding get() = _binding!!

    //Да, некрасиво. Если успею, то доберусь и добавлю нормальный DI
    private val viewModel: WorkoutDetailsViewModel by viewModels {
        TrainingDetailsViewModelFactory(
            GetWorkoutVideoUseCase(
                WorkoutVideoRepositoryImpl(
                    RetrofitBuilder.workoutApi
                )
            )
        )
    }

    private val args: TrainingDetailsFragmentArgs by navArgs()
    private lateinit var player: KolsaMediaPlayer
    private var wasPlaying = false
    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainingId = args.trainingId
        val trainingDescription = args.description

        player = KolsaMediaPlayer(requireContext())
        player.setupWithVideoView(binding.videoPlayer)

        lifecycleScope.launch {
            viewModel.getVideo(trainingId)
            viewModel.workoutVideo.collect { state ->
                when(state) {
                    is WorkoutVideoState.Success -> {
                        player.playVideo(state.workouts.link.toUri())
                    }
                    is WorkoutVideoState.Loading -> {

                    }
                    is WorkoutVideoState.Error -> {
                        Log.e("DEBUG_VIDEO", "State.Error: ${state.message}")

                    }
                }

            }
        }

        binding.tittle.text = trainingDescription
    }

    override fun onResume() {
        super.onResume()
        if (wasPlaying) {
            player.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        wasPlaying = player.isPlaying()
        currentPosition = player.getCurrentPosition()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

