package com.enesselcuk.loginprotodatastore.uı.activity.home


import android.content.Intent
import androidx.activity.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseActivity
import com.enesselcuk.loginprotodatastore.databinding.ActivityHomeBinding
import com.enesselcuk.loginprotodatastore.uı.activity.signIn.SignInActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    override fun define() {

        viewModel.readMail.observe(this) {
            if (it.login?.email!!.isEmpty() && it.login.password!!.isEmpty()) {
                startActivity(Intent(this@HomeActivity, SignInActivity::class.java))
                finish()
            } else {
                setContentView(binding.root)
            }
        }
    }
}
