package com.example.practicingroom.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practicingroom.R
import com.example.practicingroom.model.User
import com.example.practicingroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private    val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_update, container, false)



        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateInputName.setText(args.currentUser.firstName)
        view.updateInputSurname.setText(args.currentUser.secondName)
        view.updateInputAge.setText(args.currentUser.age.toString())


        view.updateButton.setOnClickListener {
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true)


        return  view
    }


    private fun updateItem(){

        val firstName =  updateInputName.text.toString()
        val secondName = updateInputSurname.text.toString()
        val age = Integer.parseInt(updateInputAge.text.toString())

        if(inputCheck(firstName,secondName,updateInputAge.text)){

            //Create user

            val updateUser = User(args.currentUser.id, firstName, secondName, age)

            //Update

            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Input data correctly", Toast.LENGTH_SHORT).show()
        }

    }


    private fun inputCheck(firstName : String, secondName : String, age : Editable)  : Boolean{
        return !((TextUtils.isEmpty(firstName) || firstName.length>8) || (TextUtils.isEmpty(secondName) ||  secondName.length>8) || age.isEmpty())
    }

// z menu te dwie metody
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully Removed : ${args.currentUser.firstName} ", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }


        builder.setNegativeButton("No"){_, _ ->
        }

        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are You sure You want to delete ${args.currentUser.firstName} ?")
        builder.create().show()
    }
}