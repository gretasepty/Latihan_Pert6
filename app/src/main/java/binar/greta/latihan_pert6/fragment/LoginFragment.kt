package binar.greta.latihan_pert6.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import binar.greta.latihan_pert6.R
import binar.greta.latihan_pert6.adapter.UserManager
import binar.greta.latihan_pert6.roomUser.UserDB
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    lateinit var userManager: UserManager
    private var dbuser : UserDB? = null
    lateinit var usernameLog : String
    lateinit var passwordLog : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbuser = UserDB.getInstance(requireContext())
        userManager = UserManager(requireContext())

        btn_login.setOnClickListener {
            if(edt_username.text.isNotEmpty() && edt_password.text.isNotEmpty()){  //input user dan password tidak boleh kosong
                val username = edt_username.text.toString()
                val password = edt_password.text.toString()

                if (username == usernameLog &&    //Menentukan username dan pass menggunakan shared preferences
                    password == passwordLog){
                    Navigation.findNavController(view).navigate(R.id.btn_loginKeHome)
                }else{
                    Toast.makeText(requireContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Username dan Password belum diisi", Toast.LENGTH_SHORT).show()
            }
        }

        userManager.userName.asLiveData().observe(requireActivity(),{
            usernameLog = it.toString()
        })
        userManager.passWord.asLiveData().observe(requireActivity(),{
            passwordLog = it.toString()
        })

        txt_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.btn_loginKeRegis)
        }

    }





}