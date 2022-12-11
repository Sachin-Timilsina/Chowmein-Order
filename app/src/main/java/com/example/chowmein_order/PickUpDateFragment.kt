package com.example.chowmein_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chowmein_order.databinding.FragmentPickUpDateBinding
import com.example.chowmein_order.model.OrderViewModel


class PickUpDateFragment : Fragment() {

    private var binding: FragmentPickUpDateBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickUpDateBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickUpDateFragment = this@PickUpDateFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickUpDateFragment_to_orderSummaryFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()

        findNavController().navigate(R.id.action_pickUpDateFragment_to_itemFragment)
    }
}