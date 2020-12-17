package com.roomdbb.roomdbb.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.roomdbb.roomdbb.R
import com.roomdbb.roomdbb.userviewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        userViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val adapter= ListAdapter()
        view.rvUserData.adapter=adapter
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.menu_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteAll(){
        val builder= androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Delete All")
        builder.setMessage("Delete All User ?")
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setPositiveButton("Yes"){_,_ ->
            userViewModel.deleteAll()
            Toast.makeText(requireContext(),"Delete All Users", Toast.LENGTH_LONG).show()
        }
        builder.create().show()
    }
}