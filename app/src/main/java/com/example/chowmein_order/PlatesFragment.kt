package com.example.chowmein_order


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chowmein_order.databinding.FragmentPlatesBinding
import com.example.chowmein_order.model.OrderViewModel


class PlatesFragment : Fragment() {

    private var binding: FragmentPlatesBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPlatesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            platesFragment = this@PlatesFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_platesFragment_to_platesQuantityFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()

        findNavController().navigate(R.id.action_platesFragment_to_itemFragment)
    }

}