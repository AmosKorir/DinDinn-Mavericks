package com.food.dindinn.ui.adapters

import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.food.dindinn.R
import com.food.domain.datamodels.Food


class FoodRecyclerAdapter(
    private val context: Context,
    private val food: List<Food>,
    private val foodSelectionInterface: FoodSelectionInterface
) :
    RecyclerView.Adapter<FoodRecyclerAdapter.FoodRecyclerAdapterViewHolder>() {

    class FoodRecyclerAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodRecyclerAdapterViewHolder {
        return FoodRecyclerAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.food_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun onBindViewHolder(holder: FoodRecyclerAdapterViewHolder, position: Int) {
        val view = holder.itemView
        val foodMeal = food[position]
        bind(view, foodMeal)
    }

    private fun bind(view: View, foodMeal: Food) {
        lateinit var foodImage: ImageView
        lateinit var foodName: TextView
        lateinit var foodDescriptionTv: TextView
        lateinit var foodPrice: TextView

        with(view) {
            foodImage = findViewById(R.id.foodImageItemTv)
            foodName = findViewById(R.id.foodNameTv)
            foodDescriptionTv = findViewById(R.id.descriptionTv)
            foodPrice = findViewById(R.id.addToCartButton)
        }

        foodImage.load(foodMeal.image)
        foodName.text = foodMeal.name
        foodPrice.text = context.getString(R.string.pricing, foodMeal.price.toString())
        foodDescriptionTv.text = foodMeal.description
        foodPrice.setOnClickListener {
            foodSelectionInterface.addToCart(foodMeal)
            rippleEffect(foodPrice, foodMeal)
        }
    }

    private fun rippleEffect(view: TextView, food: Food) {
        view.text = context.getString(R.string.added)
        val transition = view.background as TransitionDrawable
        transition.startTransition(1000)
        transition.reverseTransition(2000)
        val countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                view.text = context.getString(R.string.pricing, food.price.toString())

            }
        }
        countDownTimer.start()
    }

    interface FoodSelectionInterface {
        fun addToCart(food: Food)
    }
}