package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button



class BasicOperationsFragment : Fragment() {
    var result:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_operations, container, false)
    }

    fun onButtonClick(view: View) {
        when (view.getId()) {
            R.id.eq_sign -> onResult()
            else -> result + (view as Button).text
        }
    }

    private fun onResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


