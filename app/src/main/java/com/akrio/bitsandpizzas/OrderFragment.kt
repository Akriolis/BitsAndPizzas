package com.akrio.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    private lateinit var toolBar: MaterialToolbar
    private lateinit var fab: FloatingActionButton
    private lateinit var pizzaGroup: RadioGroup
    private lateinit var parmesan: Chip
    private lateinit var chiliOil: Chip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        toolBar = view.findViewById(R.id.toolbar)
        val orderActivity = activity
        (orderActivity as AppCompatActivity).setSupportActionBar(toolBar)
        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            pizzaGroup = view.findViewById(R.id.pizza_group)
            val pizzaType = pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1){
                val toastText = getString(R.string.toast_text)
                Toast.makeText(activity, toastText, Toast.LENGTH_LONG).show()
            } else{
                var radioText = when (pizzaType){
                    R.id.radio_diavolo -> getString(R.string.diavolo_pizza)
                    R.id.radio_funghi -> getString(R.string.funghi_pizza)
                    else -> getString(R.string.funghi_pizza)
                }
                parmesan = view.findViewById(R.id.parmesan)
                radioText += if (parmesan.isChecked) ", extra ${getString(R.string.parmesan)}" else ""
                chiliOil = view.findViewById(R.id.chili_oil)
                radioText += if (chiliOil.isChecked) ", extra ${getString(R.string.chili_oil)}" else ""
                Snackbar.make(fab, radioText, Snackbar.LENGTH_LONG).setAction("Undo"){
                    Toast.makeText(activity,"Pizzas unordered",Toast.LENGTH_SHORT).show()
                }.show()


            }
        }

        return view
    }

}