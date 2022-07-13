package com.example.rpncalculator.Stack

import java.util.Stack
import com.example.rpncalculator.Operators.*
import java.lang.Exception

class Stack() {
    private var myStack: Stack<Double>

    private var ADD_SYMBOL: String = "+"
    private var SUBTRACT_SYMBOL: String = "-"
    private var MULTIPLY_SYMBOL: String = "*"
    private var DIVIDE_SYMBOL: String = "/"
    private var POWER_SYMBOL: String = "^"
    private var SQRT_SYMBOL: String = "√"

    init {
        this.myStack = Stack()
    }

    fun pushValue(value: Double){
        this.myStack.push(value)
    }

    fun getMyStack() : Stack<Double> {
        return this.myStack
    }

    fun operate(symbol: String, decimalPlaces : Int){
        val expression: Expression
        when (symbol){
            ADD_SYMBOL -> if(myStack.count() >= 2){
                expression = Add(myStack.pop(), myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            SUBTRACT_SYMBOL -> if(myStack.count() >= 2){
                expression = Substract(myStack.pop(), myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            MULTIPLY_SYMBOL -> if(myStack.count() >= 2){
                expression = Multiply(myStack.pop(), myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            DIVIDE_SYMBOL -> if(myStack.count() >= 2) {
                expression = Divide(myStack.pop(), myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            POWER_SYMBOL -> if(myStack.count() >= 2){
                expression = Power(myStack.pop(), myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            SQRT_SYMBOL -> if(myStack.count() >= 1){
                expression = Sqrt(myStack.pop(), decimalPlaces)
            } else {
                throw Exception("Too few arguments!")
            }
            else -> throw Exception("Not valid operator!")
        }
        myStack.push(expression.compute())
    }

}