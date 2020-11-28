package com.example.practicingroom.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.practicingroom.R
import com.example.practicingroom.model.User
import kotlinx.android.synthetic.main.my_row.view.*

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

     var userList  = emptyList<User>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.displayedId.text = currentItem.id.toString()
        holder.itemView.displayedName.text = currentItem.firstName
        holder.itemView.displayedSecondName.text = currentItem.secondName
        holder.itemView.displayedAge.text = currentItem.age.toString()

        // Click listener by edyowac
        holder.itemView.rowLayout.setOnClickListener {
            //podajemy user object do update fragment
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            //Navigujemy jak wczesniej tylko ze dzieki ListFragmentDirections mozemy podac cos dalej do innego fragmentu <3
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


    fun setData( user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}