import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.food.dindinn.R
import com.food.domain.datamodels.Food


class CartRecyclerAdapter(
    private val context: Context,
    private val foods: List<Food>,
    private val cartInterface: CartInterface
) :
    RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerAdapterViewHolder>() {

    class CartRecyclerAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecyclerAdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.foods_item_layout, parent, false)
        return CartRecyclerAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: CartRecyclerAdapterViewHolder, position: Int) {
        val foodMeal = foods[position]
        bind(holder.itemView, foodMeal)
    }

    private fun bind(itemView: View, food: Food) {
        lateinit var removeImage: ImageView
        lateinit var foodImage: ImageView
        lateinit var foodNmeTv: TextView
        lateinit var foodPrice: TextView
        with(itemView) {
            removeImage = findViewById(com.food.dindinn.R.id.removeImg)
            foodImage = findViewById(com.food.dindinn.R.id.foodImage)
            foodNmeTv = findViewById(com.food.dindinn.R.id.foodName)
            foodPrice = findViewById(com.food.dindinn.R.id.priceTv)
        }
        foodNmeTv.text = food.name
        foodPrice.text = "${food.price} USD"
        removeImage.setOnClickListener {
            cartInterface.removeFromCart(food)
        }

    }

    interface CartInterface {
        fun removeFromCart(food: Food)
    }
}