package com.roomdbb.roomdbb.fragments.add

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.roomdbb.roomdbb.R
import com.roomdbb.roomdbb.model.User
import com.roomdbb.roomdbb.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
class AddFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        view.btnAdd.setOnClickListener {
            insertDatatoDatabase()
        }

        return view
    }
    private fun insertDatatoDatabase(){
        val firstName= edtFirstName.text.toString()
        val lastName= edtLastName.text.toString()
        val age= edtAge.text.toString()
        if(isCheck(firstName,lastName, age)){
          val user= User(0,firstName,lastName,Integer.parseInt(age))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(),"Add Successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please Check the Fields",Toast.LENGTH_LONG).show()
        }
    }
    fun isCheck(firstName:String, lastName:String, age :String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age) )
    }

}