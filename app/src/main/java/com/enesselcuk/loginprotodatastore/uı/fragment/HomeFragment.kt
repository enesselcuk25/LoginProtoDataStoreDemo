package com.enesselcuk.loginprotodatastore.uı.fragment


import androidx.fragment.app.viewModels
import com.enesselcuk.loginprotodatastore.common.BaseFragment
import com.enesselcuk.loginprotodatastore.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    override fun define() {
        viewModel.readMail.observe(viewLifecycleOwner) {
            binding.textViewMail.text = it.login?.email
            binding.textViewName.text = it.login?.nameLastName
        }
    }
}


