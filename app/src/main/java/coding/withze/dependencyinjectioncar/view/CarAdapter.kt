package coding.withze.dependencyinjectioncar.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coding.withze.dependencyinjectioncar.databinding.ItemCarBinding
import coding.withze.dependencyinjectioncar.model.ResponseDataCarItem
import com.bumptech.glide.Glide

class CarAdapter(var listcar : List<ResponseDataCarItem>): RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var onDelete : ((ResponseDataCarItem)->Unit)? = null
    var onDetail : ((ResponseDataCarItem)->Unit)? = null

    class ViewHolder(var binding : ItemCarBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.ViewHolder {
        var view = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.nameCar.text = listcar!![position].name
        holder.binding.categoryCar.text = listcar!![position].category
        holder.binding.priceCar.text = listcar!![position].price.toString()
        Glide.with(holder.itemView.context).load(listcar!![position].image).into(holder.binding.imgCar)

        holder.binding.deleteCar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val bun = Bundle()
                bun.putString("id", listcar[position].id.toString())
            }
        })

    }

    override fun getItemCount(): Int {

        return listcar.size

    }
}