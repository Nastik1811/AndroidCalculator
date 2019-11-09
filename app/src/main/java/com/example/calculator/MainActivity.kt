package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var mainPane: TextView
    lateinit var basicOperstionsFragment: BasicOperationsFragment

    lateinit var scientificOptionsFragment: ScientificOptionsFragment
    lateinit var cleanButton: Button
    lateinit var changeModeButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPane = findViewById(R.id.mainPane)
        basicOperstionsFragment = supportFragmentManager.findFragmentById(R.id.basicPane) as BasicOperationsFragment
        scientificOptionsFragment = supportFragmentManager.findFragmentById(R.id.scientificPane) as ScientificOptionsFragment

        cleanButton = findViewById(R.id.clean)
        changeModeButton = findViewById(R.id.pro_options)

        cleanButton.setOnClickListener{onButtonClick(it)}
        changeModeButton.setOnClickListener { onButtonClick(it) }


    }

    fun onButtonClick(view: View) {
        when (view.getId()) {
            R.id.pro_options -> changeMode()
            R.id.clean -> onClear()
        }
    }

    private fun changeMode() {
        scientificOptionsFragment.view?.visibility ?: View.VISIBLE
        basicOperstionsFragment.view?.visibility?: View.GONE
    }

    private fun onClear() {
        mainPane.text = ""
    }
}
