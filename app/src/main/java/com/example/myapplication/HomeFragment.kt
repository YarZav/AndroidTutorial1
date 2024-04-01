package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.Adapters.ImageSliderAdapter
import com.example.myapplication.Adapters.PopularAdapter
import com.example.myapplication.Fragments.MenuBottomSheetFragment
import com.example.myapplication.Models.PopularModel
import com.example.myapplication.Models.SharedModel

class HomeFragment : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var imageList: ArrayList<Int>
    private lateinit var handler: Handler

    private lateinit var popularAdapter: PopularAdapter
    private lateinit var listPopular: ArrayList<PopularModel>
    private lateinit var homeRecyclerView: RecyclerView

    private lateinit var goMenuText: TextView

    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager2 = view.findViewById(R.id.imageSlider)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel :: class.java)

        listPopular = ArrayList()
        listPopular.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        listPopular.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))

        popularAdapter = PopularAdapter(requireContext(), listPopular)
        popularAdapter.setShareModel(sharedModel)

        homeRecyclerView = view.findViewById(R.id.home_RV)
        homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        homeRecyclerView.adapter = popularAdapter

        goMenuText = view.findViewById(R.id.go_menu)
        goMenuText.setOnClickListener {
            val bottomSheetMenu = MenuBottomSheetFragment()
            bottomSheetMenu.show(parentFragmentManager, "Test")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setTransformer()

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2000)
    }

    private fun init() {
        imageList = ArrayList()
        adapter = ImageSliderAdapter(requireContext(), imageList, viewPager2)
        handler = Handler(Looper.myLooper()!!)

        imageList.add(R.drawable.banner_1)
        imageList.add(R.drawable.banner_2)
        imageList.add(R.drawable.banner_3)
        imageList.add(R.drawable.banner_4)
        imageList.add(R.drawable.banner_5)
        imageList.add(R.drawable.banner_6)
        imageList.add(R.drawable.banner_7)
        imageList.add(R.drawable.banner_8)
        imageList.add(R.drawable.banner_9)
        imageList.add(R.drawable.banner_10)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }
}