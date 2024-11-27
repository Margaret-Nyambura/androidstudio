package com.example.makaaccount

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.makaaccount.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextWatchers()

        binding.btLogin.setOnClickListener {
            if (validateRegistration()){
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)

            }

        }

    }


    fun setupTextWatchers() {
        binding.etParent.addTextChangedListener(createTextWatcher(binding.tilParent))
        binding.etStudent.addTextChangedListener(createTextWatcher(binding.tilStudent))
        binding.etId.addTextChangedListener(createTextWatcher(binding.tilId))
        binding.etClass.addTextChangedListener(createTextWatcher(binding.tilClass))
        binding.rtEmail.addTextChangedListener(createTextWatcher(binding.tilEmail))
        binding.etPassword.addTextChangedListener(createTextWatcher(binding.tilPassword))
        binding.etConfirm.addTextChangedListener(createTextWatcher(binding.tilConfirm))
    }

    fun createTextWatcher(textInputLayout: TextInputLayout): TextWatcher? = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            textInputLayout.error = null
        }

        override fun afterTextChanged(s: Editable?) {}
    }



    fun validateRegistration(): Boolean{
        clearErrors()
        var formError = false
        val firstName = binding.etParent.text.toString()
        if (firstName.isBlank()){
            formError = true
            binding.tilParent.error = "Parent name is required"
        }else if (!valid(firstName)){
            formError = true;
            binding.tilParent.error = "Parent name must contain letters only"
        }

        val lastName = binding.etStudent.text.toString()
        if (lastName.isBlank()){
            formError = true
            binding.tilStudent.error = "Student name is required"
        }else if (!valid(lastName)){
            formError = true
            binding.tilStudent.error = "Student name must contain letters only"
        }

        val userName = binding.etId.text.toString()
        if (userName.isBlank()){
            formError = true
            binding.tilId.error = "Id is required"
        }else if (userName.length !== 4){
            formError = true
            binding.tilId.error = "Id must be 4 characters long"
        }

        val etClass = binding.etClass.text.toString()
//        if (etClass.length !== 1){
//            formError = true
//            binding.tilId.error = "Class must be 1 character long"
//        }

        val email = binding.rtEmail.text.toString()
        if (email.isBlank()){
            formError = true
            "Email is required".also { binding.tilEmail.error = it }
        }else if (!email.contains("@")){
            formError = true
            binding.tilEmail.error = "Email must contain an @ character"
        }

        val passWord = binding.etPassword.text.toString()
        if (passWord.isBlank()){
            formError = true
            binding.tilPassword.error = "Confirm your password"
        }else if (!match(passWord)){
            formError = true
            binding.tilPassword.error = "Password must contain at least one special character (@ or *) and one number (0-9)"
//        }else if (passWord.length !== 8){
//            formError = true
//            binding.tilPassword.error = "Password must be at least 8 characters long"
        }

        val confirm = binding.etConfirm.text.toString()
        if (confirm!= passWord){
            formError = true
            binding.tilConfirm.error = "Password and password confirmation do not match"
        }



        return !formError


    }
    fun clearErrors(){
        binding.tilParent.error=null
        binding.tilStudent.error=null
        binding.tilId.error=null
        binding.tilEmail.error=null
        binding.tilPassword.error=null
        binding.tilConfirm.error=null

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