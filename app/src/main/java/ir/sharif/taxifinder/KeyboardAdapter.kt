package ir.sharif.taxifinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class KeyboardAdapter(private val context: Context, private val images: Array<Int>, private val listener: KeyListener) : RecyclerView.Adapter<KeyboardAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.image).setImageResource(images[position])
        holder.itemView.setOnClickListener {
            listener.click(position + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.num_layout, null))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}