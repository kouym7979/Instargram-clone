package com.example.insta.signup.phone

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.example.insta.R
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentSignupPhoneBinding
import com.example.insta.signup.name.NameActivity
import java.util.regex.Pattern

class SignupPhoneFragment : BaseFragment<FragmentSignupPhoneBinding>(FragmentSignupPhoneBinding::bind, R.layout.fragment_signup_phone
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupFramePhoneBtn.setOnClickListener {

           if(!Pattern.matches("01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}\$",binding.fragmentPhoneEdit.text!!))
               {
                binding.textinput.error=getString(R.string.phoneerror)
               }
            else {
               startActivity(Intent(context, NameActivity::class.java))
           }
        }


        binding.fragmentPhoneEdit.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()){
                    binding.signupFramePhoneBtn.setBackgroundColor(Color.BLUE)
                    binding.signupFramePhoneBtn.isEnabled=true
                }
                if(s.toString().isEmpty()){
                    binding.signupFramePhoneBtn.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.unable))
                    binding.signupFramePhoneBtn.isEnabled=false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }
}