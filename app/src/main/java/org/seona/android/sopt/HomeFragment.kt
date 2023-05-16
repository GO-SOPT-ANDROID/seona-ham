package org.seona.android.sopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.seona.android.sopt.adapter.UserAdapter.UserAdapter
import org.seona.android.sopt.data.Response.ResponseGetUserDto
import org.seona.android.sopt.data.ServicePool
import org.seona.android.sopt.data.User
import org.seona.android.sopt.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userService = ServicePool.userReqresService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userAdapter = UserAdapter(requireContext())
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
                            "${it.firstName} ${it.lastName}",
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
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}