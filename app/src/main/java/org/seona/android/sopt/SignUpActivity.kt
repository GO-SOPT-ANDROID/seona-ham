package org.seona.android.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() { //하위 액티비티
    private lateinit var binding: ActivitySignUpBinding //나중에 변수 초기화?

    override fun onCreate(savedInstanceState: Bundle?) { //화면 가로모드 세로모드 전환 떄 oncreate 함수를 다시 호출하는데 이 때 데이터 유지하기 위함?
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater) //xml에 표기된 레이아웃들을 메모리에 객체화시키는 행동이다.
        setContentView(binding.root) //레이아웃을 보여주기 위함

        binding.buttonGoBack.setOnClickListener {
            val goToLoginActivity = Intent(applicationContext, LoginActivity::class.java) //applicationcontext 인터페이스
            startActivity(goToLoginActivity)
        }

        binding.buttonSignup.setOnClickListener {
            if (binding.etSingupId.text.length !in 6..10) {
                Snackbar.make(
                    binding.root, //해당 화면에 노출시키겠다는 것을 명령하기 위함인가용? 어디에. 라는 것을 지정?
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

            val loginIntent = Intent(this@SignUpActivity, LoginActivity::class.java) //intent activity 통신 원활하게 할 수 있게 도와주는 친구
            loginIntent.putExtra("id", binding.etSingupId.text.toString()) //로그인 액티비티에 데이터 전달
            loginIntent.putExtra("pw", binding.etSignupPw.text.toString())
            loginIntent.putExtra("name", binding.etSignupName.text.toString())
            loginIntent.putExtra("mbti", binding.etSignupMbti.text.toString())
            setResult(RESULT_OK, loginIntent)
            finish() //로그인 액티비티가 이미 있는 상태, signup 액티비티에서 또 다시 login activity를 실행시키면 안된다! 충돌난다
        }
    }
}

