package com.enesselcuk.loginprotodatastore.uı.activity.signUp


import android.content.Intent
import androidx.activity.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseActivity
import com.enesselcuk.loginprotodatastore.databinding.ActivitySignUpBinding
import com.enesselcuk.loginprotodatastore.uı.activity.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    private val viewModel: SignUpViewModel by viewModels()


    override fun define() {
        with(binding) {

            btnRegister.setOnClickListener {
                viewModel.saveToUser(
                    userNameAndSurnameEditText.text.toString(),
                    userEmailEditText.text.toString(),
                    userPasswordEditText.text.toString()
                )
                val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}