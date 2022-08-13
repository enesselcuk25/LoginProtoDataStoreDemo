package com.enesselcuk.loginprotodatastore.uı.activity.signIn


import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseActivity
import com.enesselcuk.loginprotodatastore.databinding.ActivitySigninBinding
import com.enesselcuk.loginprotodatastore.util.collect
import com.enesselcuk.loginprotodatastore.uı.activity.home.HomeActivity
import com.enesselcuk.loginprotodatastore.uı.activity.signUp.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity :
    BaseActivity<ActivitySigninBinding>(ActivitySigninBinding::inflate) {
    private val viewModel: SignInViewModel by viewModels()

    override fun define() {
        with(binding) {
            textAccountClick.setOnClickListener {
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }

            viewModel.userLogin.observe(this@SignInActivity) {}

            btnLogin.setOnClickListener {
                val userEmail = userEmailEditText.text.toString()
                val userPassword = userPasswordEditText.text.toString()

                collect(viewModel.readUser, ::uiState)
                viewModel.buttonClick(userEmail, userPassword)

            }
        }
    }

    private fun uiState(uiStateSignIn: UiStateSignIn) {
        if (uiStateSignIn.isSuccess == true) {
            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            with(binding.textMessage) {
                visibility = View.VISIBLE
                text = uiStateSignIn.isError
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
