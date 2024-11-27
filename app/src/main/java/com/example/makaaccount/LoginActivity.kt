package com.example.makaaccount

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.makaaccount.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextWatchers()

//        binding.tvLogin.setOnClickListener {
//
////            finish()
//        }
        binding.btSignup.setOnClickListener {
            if (validateLogin()){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
//            validateLogin()
        }
    }

    fun setupTextWatchers() {
        binding.etTeacher.addTextChangedListener(createTextWatcher(binding.tilTeacher))
        binding.etTscx.addTextChangedListener(createTextWatcher(binding.tilTsc))
        binding.etEmail.addTextChangedListener(createTextWatcher(binding.tilEmail))
        binding.etPwd.addTextChangedListener(createTextWatcher(binding.tilPwd))
        binding.etCon.addTextChangedListener(createTextWatcher(binding.tilCon))

    }

    fun createTextWatcher(textInputLayout: TextInputLayout): TextWatcher? = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            textInputLayout.error = null
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    fun validateLogin() : Boolean{
        clearErrors()
        var formError = false
        val userName = binding.etTeacher.text.toString()
        if (userName.isBlank()) {
            formError = true
            binding.tilTeacher.error = "Teacher's Name is required"
        }else if (!valid(userName)){
            formError = true;
            binding.tilTeacher.error = "Teacher's name must contain letters only"
        }

        val passWord = binding.etTscx.text.toString()
        if (passWord.isBlank()) {
            formError = true
            binding.tilTsc.error = "TSC Number is required"
        }else if (passWord.length !== 6){
            formError = true
            binding.tilTsc.error = "Id must be 6 characters long"
        }

        val email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            formError = true
            binding.tilEmail.error = "Email is required"
        }else if (!email.contains("@")){
            formError = true
            binding.tilEmail.error = "Email must contain an @ character"
        }

        val pwd = binding.etPwd.text.toString()
        if (pwd.isBlank()) {
            formError = true
            binding.tilPwd.error = "Password is required"
        }

        val con = binding.etEmail.text.toString()
        if (con.isBlank()) {
            formError = true
            binding.tilCon.error = "Please confirm your password"
        }

        val confirm = binding.etCon.text.toString()
        if (confirm!= pwd){
            formError = true
            binding.tilCon.error = "Password and password confirmation do not match"
        }

        return !formError


    }


    fun clearErrors(){
        binding.tilTeacher.error=null
        binding.tilTsc.error=null
        binding.tilEmail.error=null
        binding.tilPwd.error=null
        binding.tilCon.error=null

    }
    fun valid(name: String):Boolean{
        val nameRegex = Regex("[a-zA-Z\\s]+$")
        return nameRegex.matches(name)
    }
    fun match(password: String): Boolean{
        val specialChar = password.matches(Regex(".*[@*].*"))
        val hasDigit = password.matches(Regex(".*\\d.*"))
        return specialChar && hasDigit
    }

}