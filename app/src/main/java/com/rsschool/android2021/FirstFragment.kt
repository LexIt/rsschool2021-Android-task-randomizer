package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var minValue: TextView? = null
    private var maxValue: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            minValue = view.findViewById(R.id.min_value)
            maxValue = view.findViewById(R.id.max_value)

            if(minValue?.text.isNullOrEmpty() || maxValue?.text.isNullOrEmpty() ) {
                Toast.makeText(context, "Empty values not allowed!", Toast.LENGTH_LONG).show()
            }
            else{
                val min = Integer.parseInt(minValue?.text.toString())
                val max = Integer.parseInt(maxValue?.text.toString())

                if(max < min){
                    Toast.makeText(context, "MAX value must be above MIN value", Toast.LENGTH_LONG).show()
                }
                else {
                    val transaction =  requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.container, SecondFragment.newInstance(min, max))
                    transaction.disallowAddToBackStack()
                    transaction.commit()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        minValue = view?.findViewById(R.id.min_value)
        maxValue = view?.findViewById(R.id.max_value)

        outState.putString("MIN_VALUE", minValue?.text.toString())
        outState.putString("MAX_VALUE", maxValue?.text.toString())

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let{
            minValue = view?.findViewById(R.id.min_value)
            maxValue = view?.findViewById(R.id.max_value)

            minValue?.text = it.getString("MIN_VALUE")
            maxValue?.text = it.getString("MAX_VALUE")
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}