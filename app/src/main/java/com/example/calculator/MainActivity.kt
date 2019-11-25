package com.example.calculator

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    BasicOperationsFragment.OnBasicFragmentInteractionListener,
    ScientificOptionsFragment.OnScientificFragmentInteractionListener {

    lateinit var resultView: TextView
    var expressionString: String = ""

    lateinit var changeModeButton: Button

    private enum class CalculatorMode {BASIC, SCIENTIFIC}
    private enum class CalculatorState {BASIC, SCIENTIFIC}

    private var currentMode: CalculatorMode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        actionBar?.hide()
        if(savedInstanceState != null)
        {
            resultView.setText(savedInstanceState.getString("Result"))
        }
        if((resources.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            && (BuildConfig.FLAVOR == "full"))
        {
            changeModeButton = findViewById(R.id.change_mode_button)
            changeModeButton.setOnClickListener{changeMode()}
            currentMode = CalculatorMode.BASIC;
        }
        resultView = findViewById(R.id.result_view)


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString("Result", resultView.text.toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onBasicFragmentInteraction(view: View)
    {
        if(resultView.text == "0") resultView.text=""
        when(view.id) {
            R.id.digit_0 -> resultView.text = resultView.text.toString().plus("0")
            R.id.digit_1 -> resultView.text = resultView.text.toString().plus("1")
            R.id.digit_2 -> resultView.text = resultView.text.toString().plus("2")
            R.id.digit_3 -> resultView.text = resultView.text.toString().plus("3")
            R.id.digit_4 -> resultView.text = resultView.text.toString().plus("4")
            R.id.digit_5 -> resultView.text = resultView.text.toString().plus("5")
            R.id.digit_6 -> resultView.text = resultView.text.toString().plus("6")
            R.id.digit_7 -> resultView.text = resultView.text.toString().plus("7")
            R.id.digit_8 -> resultView.text = resultView.text.toString().plus("8")
            R.id.digit_9 -> resultView.text = resultView.text.toString().plus("9")
            R.id.clear -> resultView.text = ""
            }
        calculate()

    }


    fun calculate() {
    }


    override fun onScientificFragmentInteraction(view: View)
    {
        calculate()

    }

    private fun changeMode() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (currentMode == CalculatorMode.BASIC){
            val scientificFragment = ScientificOptionsFragment()
            fragmentTransaction.replace(R.id.fragment_container, scientificFragment).commit()
            currentMode = CalculatorMode.SCIENTIFIC
        }
        else{
            val basicFragment = BasicOperationsFragment()
            fragmentTransaction.replace(R.id.fragment_container, basicFragment).commit()
            currentMode = CalculatorMode.BASIC
        }
    }


}
