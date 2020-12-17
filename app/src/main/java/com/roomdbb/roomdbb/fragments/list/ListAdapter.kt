package com.roomdbb.roomdbb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.roomdbb.roomdbb.R
import com.roomdbb.roomdbb.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()
    class MyViewHolder(item:View):RecyclerView.ViewHolder(item){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= userList[position]
        holder.itemView.tvId.text= currentItem.id.toString()
        holder.itemView.tvFname.text=currentItem.firstName
        holder.itemView.tvLName.text= currentItem.lastName
        holder.itemView.tvAge.text= currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action= ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
      return  userList.size
    }
    fun setData(user:List<User>){
        this.userList=user
        notifyDataSetChanged()
    }
}