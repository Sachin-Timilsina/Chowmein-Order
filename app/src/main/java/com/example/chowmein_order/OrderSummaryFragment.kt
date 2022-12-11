package com.example.chowmein_order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chowmein_order.databinding.FragmentOrderSummaryBinding
import com.example.chowmein_order.model.OrderViewModel


class OrderSummaryFragment : Fragment() {

    private var binding: FragmentOrderSummaryBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentOrderSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            orderSummaryFragment = this@OrderSummaryFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun sendOrder() {
        val orderSummary = getString(
            R.string.order_details,
            sharedViewModel.item.value.toString(),
            sharedViewModel.plates.value.toString(),
            sharedViewModel.platesQuantity.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(
                Intent.EXTRA_SUBJECT,
                getString(R.string.Chowmein_order, sharedViewModel.item.value.toString())
            )
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        // Check if there's an app that can handle this intent before launching it
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            // Start a new activity with the given intent (this may open the share dialog on a
            // device if multiple apps can handle this intent)
            startActivity(intent)
        }


    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()

        findNavController().navigate(R.id.action_orderSummaryFragment_to_itemFragment)
    }
}