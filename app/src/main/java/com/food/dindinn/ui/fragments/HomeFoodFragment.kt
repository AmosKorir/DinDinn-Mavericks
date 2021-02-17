package com.food.dindinn.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.food.dindinn.R
import com.food.dindinn.ui.adapters.FoodRecyclerAdapter
import com.food.domain.datamodels.Food
import com.food.viewmodels.FoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFoodFragment : Fragment(R.layout.food_layout_fragment), MavericksView,
    FoodRecyclerAdapter.FoodSelectionInterface {

    private val mainFoodViewModel: FoodViewModel by activityViewModel()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var floatActionButton: FloatingActionButton
    private lateinit var cartCountTv: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        with(view) {
            cartCountTv = findViewById(R.id.cartCountTv)
        }
        floatActionButton = view.findViewById(R.id.floatingActionButton)

        foodRecyclerView = view.findViewById(R.id.foodRecyclerView)
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        floatActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFoodFragment_to_cartFragment)
        }

        mainFoodViewModel.getFood()
    }

    private fun showFood(food: List<Food>) {
        Toast.makeText(requireContext(), food.size.toString(), Toast.LENGTH_SHORT).show()
        foodRecyclerView.adapter = FoodRecyclerAdapter(requireContext(), food, this)
    }


    override fun invalidate() {
        withState(mainFoodViewModel) { state ->
            when (state.food) {
                is Success -> {
                    state.food.invoke()?.let {
                        showFood(it)
                    }
                }
                is Loading -> {

                }
                is Fail -> {

                }
                else -> {

                }
            }

            showCartCount(state.cartsFood.size)
        }
    }

    private fun showCartCount(size: Int) {
        cartCountTv.text = size.toString()
    }

    override fun addToCart(food: Food) {
        mainFoodViewModel.addToCart(food)
    }
}