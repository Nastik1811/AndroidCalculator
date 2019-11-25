package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment




class ScientificOptionsFragment : Fragment(), View.OnClickListener {
    private var listener: OnScientificFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scientific_options, container, false)

        // TODO: Find a better solution
        (view.findViewById(R.id.cos) as Button).setOnClickListener(this)
        (view.findViewById(R.id.sin) as Button).setOnClickListener(this)
        (view.findViewById(R.id.ctg) as Button).setOnClickListener(this)
        (view.findViewById(R.id.tan) as Button).setOnClickListener(this)
        (view.findViewById(R.id.module) as Button).setOnClickListener(this)
        (view.findViewById(R.id.exp) as Button).setOnClickListener(this)
        (view.findViewById(R.id.e_sign) as Button).setOnClickListener(this)
        (view.findViewById(R.id.pi_sign) as Button).setOnClickListener(this)
        (view.findViewById(R.id.ln) as Button).setOnClickListener(this)
        (view.findViewById(R.id.log) as Button).setOnClickListener(this)
        (view.findViewById(R.id.reminder) as Button).setOnClickListener(this)
        (view.findViewById(R.id.sqrt) as Button).setOnClickListener(this)
        (view.findViewById(R.id.x_deg) as Button).setOnClickListener(this)
        (view.findViewById(R.id.x_sq) as Button).setOnClickListener(this)
        (view.findViewById(R.id.factorial) as Button).setOnClickListener(this)
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnScientificFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onClick(view: View) {
        listener?.onScientificFragmentInteraction(view)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnScientificFragmentInteractionListener {
        fun onScientificFragmentInteraction(view: View)
    }

}