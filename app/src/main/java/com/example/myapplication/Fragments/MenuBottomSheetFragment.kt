package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapters.PopularAdapter
import com.example.myapplication.Models.PopularModel
import com.example.myapplication.Models.SharedModel
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var adapter: PopularAdapter
    private lateinit var menuList: ArrayList<PopularModel>
    private lateinit var menuRV: RecyclerView
    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu_bottom_sheet, container, false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)

        menuList = ArrayList()
        menuList.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_burger, "Sandwich", "7$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_sandwich, "Momo", "9$", foodCount = 1))
        menuList.add(PopularModel(R.drawable.pop_menu_momo, "Burger", "2$", foodCount = 1))

        adapter = PopularAdapter(requireContext(), menuList)
        adapter.setShareModel(sharedModel)

        menuRV = view.findViewById(R.id.menu_RV)
        menuRV.layoutManager = LinearLayoutManager(requireContext())
        menuRV.adapter = adapter

        return view
    }
}