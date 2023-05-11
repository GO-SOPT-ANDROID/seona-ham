package org.seona.android.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.data.Request.RequestSignupDto
import org.seona.android.sopt.data.Response.ResponseSignupDto
import org.seona.android.sopt.data.ServicePool
import org.seona.android.sopt.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() { //하위 액티비티
    private lateinit var binding: ActivitySignUpBinding //나중에 변수 초기화?

    private var idValidationCheck = false
    private var pwValidationCheck = false
    private var nameValidationCheck = false
    private var mbtiValidationCheck = false

    private val userService = ServicePool.userSoptService

    private fun makeSnackBarMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun completeSignUp() {
        userService.signup(
            with(binding) {
                RequestSignupDto(
                    etSingupId.text.toString(),
                    etSignupPw.text.toString(),
                    etSignupName.text.toString(),
                    etSignupMbti.text.toString(),
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignupDto> {
            override fun onResponse(
                call: Call<ResponseSignupDto>,
                response: Response<ResponseSignupDto>
            ) {
                if (response.isSuccessful) {
                    val loginIntent = Intent(
                        this@SignUpActivity,
                        LoginActivity::class.java
                    ) //intent activity 통신 원활하게 할 수 있게 도와주는 친구

                    setResult(RESULT_OK, loginIntent)

                    if (!isFinishing) finish()

                } else {
                    response.body()?.message?.let {
                        makeSnackBarMessage(it)
                    } ?: "서버 통신 실패(40x)"
                }
            }

            override fun onFailure(call: Call<ResponseSignupDto>, t: Throwable) {
                t.message?.let {
                    makeSnackBarMessage(it)
                } ?: "서버통신 실패(응답값 x)"
            }
        })
    }

    private fun buttonValidationCheck() {
        binding.buttonSignup.isEnabled = idValidationCheck
                && pwValidationCheck
                && nameValidationCheck
                && mbtiValidationCheck
    }

    override fun onCreate(savedInstanceState: Bundle?) { //화면 가로모드 세로모드 전환 떄 oncreate 함수를 다시 호출하는데 이 때 데이터 유지하기 위함?
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater) //xml에 표기된 레이아웃들을 메모리에 객체화시키는 행동이다.
        setContentView(binding.root) //레이아웃을 보여주기 위함

        binding.etSingupId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                idValidationCheck = p0?.length in 6..10

                buttonValidationCheck()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignupPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pwValidationCheck = p0?.length in 8..12

                buttonValidationCheck()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignupName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameValidationCheck = p0?.length != 0

                buttonValidationCheck()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.etSignupMbti.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mbtiValidationCheck = p0?.length == 4

                buttonValidationCheck()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.buttonGoBack.setOnClickListener {
            val goToLoginActivity =
                Intent(applicationContext, LoginActivity::class.java) //applicationcontext 인터페이스
            startActivity(goToLoginActivity)
        }

        binding.buttonSignup.setOnClickListener {
            completeSignUp()
        }
    }
}

