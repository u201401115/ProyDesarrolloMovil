import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptracking.R

class OrdersAdapter(private val orders: List<Map<String, String>>) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.item_Rfecha)
        val tvOrdenId: TextView = view.findViewById(R.id.item_Rid)
        val tvEstado: TextView = view.findViewById(R.id.item_Restado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.tvFecha.text = order["fecha_hora"] ?: "N/A"
        holder.tvOrdenId.text = order["id_orden"] ?: "N/A"
        holder.tvEstado.text = order["estado"] ?: "N/A"
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}