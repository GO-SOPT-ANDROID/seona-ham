package org.seona.android.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import org.seona.android.sopt.databinding.ActivityLoginBinding
import org.seona.android.sopt.databinding.ActivityProfileBinding
import org.seona.android.sopt.databinding.ActivitySignUpBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("name") ?: ""
        val mbti = intent.getStringExtra("mbti") ?: ""

        binding.tvProfileName.text="이름 : ${name}"
        binding.tvProfileMbti.text="mbti : ${mbti}"
    }
}




