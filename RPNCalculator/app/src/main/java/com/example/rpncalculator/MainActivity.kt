package com.example.rpncalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.rpncalculator.Stack.Stack
import android.widget.Toast
import android.content.Intent
import android.graphics.Color.parseColor

class MainActivity : AppCompatActivity() {
    // Deklaracja zmiennej typu Stack
    var calculator: Stack = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val decimalPlaces = intent.getIntExtra("decimalPlaces", 1)

        // Deklaracja i powiazanie z ID przyciskow i pol tekstowych
        // Przyciki cyfr i kropki
        val zeroButton: Button = findViewById(R.id.button0)
        val oneButton: Button = findViewById(R.id.button1)
        val twoButton: Button = findViewById(R.id.button2)
        val threeButton: Button = findViewById(R.id.button3)
        val fourButton: Button = findViewById(R.id.button4)
        val fiveButton: Button = findViewById(R.id.button5)
        val sixButton: Button = findViewById(R.id.button6)
        val sevenButton: Button = findViewById(R.id.button7)
        val eightButton: Button = findViewById(R.id.button8)
        val nineButton: Button = findViewById(R.id.button9)
        val dotButton: Button = findViewById(R.id.dotButton)
        // Przyciski dzialan
        val plusButton: Button = findViewById(R.id.plus)
        val minusButton: Button = findViewById(R.id.minus)
        val multiplyButton: Button = findViewById(R.id.multiplyButton)
        val divideButton: Button = findViewById(R.id.divideButton)
        val sqrtButton: Button = findViewById(R.id.sqrtButton)
        val powerButton: Button = findViewById(R.id.powerButton)
        //Pola tekstowe
        val editTextField: TextView = findViewById(R.id.edit)
        val stackOneTextField: TextView = findViewById(R.id.stack1)
        val stackTwoTextField: TextView = findViewById(R.id.stack2)
        val stackThreeTextField: TextView = findViewById(R.id.stack3)
        val stackFourTextField: TextView = findViewById(R.id.stack4)
        // Przyciski funckji kalkulatora
        val enterButton: Button = findViewById(R.id.enter)
        val swapButton: Button = findViewById(R.id.swapButton)
        val allClearButton: Button = findViewById(R.id.allClearButton)
        val dropButton: Button = findViewById(R.id.dropButton)
        val oppositeButton: Button = findViewById(R.id.oppositeButton)
        val menuButton: Button = findViewById(R.id.menuButton)

