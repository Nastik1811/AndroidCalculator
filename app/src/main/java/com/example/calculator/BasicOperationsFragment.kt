package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment




class BasicOperationsFragment : Fragment(), View.OnClickListener {
    private var listener: OnBasicFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_basic_options, container, false)

        // TODO: Find a better solution
        (view.findViewById(R.id.eq_sign) as Button).setOnClickListener(this)
        (view.findViewById(R.id.clear) as Button).setOnClickListener(this)
        //(view.findViewById(R.id.del) as Button).setOnClickListener(this)
        (view.findViewById(R.id.product) as Button).setOnClickListener(this)
        (view.findViewById(R.id.devision) as Button).setOnClickListener(this)
        (view.findViewById(R.id.minus) as Button).setOnClickListener(this)
        (view.findViewById(R.id.plus) as Button).setOnClickListener(this)
        (view.findViewById(R.id.decimal_point) as Button).setOnClickListener(this)
        (view.findViewById(R.id.r_bracket) as Button).setOnClickListener(this)
        (view.findViewById(R.id.l_bracket) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_0) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_1) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_2) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_3) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_4) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_5) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_6) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_7) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_8) as Button).setOnClickListener(this)
        (view.findViewById(R.id.digit_9) as Button).setOnClickListener(this)
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBasicFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onClick(view: View) {
        listener?.onBasicFragmentInteraction(view)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBasicFragmentInteractionListener {
        fun onBasicFragmentInteraction(view: View)
    }

}