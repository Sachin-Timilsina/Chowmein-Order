package com.example.chowmein_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chowmein_order.databinding.FragmentPlatesQuantityBinding
import com.example.chowmein_order.model.OrderViewModel


class PlatesQuantityFragment : Fragment() {

    private var binding: FragmentPlatesQuantityBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPlatesQuantityBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            platesQuantityFragment = this@PlatesQuantityFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNextFragment() {
        findNavController().navigate(R.id.action_platesQuantityFragment_to_pickUpDateFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()

        findNavController().navigate(R.id.action_platesQuantityFragment_to_itemFragment)
    }


}