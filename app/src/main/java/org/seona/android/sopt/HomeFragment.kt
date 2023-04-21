package org.seona.android.sopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import org.seona.android.sopt.adapter.MenuAdapter
import org.seona.android.sopt.adapter.TitleAdapter
import org.seona.android.sopt.data.Menu
import org.seona.android.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val mockMenuList = listOf<Menu>(
        Menu(
            image = R.drawable.odung,
            menuName = "아메리카노",
            menuPrice = 2500,
        ),
        Menu(
            image = R.drawable.odung2,
            menuName = "에스프레소",
            menuPrice = 2000,
        ),
        Menu(
            image = R.drawable.odung3,
            menuName = "카페라떼",
            menuPrice = 3500,
        ),
        Menu(
            image = R.drawable.odung4,
            menuName = "돌체라떼",
            menuPrice = 3500,
        ),
        Menu(
            image = R.drawable.odung5,
            menuName = "녹차라떼",
            menuPrice = 4500,
        ),
        Menu(
            image = R.drawable.odung6,
            menuName = "초코라떼",
            menuPrice = 4500,
        ),
        Menu(
            image = R.drawable.odung7,
            menuName = "자몽에이드",
            menuPrice = 6000,
        ),
        Menu(
            image = R.drawable.odung8,
            menuName = "레몬에이드",
            menuPrice = 6000,
        ),
        Menu(
            image = R.drawable.odung9,
            menuName = "녹차프라페",
            menuPrice = 6000,
        ),
        Menu(
            image = R.drawable.odung10,
            menuName = "딸기프라페",
            menuPrice = 6000,
        ),
        Menu(
            image = R.drawable.odung11,
            menuName = "초코프라페",
            menuPrice = 6000,
        ),
    )

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
        val menuAdapter = MenuAdapter(requireContext())
        val titleAdapter = TitleAdapter(requireContext())
        val concatAdapter = ConcatAdapter(titleAdapter, menuAdapter)
        binding.rvMenu.adapter = concatAdapter

        menuAdapter.setMenuList(mockMenuList)
        titleAdapter.setTitle("5doong cafe")
        concatAdapter.notifyDataSetChanged()
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