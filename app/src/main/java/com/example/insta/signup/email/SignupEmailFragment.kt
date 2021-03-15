package com.example.insta.signup.email

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.insta.R
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentSignupEmailBinding
import com.example.insta.signup.email.models.EmailSearch
import com.example.insta.signup.name.NameActivity
import java.util.regex.Pattern

class SignupEmailFragment : BaseFragment<FragmentSignupEmailBinding>(
    FragmentSignupEmailBinding::bind,
    R.layout.fragment_signup_email
), EmailFragmentView {
    private var userEmail: String? = null
    private var pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS
    private var check: Boolean = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.signupFrameEmailBtn.setOnClickListener {

            if (pattern.matcher(binding.fragmentEmailEdit.text.toString()).matches()) {
                binding.emailTextfield.isErrorEnabled = false
                userEmail = binding.fragmentEmailEdit.text.toString()
                showLoadingDialog(requireContext())
                EmailService(this).tryGetEmail(userEmail!!)

            } else if (!pattern.matcher(binding.fragmentEmailEdit.text.toString()).matches()) {
                binding.emailTextfield.isErrorEnabled = true
                binding.emailTextfield.error = "올바른 이메일 주소를 입력하세요."
                check = false
            }
        }

        binding.fragmentEmailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                /*if (s != null) {
                    if (s.isNotEmpty()) {
                        if (!pattern.matcher(binding.fragmentEmailEdit.text.toString()).matches()) {
                            binding.emailTextfield.isErrorEnabled = true
                            binding.emailTextfield.error = "올바른 이메일 주소를 입력하세요."
                            check = false
                        }else
                        {
                            binding.emailTextfield.isErrorEnabled = false
                            check = true
                        }
                    }
                }*/
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    binding.signupFrameEmailBtn.isEnabled = true
                }
                if (s.toString().isEmpty()) {
                    binding.emailTextfield.error = null
                    binding.signupFrameEmailBtn.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onGetEmailSuccess(response: EmailSearch) {
        response.message.let {
            Log.d("확인", it)
        }
        dismissLoadingDialog()

        if (response.code == 1000) {
            val intent = Intent(context, NameActivity::class.java)
            intent.putExtra("email", binding.fragmentEmailEdit.text.toString())
            intent.putExtra("phone", "010-9999-9999")
            startActivity(intent)
        }

    }

    override fun onGetEmailFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인", message)
    }
}