package org.seona.android.sopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    var id = ""
    var pw = ""
    var name = ""
    var mbti = ""

    fun signUpSuccess(intent: Intent?) {
        Snackbar.make(
            binding.root,
            "회원가입이 완료되었습니다.",
            Snackbar.LENGTH_SHORT
        ).show()

        id = intent?.getStringExtra("id") ?: ""
        pw = intent?.getStringExtra("pw") ?: ""
        name = intent?.getStringExtra("name") ?: ""
        mbti = intent?.getStringExtra("mbti") ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    signUpSuccess(result.data)
                }
            }
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (binding.etLoginId.text.isEmpty() || binding.etLoginPw.text.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "계정 정보를 입력해주세요.",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (binding.etLoginId.text.toString() == id) {
                if (binding.etLoginPw.text.toString() == pw) {
                    Snackbar.make(
                        binding.root,
                        "OK!",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    val profileIntent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    profileIntent.putExtra("name", name)
                    profileIntent.putExtra("mbti", mbti)
                    startActivity(profileIntent)
                } else {
                    Snackbar.make(
                        binding.root,
                        "비밀번호가 일치하지 않습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "아이디가 일치하지 않습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        binding.buttonSignup.setOnClickListener {
            val goToSignUpActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            resultLauncher.launch(goToSignUpActivity)
        }
    }
}