        //Obsluga przycisku dodajacego kropke dziesietna do wartosci w polu edycji
        dotButton.setOnClickListener{
            if (editTextField.text.toString() != "" && "." !in editTextField.text.toString()) { //Nie moze byc na poczatku ani wiecej niz jedna
                var previousState: String = editTextField.text.toString()
                editTextField.setText(previousState + ".")
            }
            else if (editTextField.text.toString() == ""){ //Jesli uzytkownik probuje wstawic kropke na poczatku wyswietl komunikat
                Toast.makeText(this, "Value cannot start with a dot!", Toast.LENGTH_SHORT).show()
            }
            else if("." in editTextField.text.toString()){ //Jesli uzytkownik probuje wstawic kolejna kropke wyswietl komunikat
                Toast.makeText(this, "Value has a dot already!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga wpisywania cyfr na pole edycji
        zeroButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "0")
        }
        oneButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "1")
        }
        twoButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "2")
        }
        threeButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "3")
        }
        fourButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "4")
        }
        fiveButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "5")
        }
        sixButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "6")
        }
        sevenButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "7")
        }
        eightButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "8")
        }
        nineButton.setOnClickListener{
            var previousState: String = editTextField.text.toString()
            editTextField.setText(previousState + "9")
        }

        // Obsluga przycisku wstawiania na stos
        enterButton.setOnClickListener{
            if(editTextField.text.toString() != "") {
                var newStackEl: Double = editTextField.text.toString().toDouble() //Przechwycenie wartosci wpisanej w polu edycji na Double
                calculator.pushValue(newStackEl) // Wstawienie wartosci na stos
                editTextField.text = "" // Wyczyszczenie pola edycji
                // Wyswietlenie czterech elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                }
            }
            else{ //Jesli nie ma nic w polu edycji wyswietl komunikat
                Toast.makeText(this,"Provide the value first!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku do obliczania sumy dwóch ostatnio wprowadzonych wartosci
        plusButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) { //Sprawdzenie czy sa co najmniej dwa elementy
                calculator.operate("+", decimalPlaces) // Wykonanie sumowania
                editTextField.text = "" // Wyczyszczenie pola edycji
                // Pokazanie nowych wartosci na stosie
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                } else {
                    stackFourTextField.setText("4: ")
                }
            }
            else{ //Jesli nie ma minimum dwoch wartosci wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku do obliczania roznicy dwoch ostatnio wprowadzonych wartosci
        minusButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) { // Sprawdzenie czy sa co najmniej dwie wartosci na stosie
                calculator.operate("-", decimalPlaces) // Wykonanie odejmowania
                editTextField.text = "" // Wyczyszczenie pola edycji
                // Wyswietlenie nowych elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                } else {
                    stackFourTextField.setText("4: ")
                }
            }
            else{ // Jesli nie ma minimum dwoch elementow wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku do wykonywania mnozenia na dwoch ostatnio dodanych elementach stosu
        multiplyButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) { // Sprawdzenie czy sa co najmniej dwie wartosci na stosie
                calculator.operate("*", decimalPlaces) // Wykonanie mnozenia
                editTextField.text = "" // Wyczyszczenie pola edycji
                // Wyswietlenie nowych elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                } else {
                    stackFourTextField.setText("4: ")
                }
            }
            else{ // Jesli nie ma minimum dwoch elementow wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku wykonujacego dzielenie ostatniego przez przedostatni element
        divideButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) { // Sprawdzenie czy sa co najmniej dwie wartosci na stosie
                if (calculator.getMyStack().get(calculator.getMyStack().count() - 2) != 0.0) { // Sprawdzenie czy nie dzielomy przez 0
                    calculator.operate("/", decimalPlaces) // Wykonanie dzielenia
                    editTextField.text = "" // Wyczyszczenie pola edycji
                    // Wyswietlenie nowych elementow stosu
                    if (calculator.getMyStack().count() >= 1) {
                        stackOneTextField.setText("1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString())
                    }
                    if (calculator.getMyStack().count() >= 2) {
                        stackTwoTextField.setText("2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString())
                    } else {
                        stackTwoTextField.setText("2: ")
                    }
                    if (calculator.getMyStack().count() >= 3) {
                        stackThreeTextField.setText("3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString())
                    } else {
                        stackThreeTextField.setText("3: ")
                    }
                    if (calculator.getMyStack().count() >= 4) {
                        stackFourTextField.setText("4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString())
                    } else {
                        stackFourTextField.setText("4: ")
                    }
                }
                else{ // Jesli uzytkownik probuje podzielic przez 0 wyswietl komunikat
                    Toast.makeText(this, "Dividing by zero is forbidden!", Toast.LENGTH_SHORT).show()
                }
            }
            else{ // Jesli nie ma minimum dwoch elementow wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku podnoszacego ostatni element do potegi rownej przedostaniemu elementowi
        powerButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) { // Sprawdzenie czy sa co najmniej dwie wartosci na stosie
                calculator.operate("^", decimalPlaces) // Wykonanie potegowania
                editTextField.text = "" // Wyczyszczenie pola edycji
                // Wyswietlenie nowych elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                } else {
                    stackFourTextField.setText("4: ")
                }
            }
            else{ // Jesli nie ma minimum dwoch elementow wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        // Obsluga przycisku obliczajacego pierwiastek z ostaniej podanej na stos liczby
        sqrtButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 1) { // Sprawdzenie czy cos jest na stosie
                if (calculator.getMyStack().get(calculator.getMyStack().count() - 1) > 0.0) { // Sprawdzenie czy waartosc jest nieujemna
                    calculator.operate("√", decimalPlaces) // Wykonanie pierwsiastkowania
                    editTextField.text = "" // Wyczyszczenie pola edycji
                    // Wyswietlenie nowych elementow stosu
                    if (calculator.getMyStack().count() >= 1) {
                        stackOneTextField.setText("1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString())
                    }
                    if (calculator.getMyStack().count() >= 2) {
                        stackTwoTextField.setText("2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString())
                    } else {
                        stackTwoTextField.setText("2: ")
                    }
                    if (calculator.getMyStack().count() >= 3) {
                        stackThreeTextField.setText("3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString())
                    } else {
                        stackThreeTextField.setText("3: ")
                    }
                    if (calculator.getMyStack().count() >= 4) {
                        stackFourTextField.setText("4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString())
                    } else {
                        stackFourTextField.setText("4: ")
                    }
                }
                else{ // Jesli uzytkownik probuje wyciagnac pierwsiastek z liczby ujemnej wyswietl komunikat
                    Toast.makeText(this, "Value must be non-negative!", Toast.LENGTH_SHORT).show()
                }
            }
            else{ // Jesli nie ma minimum jendego elementu wyswietl komunikat
                Toast.makeText(this, "To few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        //Obsluga przycisku SWAP
        swapButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 2) {
                var firstValue: Double = calculator.getMyStack().pop()
                var secondValue: Double = calculator.getMyStack().pop()
                calculator.getMyStack().push(firstValue)
                calculator.getMyStack().push(secondValue)
                // Wyswietlenie nowych elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString()
                    )
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString()
                    )
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString()
                    )
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString()
                    )
                } else {
                    stackFourTextField.setText("4: ")
                }
            }
            else{
                Toast.makeText(this, "Too few arguments!", Toast.LENGTH_SHORT).show()
            }
        }
        //Obsluga przycisku DROP
        dropButton.setOnClickListener {
            if (calculator.getMyStack().count() >= 1) {
                calculator.getMyStack().pop()
                // Wyswietlenie nowych elementow stosu
                if (calculator.getMyStack().count() >= 1) {
                    stackOneTextField.setText(
                        "1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString())
                } else{
                    stackOneTextField.setText("1: ")
                }
                if (calculator.getMyStack().count() >= 2) {
                    stackTwoTextField.setText(
                        "2: " + calculator.getMyStack().get(calculator.getMyStack().count() - 2).toString())
                } else {
                    stackTwoTextField.setText("2: ")
                }
                if (calculator.getMyStack().count() >= 3) {
                    stackThreeTextField.setText(
                        "3: " + calculator.getMyStack().get(calculator.getMyStack().count() - 3).toString())
                } else {
                    stackThreeTextField.setText("3: ")
                }
                if (calculator.getMyStack().count() >= 4) {
                    stackFourTextField.setText(
                        "4: " + calculator.getMyStack().get(calculator.getMyStack().count() - 4).toString())
                } else {
                    stackFourTextField.setText("4: ")
                }
            } else {
                Toast.makeText(this, "There is nothing to drop!", Toast.LENGTH_SHORT).show()
            }
        }
        //Obsluga przycisku zmiany znaku
        oppositeButton.setOnClickListener{
            if(calculator.getMyStack().count() >= 1){
                var normalValue : Double = calculator.getMyStack().pop()
                normalValue = -normalValue
                calculator.getMyStack().push(normalValue)
                stackOneTextField.setText("1: " + calculator.getMyStack().get(calculator.getMyStack().count() - 1).toString())
            }
            else{
                Toast.makeText(this, "There is nothing to change!", Toast.LENGTH_SHORT).show()
            }
        }
        //Obsluga przycisku AllCLear
        allClearButton.setOnClickListener {
            calculator = Stack() // Wyczysczenie stosu
            // Wyczyszczenie wyswietlanych wartosci
            stackFourTextField.setText("4: ")
            stackThreeTextField.setText("3: ")
            stackTwoTextField.setText("2: ")
            stackOneTextField.setText("1: ")
        }
        //Obsluga przycisku Menu
        menuButton.setOnClickListener{
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
    }
}