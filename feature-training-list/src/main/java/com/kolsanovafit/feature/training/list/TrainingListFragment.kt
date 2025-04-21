package com.kolsanovafit.feature.training.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kolsanovafit.feature.training.list.databinding.FragmentTrainingListBinding

class TrainingListFragment : Fragment() {

    private var _binding: FragmentTrainingListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

    }

    private fun setupClickListeners() {
        // Например, для перехода к деталям тренировки
        binding.toDetailsButton.setOnClickListener {
            navigateToDetails("training_id_1")
        }
    }

    private fun navigateToDetails(id: String) {
        val action = TrainingListFragmentDirections
            .actionListToDetails(trainingId = id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}