package com.food.dindinn.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.food.dindinn.R
import com.food.dindinn.ui.adapters.FoodRecyclerAdapter
import com.food.domain.datamodels.Food
import com.food.viewmodels.FoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFoodFragment : Fragment(R.layout.food_layout_fragment), MavericksView {

    private val mainFoodViewModel: FoodViewModel by fragmentViewModel()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var floatActionButton: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        floatActionButton = view.findViewById(R.id.floatingActionButton)

        foodRecyclerView = view.findViewById(R.id.foodRecyclerView)
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        floatActionButton.setOnClickListener {
            mainFoodViewModel.getFood()
        }

		mainFoodViewModel.getFood()
    }

    private fun showFood(food: List<Food>) {
        Toast.makeText(requireContext(), food.size.toString(), Toast.LENGTH_SHORT).show()
        foodRecyclerView.adapter = FoodRecyclerAdapter(requireContext(), food)
    }


    override fun invalidate() {
        withState(mainFoodViewModel) { state ->
			showFood(state.food)
        }
    }
}