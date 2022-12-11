package com.example.chowmein_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chowmein_order.databinding.FragmentItemBinding
import com.example.chowmein_order.model.FULL_PLATE
import com.example.chowmein_order.model.OrderViewModel


class ItemFragment : Fragment() {

    private var binding: FragmentItemBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentItemBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.itemFragment = this

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    fun setItemItemPrice(topping: String, toppingPrice: Int) {
        sharedViewModel.setTopping(topping)
        sharedViewModel.setToppingPrice(toppingPrice)
        //Initially No. of Plates and plate's Quantity
        sharedViewModel.setPlates(2)
        sharedViewModel.setPlatesQuantity(FULL_PLATE)
        findNavController().navigate(R.id.action_itemFragment_to_platesFragment)
    }

}