package com.food.dindinn.ui.fragments

import CartRecyclerAdapter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.food.dindinn.R
import com.food.domain.datamodels.Food
import com.food.viewmodels.FoodViewModel

class CartFragment : Fragment(R.layout.cart_fragment_layout), MavericksView,
    CartRecyclerAdapter.CartInterface {

    private val mainFoodViewModel: FoodViewModel by activityViewModel()
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var totalTv: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        with(view) {
            cartRecyclerView = findViewById(R.id.cartRecyclerView)
            cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            totalTv = findViewById(R.id.totalPriceTv)

        }
    }

    override fun invalidate() {
        withState(mainFoodViewModel) { state ->
            showFoodInCart(state.cartsFood)
            showTotalPrice(state.cartTotalPrice)
        }
    }

    private fun showTotalPrice(cartTotalPrice: Double) {
        totalTv.text="$cartTotalPrice USD"
    }

    private fun showFoodInCart(cartsFood: List<Food>) {
        cartRecyclerView.adapter = CartRecyclerAdapter(requireContext(), cartsFood, this)
    }

    override fun removeFromCart(food: Food) {
        mainFoodViewModel.removeFoodFromCart(food)
    }

}