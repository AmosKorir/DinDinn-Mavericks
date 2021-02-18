package com.food.dindinn.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.airbnb.mvrx.*
import com.food.dindinn.R
import com.food.dindinn.ui.adapters.FoodRecyclerAdapter
import com.food.domain.datamodels.Food
import com.food.domain.datamodels.Promotion
import com.food.viewmodels.FoodViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class HomeFoodFragment : Fragment(R.layout.food_layout_fragment_nex), MavericksView,
    FoodRecyclerAdapter.FoodSelectionInterface {

    private val mainFoodViewModel: FoodViewModel by activityViewModel()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var floatActionButton: FloatingActionButton
    private lateinit var cartCountTv: TextView
    private lateinit var carouselView: CarouselView
    private lateinit var adapter: FoodRecyclerAdapter
    private lateinit var promotionProgressBar:ProgressBar
    private lateinit var foodProgressBar: ProgressBar

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
            foodProgressBar=findViewById(R.id.foodProgressBar)
            promotionProgressBar=findViewById(R.id.promotionProgressBar)

        }
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FoodRecyclerAdapter(requireContext(), ArrayList<Food>(), this)
        foodRecyclerView.adapter = adapter
        floatActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFoodFragment_to_cartFragment)
        }
        mainFoodViewModel.getFood()
    }

    private fun showFood(food: List<Food>) {
        adapter.food = food
        adapter.notifyDataSetChanged()
    }


    override fun invalidate() {
        withState(mainFoodViewModel) { state ->
            when (state.food) {
                is Success -> {
                    state.food.invoke()?.let {
                        showFood(it)
                    }
                    foodProgressBar.visibility=View.INVISIBLE
                }
                is Loading -> {
                    foodProgressBar.visibility=View.VISIBLE
                }
                is Fail -> {
                    errorMessage()
                    foodProgressBar.visibility=View.INVISIBLE
                }
                else -> {

                }
            }

            when(state.promotion){
                is Success -> {
                    state.promotion.invoke()?.let {
                        showCarouselView(it)
                        promotionProgressBar.visibility=View.INVISIBLE
                    }
                }
                is Loading -> {
                    promotionProgressBar.visibility=View.VISIBLE
                }
                is Fail -> {
                    errorMessage()
                 promotionProgressBar.visibility=View.INVISIBLE
                }
                else ->{

                }
            }

            showCartCount(state.cartsFood.size)
        }
    }

    private fun errorMessage() {
        Toast.makeText(requireContext(),"Error encountered",Toast.LENGTH_SHORT).show()
    }

    private fun showCartCount(size: Int) {
        cartCountTv.text = size.toString()
    }

    private fun showCarouselView(food: List<Promotion>) {
        val imageListener: ImageListener =
            ImageListener { position, imageView ->
                imageView.load(food[position].image)
            }
        carouselView.setImageListener(imageListener)
        carouselView.pageCount = food.size
    }

    override fun addToCart(food: Food) {
        mainFoodViewModel.addToCart(food)
    }
}