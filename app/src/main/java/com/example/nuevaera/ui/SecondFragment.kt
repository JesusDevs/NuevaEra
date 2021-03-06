package com.example.nuevaera.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nuevaera.R
import com.example.nuevaera.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ProductViewModel by activityViewModels()
    var idProduct: Int = 0
    var imagen: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            idProduct = it.getInt("id",0)
            imagen = it.getString("image","")

        }

    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductDetail(idProduct).observe(viewLifecycleOwner,{
            it?.let {

                Log.d("id2", id.toString())

                binding.nameEd2.setText(it.name)
                binding.descriptionEd2.setText(it.description)
                binding.lastPriceEd.setText(it.lastPrice)
                binding.precioEd2.setText(it.price)

                Glide.with(binding.imageView2).load(it.image).fitCenter().into(binding.imageView2)


            }
        })




        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}