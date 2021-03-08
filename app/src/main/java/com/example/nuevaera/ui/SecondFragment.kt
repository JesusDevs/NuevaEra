package com.example.nuevaera.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.nuevaera.R
import com.example.nuevaera.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ProductViewModel by activityViewModels()

//pasando datos con el metodo Bundle , opcional finalmente utilize el viewModel scope
    var idProduct: Int = 0
    var imagen: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//variables con bundle
            idProduct = it.getInt("id", 0)
            imagen = it.getString("image", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner, {
            it?.let {
                viewModel.getDetailProductoById(it.id).observe(viewLifecycleOwner, {
                    with(binding) {
                        Glide.with(imagen2).load(it.image).circleCrop().into(imagen2)
                        name2.text = it.name
                        lastPriceEd.text = "Antes " + " " + it.lastPrice
                        descriptionEd.text = "Description" + " " + it.description
                        precioEd2.text = "Precio Oferta solo hoy" + it.price
                        if (it.credit) {
                            creditEd.text = " Tarjeta de Crédito  24 cuotas "
                        } else {
                            creditEd.text = "Solo Precio Contado"
                        }
                    }
                })
            }
        })

        binding.btnBuy.setOnClickListener(View.OnClickListener {
            sendMessageBuy()
            Toast.makeText(context, "Lo Contactaremos en Brevedad", Toast.LENGTH_LONG).show()

        })

        view.findViewById<Button>(R.id.btnBack).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    //método intent para enviar correo de ventas
    fun sendMessageBuy() {
        val mIntent = Intent(Intent.ACTION_SENDTO)
        //correo de ventas
        mIntent.data = Uri.parse("mailto:leon.rodriguez.ore@gmail.com")

        mIntent.putExtra(Intent.EXTRA_SUBJECT, "prueba ")
        mIntent.putExtra(Intent.EXTRA_TEXT, "")
        startActivity(mIntent)

    }
}