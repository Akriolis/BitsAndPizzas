package com.akrio.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.akrio.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        val view = binding.root

        val orderActivity = activity
        (orderActivity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {

            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1){
                val toastText = getString(R.string.toast_text)
                Toast.makeText(activity, toastText, Toast.LENGTH_LONG).show()
            } else{
                var radioText = when (pizzaType){
                    R.id.radio_diavolo -> getString(R.string.diavolo_pizza)
                    R.id.radio_funghi -> getString(R.string.funghi_pizza)
                    else -> getString(R.string.funghi_pizza)
                }

                radioText += if (binding.parmesan.isChecked) ", extra ${getString(R.string.parmesan)}" else ""

                radioText += if (binding.chiliOil.isChecked) ", extra ${getString(R.string.chili_oil)}" else ""
                Snackbar.make(binding.fab, radioText, Snackbar.LENGTH_LONG).setAction("Undo"){
                    Toast.makeText(activity,"Pizzas unordered",Toast.LENGTH_SHORT).show()
                }.show()


            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}