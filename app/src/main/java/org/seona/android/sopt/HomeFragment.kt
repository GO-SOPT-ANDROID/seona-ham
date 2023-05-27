package org.seona.android.sopt

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.adapter.UserAdapter.UserAdapter
import org.seona.android.sopt.data.Response.ResponseGetUserDto
import org.seona.android.sopt.data.Response.ResponseSigninDto
import org.seona.android.sopt.data.ServicePool
import org.seona.android.sopt.data.User
import org.seona.android.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response
import java.net.URL

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userService = ServicePool.userReqresService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAdapter = UserAdapter(requireContext())
//        val titleAdapter = TitleAdapter(requireContext())
//        val concatAdapter = ConcatAdapter(titleAdapter, userAdapter)
        binding.rvUser.adapter = userAdapter

        userService.getUsers(1).enqueue(object : retrofit2.Callback<ResponseGetUserDto> {
            override fun onResponse(
                call: Call<ResponseGetUserDto>,
                response: Response<ResponseGetUserDto>
            ) {
                if (response.isSuccessful) {
                    userAdapter.setUserList(response.body()?.data?.map {
                        return@map User(
                            it.avatar,
                            "${it.first_name} ${it.last_name}",
                            it.email
                        )
                    } ?: emptyList())
                } else {
                    Snackbar.make(
                        binding.root,
                        "유저 조회에 실패하였습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseGetUserDto>, t: Throwable) {
                Snackbar.make(
                    binding.root,
                    t.message ?: "유저 조회에 실패하였습니다.(-1)",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}