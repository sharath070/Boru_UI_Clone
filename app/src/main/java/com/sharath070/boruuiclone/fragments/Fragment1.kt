package com.sharath070.boruuiclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sharath070.boruuiclone.adapters.RecyclerItems
import com.sharath070.boruuiclone.databinding.Fragment1Binding
import com.sharath070.boruuiclone.model.ProductList
import com.sharath070.boruuiclone.model.Products
import com.sharath070.boruuiclone.utils.UtilConstants
import java.util.Locale

class Fragment1 : Fragment() {

    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }


    private lateinit var recyclerAdapter: RecyclerItems
    private lateinit var viewModel: UtilConstants

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[UtilConstants::class.java]

        recyclerAdapter = RecyclerItems(requireContext(), ProductList.ProductList, viewModel)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = recyclerAdapter


        viewModel.itemCount.observe(viewLifecycleOwner) {
            if (it > 0){
                binding.cvCheckout.visibility = View.VISIBLE
                binding.tvTotalNumOfItems.text = "${it.toString()} Products"
            }
            else{
                binding.cvCheckout.visibility = View.GONE
            }
        }

        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.tvTotalPrice.text = "₹ ${it.toString()}"
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}