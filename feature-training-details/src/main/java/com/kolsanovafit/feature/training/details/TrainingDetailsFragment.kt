package com.kolsanovafit.feature.training.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kolsanovafit.feature.training.details.databinding.FragmentTrainingDetailsBinding
import kotlin.getValue

class TrainingDetailsFragment : Fragment() {
    private var _binding: FragmentTrainingDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: TrainingDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Получаем ID тренировки из аргументов
        val trainingId = args.trainingId

        // Используем ID для загрузки данных и обновления UI
        binding.tittle.text = "Training ID: $trainingId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}