package com.example.calculator

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity(),
    BasicOperationsFragment.OnBasicFragmentInteractionListener,
    ScientificOptionsFragment.OnScientificFragmentInteractionListener {

    lateinit var resultView: TextView
    lateinit var viewModel: CalculatorViewModel

    private enum class CalculatorMode {BASIC, SCIENTIFIC}
    private enum class CalculatorState {NEW, CONTINUE}

    private var currentMode: CalculatorMode? = CalculatorMode.BASIC
    private var currentState: CalculatorState? = CalculatorState.NEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        actionBar?.hide()

        resultView = findViewById(R.id.result_view)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)

        if (savedInstanceState != null)
        {
            currentMode = savedInstanceState.getSerializable("Mode") as CalculatorMode?
            currentState = savedInstanceState.getSerializable("State") as CalculatorState?
            resultView.text = savedInstanceState.getString("Result")
        }
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
        outState.putSerializable("Mode", currentMode)
        outState.putSerializable("State", currentState)
        outState.putString("Result", resultView.text.toString())
        super.onSaveInstanceState(outState)
    }
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
            R.id.decimal_point -> {
                if (resultView.text.split('.').size == 1) {
                    resultView.text = resultView.text.toString().plus(".")
                }
            }
            R.id.reminder -> {
                evaluate()
                viewModel.applyFunction("reminder")
                updateResult()
            }
            R.id.eq_sign -> {
                if(currentState != CalculatorState.NEW) evaluate()
                viewModel.reset()
            }
            R.id.plus -> {
                if(currentState != CalculatorState.NEW) evaluate()
                viewModel.changeMode("SUM")
                viewModel.temp = resultView.text.toString().toDouble()
            }
            R.id.minus -> {
                if(currentState != CalculatorState.NEW) evaluate()
                viewModel.changeMode("SUBTRACTION")
                viewModel.temp = resultView.text.toString().toDouble()
            }
            R.id.product -> {
                if(currentState != CalculatorState.NEW) evaluate()
                viewModel.changeMode("MULTIPLICATION")
                viewModel.temp = resultView.text.toString().toDouble()
            }
            R.id.division -> {
                if(currentState != CalculatorState.NEW) evaluate()
                viewModel.changeMode("DIVISION")
                viewModel.temp = resultView.text.toString().toDouble()
            }
            R.id.clear -> resultView.text = resultView.text.toString().dropLast(1)
        }
    }

    private fun onInputDigit(digit:Char){
        if(resultView.text == "0") resultView.text=""
        if(currentState == CalculatorState.NEW)
        {
            resultView.text=""
            currentState = CalculatorState.CONTINUE
        }
        resultView.text = resultView.text.toString().plus(digit)
    }

    private fun evaluate(){
        viewModel.result = resultView.text.toString().toDouble()
        viewModel.evaluate()
        updateResult()
    }

    override fun onScientificFragmentInteraction(view: View)
    {
        if(resultView.text == "") resultView.text="0"
        evaluate()
        when(view.id) {
            R.id.log -> viewModel.applyFunction("log10")
            R.id.ln -> viewModel.applyFunction("ln")
            R.id.exp -> viewModel.applyFunction("exp")
            R.id.sin -> viewModel.applyFunction("sin")
            R.id.cos -> viewModel.applyFunction("cos")
            R.id.tan ->  viewModel.applyFunction("tan")
            R.id.ctg -> viewModel.applyFunction("ctg")
            R.id.sqrt -> viewModel.applyFunction("sqrt")
            R.id.backward -> viewModel.applyFunction("backward")
            R.id.x_sq -> viewModel.applyFunction("x_sq")
            R.id.module -> viewModel.applyFunction("module")
            R.id.pi_sign -> viewModel.applyFunction("pi")
        }
        updateResult()
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

    private fun updateResult()
    {
        resultView.text = viewModel.toString()
        currentState = CalculatorState.NEW
    }


}
