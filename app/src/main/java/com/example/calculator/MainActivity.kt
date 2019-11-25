package com.example.calculator

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity(),
    BasicOperationsFragment.OnBasicFragmentInteractionListener,
    ScientificOptionsFragment.OnScientificFragmentInteractionListener {

    lateinit var resultView: TextView
    private enum class CalculatorMode {BASIC, SCIENTIFIC, BOTH}
    private enum class CalculatorState {INPUT, RESULT, EVALUATION}
    private enum class EvaluationMode {NONE, SUM, MULTIPLICATION, DIVISION, SUBSTRACTION}

    private var currentMode: CalculatorMode? = CalculatorMode.BASIC
    private var currentState: CalculatorState = CalculatorState.INPUT
    private var evaluationMode: EvaluationMode = EvaluationMode.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        actionBar?.hide()
        resultView = findViewById(R.id.result_view)

        if(savedInstanceState != null) {
            resultView.text = savedInstanceState.getString("Result")}

        if((resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            && (BuildConfig.FLAVOR == "full"))
        {
            val changeModeButton: Button = findViewById(R.id.change_mode_button)
            changeModeButton.setOnClickListener{changeMode()}
            if(savedInstanceState == null)
            {
                val basicFragment = BasicOperationsFragment()
                supportFragmentManager.beginTransaction().add(R.id.fragment_container, basicFragment).commit()

            }
        }


    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("Result", resultView.text.toString())
        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState)
    }
    private var result: Double = 0.0
    private var temp: Double = 0.0

    override fun onBasicFragmentInteraction(view: View)
    {
        if(resultView.text == "") resultView.text="0"
        when(view.id) {
            R.id.digit_0 -> onInputDigit('0')
            R.id.digit_1 -> onInputDigit('1')
            R.id.digit_2 -> onInputDigit('2')
            R.id.digit_3 -> onInputDigit('3')
            R.id.digit_4 -> onInputDigit('4')
            R.id.digit_5 -> onInputDigit('5')
            R.id.digit_6 -> onInputDigit('6')
            R.id.digit_7 -> onInputDigit('7')
            R.id.digit_8 -> onInputDigit('8')
            R.id.digit_9 -> onInputDigit('9')
            R.id.clear -> resultView.text = ""
            R.id.decimal_point -> {
                resultView.text = resultView.text.toString().plus(".")
            }
            R.id.reminder -> {
                result = resultView.text.toString().toDouble() / 100
                resultView.text = result.toString()
            }
            R.id.eq_sign -> {
                evaluate()
                temp = 0.0
                currentState = CalculatorState.RESULT
            }
            R.id.plus -> {
                evaluate()
                evaluationMode = EvaluationMode.SUM
                temp = resultView.text.toString().toDouble()
                currentState = CalculatorState.RESULT
            }
            R.id.minus -> {
                evaluate()
                evaluationMode = EvaluationMode.SUBSTRACTION
                temp = resultView.text.toString().toDouble()
                currentState = CalculatorState.RESULT
            }
            R.id.product -> {
                evaluate()
                evaluationMode = EvaluationMode.MULTIPLICATION
                temp = resultView.text.toString().toDouble()
                currentState = CalculatorState.RESULT
            }
            R.id.devision -> {
                evaluate()
                evaluationMode = EvaluationMode.DIVISION
                temp = resultView.text.toString().toDouble()
                currentState = CalculatorState.RESULT
            }
        }
    }

    private fun onInputDigit(digit:Char){
        if(resultView.text == "0") resultView.text=""
        if(currentState == CalculatorState.RESULT)
        {
            resultView.text=""
            currentState = CalculatorState.INPUT
        }
        resultView.text = resultView.text.toString().plus(digit)

    }

    private fun evaluate(){
        result = resultView.text.toString().toDouble()
        when(evaluationMode){
            EvaluationMode.SUM -> result += temp
            EvaluationMode.MULTIPLICATION -> result *= temp
            EvaluationMode.SUBSTRACTION -> result = temp - result
            EvaluationMode.DIVISION -> result = temp/result
        }
        resultView.text = result.toString()
        evaluationMode = EvaluationMode.NONE
    }


    @SuppressLint("SetTextI18n")
    override fun onScientificFragmentInteraction(view: View)
    {
        if(resultView.text == "") resultView.text="0"
        result = resultView.text.toString().toDouble()
        evaluate()
        when(view.id) {
            R.id.log -> result = log10(result)
            R.id.ln ->  result = ln(result)
            R.id.exp ->  result = exp(result)
            R.id.sin ->  result = sin(result)
            R.id.cos ->  result = cos(result)
            R.id.tan ->  result = tan(result)
            R.id.ctg ->  result = tan(result)
            R.id.sqrt ->  result = sqrt(result)
            R.id.factorial ->  result = (result)
            R.id.x_sq -> result *= result
            R.id.x_deg ->  result *= result
        }
        resultView.text= "%.8f".format(result)
        currentState = CalculatorState.RESULT
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
