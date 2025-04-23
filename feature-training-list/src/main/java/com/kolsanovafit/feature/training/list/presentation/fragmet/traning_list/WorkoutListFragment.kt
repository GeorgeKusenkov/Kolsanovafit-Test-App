package com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kolsanovafit.feature.training.list.data.repository.WorkoutRepositoryImpl
import com.kolsanovafit.feature.training.list.databinding.FragmentWorkoutListBinding
import com.kolsanovafit.feature.training.list.domain.model.WorkoutType
import com.kolsanovafit.feature.training.list.domain.usecase.FilterWorkoutsUseCase
import com.kolsanovafit.feature.training.list.domain.usecase.GetWorkoutsUseCase
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.adapter.WorkoutAdapter
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel.TrainingViewModelFactory
import com.kolsanovafit.feature.training.list.presentation.fragmet.traning_list.viewmodel.WorkoutListViewModel
import com.kolsanovafit.feature.training.list.presentation.state.WorkoutState
import kotlinx.coroutines.launch
import androidx.core.view.isGone

class WorkoutListFragment : Fragment() {

    private var _binding: FragmentWorkoutListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WorkoutListViewModel by viewModels {
        TrainingViewModelFactory(
            GetWorkoutsUseCase(WorkoutRepositoryImpl()),
            FilterWorkoutsUseCase()
        )
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
        setupObservers()
        setupSearch()
        setupChips()

    }
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@WorkoutListFragment.adapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // чекаем статус
                launch {
                    viewModel.workoutState.collect { state ->
                        when (state) {
                            is WorkoutState.Loading -> {
                                binding.progressBar.isVisible = true
                                binding.emptyTextView.isVisible = false
                            }
                            is WorkoutState.Success -> {
                                binding.progressBar.isVisible = false
                            }
                            is WorkoutState.Error -> {
                                binding.progressBar.isVisible = false
                                showError(state.message)
                            }
                        }
                    }
                }

                // чекаем отфильтрованный список
                launch {
                    viewModel.filteredWorkouts.collect { workouts ->
                        adapter.items = workouts
                        binding.emptyTextView.isVisible =
                            workouts.isEmpty() && binding.progressBar.isGone
                    }
                }
            }
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateSearch(newText.orEmpty())
                return true
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupChips() {
        binding.typeChipGroup.setOnCheckedStateChangeListener { group, _ ->
            val checkedId = group.checkedChipId
            val type = when (checkedId) {
                binding.chipTraining.id -> WorkoutType.TRAINING
                binding.chipLive.id -> WorkoutType.LIVE
                binding.chipComplex.id -> WorkoutType.COMPLEX
                binding.chipAll.id -> null
                else -> null
            }
            viewModel.updateType(type)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}