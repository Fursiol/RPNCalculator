package com.example.rpncalculator.Operators

import kotlin.math.roundToInt

class Add(first: Double, second: Double, decimalPlaces : Int) : Expression{
    private var firstValue : Double = first
    private var secondValue : Double = second
    private var decPlac : Int = decimalPlaces

    override fun compute() : Double{
        val solution = this.firstValue + this.secondValue
        var result = 1.0
        if (decPlac == 1) {
            result = (solution * 10.0).roundToInt() / 10.0
        }
        if (decPlac == 2) {
            result = (solution * 100.0).roundToInt() / 100.0
        }
        if (decPlac == 3) {
            result = (solution * 1000.0).roundToInt() / 1000.0
        }
        if (decPlac == 4) {
            result = (solution * 10000.0).roundToInt() / 10000.0
        }
        if (decPlac == 5) {
            result = (solution * 100000.0).roundToInt() / 100000.0
        }
        return result
    }
}