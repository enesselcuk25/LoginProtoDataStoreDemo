package com.enesselcuk.loginprotodatastore.uı.activity.signIn


import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseActivity
import com.enesselcuk.loginprotodatastore.databinding.ActivitySigninBinding
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

            btnLogin.setOnClickListener {
                val userEmail = userEmailEditText.text.toString()
                val userPassword = userPasswordEditText.text.toString()

                viewModel.userLogin.observe(this@SignInActivity) {
                    it.login?.let { loginPref ->
                        if (userEmail == loginPref.email && userPassword == loginPref.password) {
                            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignInActivity,
                                "you not have register",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
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
