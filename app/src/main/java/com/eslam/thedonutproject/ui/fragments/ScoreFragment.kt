package com.eslam.thedonutproject.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.futured.donut.DonutSection
import com.eslam.thedonutproject.R
import com.eslam.thedonutproject.databinding.FragmentScoreBinding
import com.eslam.thedonutproject.domain.model.ScoreModel
import com.eslam.thedonutproject.ui.viewmodels.MainViewModel
import com.eslam.thedonutproject.utils.wrapEspressoIdlingResource
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScoreFragment : Fragment() {

     lateinit var binding:FragmentScoreBinding

     val viewModel:MainViewModel by activityViewModels()
    private var model: ScoreModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //handling live data in xml if added
        binding.lifecycleOwner = viewLifecycleOwner

        //fetchScore()

            observeScore()
            observeError()



        binding.scoreDonut.setOnClickListener {
            navigateToDetailsScreen()
        }
    }

    private fun observeError() {
        viewModel.errorevent.observe(viewLifecycleOwner){

            Toast.makeText(this.requireContext(),"$it",Toast.LENGTH_LONG).show()
        }
    }

    private fun observeScore() {
        viewModel.scoreLiveData.observe(viewLifecycleOwner){
            Log.e(null, "observeScore: $it ", )

            if (it != null)
            {
                model = it
                val section1 = DonutSection(
                    name = "section_1",
                    color = Color.parseColor("#ffffff"),
                    amount = 0f
                )

                val section2 = DonutSection(
                    name = "section_2",
                    color = Color.parseColor("#FFC107"),
                    amount = it.score!!.toFloat()

                )

                binding.scoreDonut.cap= it.maxScoreValue?.toFloat() ?: 700f

                binding.scoreValue.text = it.score.toString()
                val maxScore = "out of ${it.maxScoreValue}"
                binding.maxScore.text = maxScore
                binding.scoreDonut.submitData(listOf(section1, section2))
            }

        }
    }

    //a way to refresh the data by a pull to refresh or a button , etc
    private fun fetchScore() {
       lifecycleScope.launch {
           viewModel.getTheScore()
       }
    }

    private fun navigateToDetailsScreen()
    {
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToDetailsFragment(model))
    }


}