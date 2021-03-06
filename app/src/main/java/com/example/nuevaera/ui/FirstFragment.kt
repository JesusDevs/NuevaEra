package com.example.nuevaera.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nuevaera.R
import com.example.nuevaera.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductAdapter()


            binding.rvView.adapter = adapter
            binding.rvView.layoutManager = LinearLayoutManager(context)


        viewModel.productLiveDataFromDataBase.observe(viewLifecycleOwner,{
            it?.let {
                Log.d("LISTADO", "$it")
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner,{
            it?.let{
                val bundle = Bundle()
                bundle.putInt("id", it.id)
                bundle.putString("img",it.image)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
        })

    }
}