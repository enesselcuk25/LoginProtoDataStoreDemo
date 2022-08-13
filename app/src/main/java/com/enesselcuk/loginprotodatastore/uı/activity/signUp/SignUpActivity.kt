package com.enesselcuk.loginprotodatastore.uı.activity.signUp


import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseActivity
import com.enesselcuk.loginprotodatastore.databinding.ActivitySignUpBinding
import com.enesselcuk.loginprotodatastore.util.collect
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
                collect(viewModel.readUser, ::uiState)
            }
        }
    }

    private fun uiState(uiState: UiStateSignUp) {
        if (uiState.isSuccess == true) {
            val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            with(binding.textMessage) {
                visibility = View.VISIBLE
                text = uiState.isError
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