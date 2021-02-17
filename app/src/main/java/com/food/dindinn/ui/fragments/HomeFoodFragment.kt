package com.food.dindinn.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
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
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class HomeFoodFragment : Fragment(R.layout.food_layout_fragment), MavericksView,
    FoodRecyclerAdapter.FoodSelectionInterface {

    private val mainFoodViewModel: FoodViewModel by activityViewModel()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var floatActionButton: FloatingActionButton
    private lateinit var cartCountTv: TextView
    private lateinit var carouselView: CarouselView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        with(view) {
            cartCountTv = findViewById(R.id.cartCountTv)
            floatActionButton = findViewById(R.id.floatingActionButton)
            foodRecyclerView = findViewById(R.id.foodRecyclerView)
            carouselView = findViewById(R.id.carouselView)

        }
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        floatActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFoodFragment_to_cartFragment)
        }


        mainFoodViewModel.getFood()
    }

    private fun showFood(food: List<Food>) {
        foodRecyclerView.adapter = FoodRecyclerAdapter(requireContext(), food, this)
    }


    override fun invalidate() {
        withState(mainFoodViewModel) { state ->
            when (state.food) {
                is Success -> {
                    state.food.invoke()?.let {
                        showFood(it)
                        showCarouselView(it)
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

    private fun showCarouselView(food: List<Food>) {

        val imageListener: ImageListener =
            ImageListener { position, imageView ->
                // You can use Glide or Picasso here

                imageView.setImageResource(R.drawable.placeholder)
            }
        carouselView.setImageListener(imageListener)
        carouselView.pageCount = food.size
    }

    override fun addToCart(food: Food) {
        mainFoodViewModel.addToCart(food)
    }
}