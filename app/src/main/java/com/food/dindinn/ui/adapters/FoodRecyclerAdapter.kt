package com.food.dindinn.ui.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.food.dindinn.R
import com.food.domain.datamodels.Food


class FoodRecyclerAdapter(private val context: Context, private val food: List<Food>) :
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

    }
}