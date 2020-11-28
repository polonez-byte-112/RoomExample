package com.example.practicingroom.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practicingroom.R
import com.example.practicingroom.model.User
import com.example.practicingroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.addButton.setOnClickListener {
            insertUser()
        }

        return view
    }

    private fun insertUser() {

        val firstName = inputName.text.toString()
        val secondName = inputSurname.text.toString()
        val age = inputAge.text

        if(inputCheck(firstName,secondName,age)){
            val user = User(0,firstName,secondName, Integer.parseInt(age.toString()))

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "User added", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Input data correctly", Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(firstName : String, secondName : String, age : Editable)  : Boolean{
        return !((TextUtils.isEmpty(firstName) || firstName.length>8) || (TextUtils.isEmpty(secondName) ||  secondName.length>8) || age.isEmpty())
    }


}