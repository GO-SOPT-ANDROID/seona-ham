package org.seona.android.sopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.data.Request.RequestSigninDto
import org.seona.android.sopt.data.Response.ResponseSigninDto
import org.seona.android.sopt.data.ServicePool
import org.seona.android.sopt.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val userService = ServicePool.userSoptService

    private fun signUpSuccess() {
        Snackbar.make(
            binding.root,
            "회원가입이 완료되었습니다.",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun signInSuccess(name : String, mbti : String) {
        val profileIntent = Intent(this@LoginActivity, ProfileActivity::class.java)
        profileIntent.putExtra("name", name)
        profileIntent.putExtra("mbti", mbti)
        startActivity(profileIntent)
    }

    private fun signInFailed(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    signUpSuccess()
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
            userService.signin(with(binding) {
                RequestSigninDto(etLoginId.text.toString(), etLoginPw.text.toString())
            }).enqueue(object : retrofit2.Callback<ResponseSigninDto> {
                override fun onResponse(
                    call: Call<ResponseSigninDto>,
                    response: Response<ResponseSigninDto>
                ) {
                    if (response.isSuccessful) {
                        signInSuccess(response.body()?.data?.name ?: "", response.body()?.data?.mbti ?: "")
                    } else {
                        signInFailed(response.body()?.message?:"알 수 없는 이유로 로그인에 실패하였습니다.")
                    }
                }

                override fun onFailure(call: Call<ResponseSigninDto>, t: Throwable) {
                    Snackbar.make(
                        binding.root,
                        t.message?: "알 수 없는 이유로 로그인에 실패하였습니다.(-1)",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })
        }

        binding.buttonSignup.setOnClickListener {
            val goToSignUpActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            resultLauncher.launch(goToSignUpActivity)
        }
    }
}
