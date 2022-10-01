package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDecimal: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        if (tvInput.text == "0")
            tvInput.text = ""
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastDecimal = false
        lastNumeric = false
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDecimal || tvInput.text == "0") {
            tvInput.append(".")
            lastNumeric = false
            lastDecimal = true
        }
    }

    fun DoubleZero(view: View) {

        if (lastNumeric) {
            tvInput.append((view as Button).text)
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())) {
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }

    private fun removeZero(result : String) : String{
        var value = result
        if(result.contains("0"))
            value = result.substring(0, result.length-2)
            return value
    }

    fun isEqual(view: View){
        if(lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                  val splitValue =  tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }
                else if (tvValue.contains("*")){
                    val splitValue =  tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }
                else  if (tvValue.contains("/")){
                    val splitValue =  tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
                else  if (tvValue.contains("+")){
                    val splitValue =  tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }

    }

}