package com.example.practiceproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceproject.R
import com.example.practiceproject.data.remote.dto.User.UserItem

class UserAdapter(private val itemListener : ItemListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var memberUsers = emptyList<UserItem>()

    fun setUserData(userList : ArrayList<UserItem>){
        memberUsers = userList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutUser = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_user,parent,false)
        return UserViewHolder(layoutUser)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val memberUser = memberUsers[position]
        holder.userName.text = memberUser.name
        holder.userId.text = memberUser.id.toString()
        holder.userPhone.text = memberUser.phone
        holder.userEmail.text = memberUser.email
        holder.userUsername.text = memberUser.username
        holder.userWebsite.text = memberUser.website
        holder.itemView.setOnClickListener {
            itemListener.userCLicked(memberUser)
        }
    }

    override fun getItemCount(): Int {
        return memberUsers.size
    }

    class UserViewHolder (UserView : View) : RecyclerView.ViewHolder(UserView){

        val userName : TextView = itemView.findViewById(R.id.userName)
        val userId : TextView = itemView.findViewById(R.id.userId)
        val userPhone : TextView = itemView.findViewById(R.id.userPhone)
        val userEmail : TextView = itemView.findViewById(R.id.userEmail)
        val userUsername : TextView = itemView.findViewById(R.id.userUsername)
        val userWebsite : TextView = itemView.findViewById(R.id.userWebsite)
    }

    interface ItemListener{
        fun userCLicked(memberUser : UserItem)
    }
}