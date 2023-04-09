package org.seona.android.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoBack.setOnClickListener {
            val goToLoginActivity = Intent(applicationContext, LoginActivity::class.java)
            startActivity(goToLoginActivity)
        }

        binding.buttonSignup.setOnClickListener {
            if (binding.etSingupId.text.length !in 6..10) {
                Snackbar.make(
                    binding.root,
                    "ID는 6글자 ~ 10글자 이내로 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (binding.etSignupPw.text.length !in 8..12) {
                Snackbar.make(
                    binding.root,
                    "PW는 8글자 ~ 12글자 이내로 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (binding.etSignupName.text.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "이름을 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (binding.etSignupMbti.text.length != 4) {
                Snackbar.make(
                    binding.root,
                    "MBTI를 정확히 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val loginIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
            loginIntent.putExtra("id", binding.etSingupId.text.toString())
            loginIntent.putExtra("pw", binding.etSignupPw.text.toString())
            loginIntent.putExtra("name", binding.etSignupName.text.toString())
            loginIntent.putExtra("mbti", binding.etSignupMbti.text.toString())
            setResult(RESULT_OK, loginIntent)
            finish()
        }
    }
}

