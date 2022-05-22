package binar.greta.latihan_pert6.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import binar.greta.latihan_pert6.R
import binar.greta.latihan_pert6.adapter.UserManager
import binar.greta.latihan_pert6.roomUser.User
import binar.greta.latihan_pert6.roomUser.UserDB
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    lateinit var userManager: UserManager
    private var dbuser : UserDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbuser = UserDB.getInstance(requireContext())
        userManager = UserManager(requireContext())

        btn_register.setOnClickListener {
            if (edt_usernameReg.text.isNotEmpty() &&
                edt_emailReg.text.isNotEmpty() &&
                edt_passwordReg.text.isNotEmpty() &&
                edt_UlangPasswordReg.text.isNotEmpty()){

                if(edt_passwordReg.text.toString() != edt_UlangPasswordReg.text.toString()){
                    Toast.makeText(requireContext(), "Konfirmasi Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }else{
                    saveRegister()
                }
            }else{
                Toast.makeText(requireContext(), "DATA BELUM TERISI", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveRegister(){
        val username = edt_usernameReg.text.toString()
        val email = edt_emailReg.text.toString()
        val password = edt_passwordReg.text.toString()

        GlobalScope.launch {
            userManager.saveData(username, email, password)
        }

        GlobalScope.async {
            val perintah = dbuser?.userDao()?.insertUser(User(null, username, email, password))

            activity?.runOnUiThread {
                if (perintah != 0.toLong()){
                    Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(requireView()).navigate(R.id.regisKeLogin)
                }else{
                    Toast.makeText(requireContext(), "Gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}