package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kolsanovafit.feature.training.list.data.repository.WorkoutRepositoryImpl
import com.kolsanovafit.feature.training.list.databinding.FragmentWorkoutListBinding
import com.kolsanovafit.feature.training.list.domain.usecase.GetWorkoutsUseCase
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.adapter.WorkoutAdapter
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel.TrainingViewModelFactory
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel.WorkoutListViewModel
import com.kolsanovafit.feature.training.list.presentation.state.WorkoutState
import kotlinx.coroutines.launch

class WorkoutListFragment : Fragment() {

    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutListViewModel by viewModels {
        TrainingViewModelFactory(GetWorkoutsUseCase(WorkoutRepositoryImpl()))
    }

    private val adapter by lazy { WorkoutAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeWorkouts()
    }
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@WorkoutListFragment.adapter
        }
    }

    private fun observeWorkouts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.trainingList.collect { state ->
                    when (state) {
                        is WorkoutState.Loading ->
                            Log.d("WorkoutList", "Загружаем…")
                        is WorkoutState.Success -> {
                            adapter.items = state.workouts
                            Log.d("WorkoutList", "Загрузили ${state.workouts.size}")
                        }
                        is WorkoutState.Error ->
                            Log.e("WorkoutList", "Ошибка при загрузке")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}