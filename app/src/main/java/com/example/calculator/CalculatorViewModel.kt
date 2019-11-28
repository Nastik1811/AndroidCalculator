package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlin.math.*

class CalculatorViewModel : ViewModel(){
    enum class EvaluationMode {NONE, SUM, MULTIPLICATION, DIVISION, SUBTRACTION}

    var result: Double
    var temp: Double
    var evaluationMode: EvaluationMode

    init {
        result = 0.0
        temp = 0.0
        evaluationMode = EvaluationMode.NONE
    }

    fun evaluate(){
        when(evaluationMode){
            EvaluationMode.SUM -> result += temp
            EvaluationMode.MULTIPLICATION -> result *= temp
            EvaluationMode.SUBTRACTION -> result = temp - result
            EvaluationMode.DIVISION -> result = temp/result
        }
        evaluationMode = EvaluationMode.NONE
    }

    fun changeMode(mode: String){
        evaluationMode = when(mode){
            "SUM" -> EvaluationMode.SUM
            "MULTIPLICATION" -> EvaluationMode.MULTIPLICATION
            "SUBTRACTION" -> EvaluationMode.SUBTRACTION
            "DIVISION" -> EvaluationMode.DIVISION
            else -> EvaluationMode.NONE
        }
    }


    fun reset(){
        result = 0.0
        temp = 0.0
        evaluationMode=EvaluationMode.NONE
    }

    fun applyFunction(function: String)
    {
        when(function) {
            "log10" -> if (result > 0) result = log10(result)
            "ln"->  if (result > 0) result = ln(result)
            "exp" ->  result = exp(result)
            "sin" ->  result = sin(result)
            "cos" ->  result = cos(result)
            "tan" ->  result = tan(result)
            "ctg" ->  result = 1/tan(result)
            "sqrt" ->  result = sqrt(result)
            "backward" ->  if (result != 0.0) result = 1/result
            "x_sq" -> result *= result
            "module" ->  result = abs(result)
            "reminder" -> result /= 100
            "pi" -> result = PI
        }

    }


    override fun toString(): String {
        val temp = result.toString().split('.')
        if(temp[1] == "0") {
            return temp[0]
        }
        else if(temp[1].length > 7)
        {
            return "%.7f".format(result)
        }
        else return result.toString()
    }

}
