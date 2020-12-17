package com.roomdbb.roomdbb.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.roomdbb.roomdbb.R
import com.roomdbb.roomdbb.model.User
import com.roomdbb.roomdbb.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
   lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        view.edtUpdateFName.setText(args.currentUser.firstName)
        view.edtUpdateLname.setText(args.currentUser.lastName)
        view.edtUpdateAge.setText(args.currentUser.age.toString())
        view.btnUpdate.setOnClickListener {
            updateItem()
        }
        // add option menu
        setHasOptionsMenu(true)
        return view
    }
    private fun updateItem(){
        val firstname= edtUpdateFName.text.toString()
        val lastName=edtUpdateLname.text.toString()
        val age=edtUpdateAge.text.toString()
        if (check(firstname,lastName,age)){
            val user= User(args.currentUser.id,firstname,lastName,age.toInt())
                mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(),"Update Successfully.",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }   else   {
            Toast.makeText(requireContext(),"Please check all the fields",Toast.LENGTH_LONG).show()
        }
    }
    fun check(firstname:String,lastname:String,age:String):Boolean{
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Delete ${args.currentUser.firstName}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Do You want to delete ${args.currentUser.firstName} ?")
        builder.create().show()
    }
}