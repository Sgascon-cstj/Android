package com.example.premiereapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var nbTries = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val nbrPicker = findViewById<NumberPicker>(R.id.nbrPicker)
        val txvMessage = findViewById<TextView>(R.id.txtPlayerName)
        val btnValidate = findViewById<Button>(R.id.btnCheck)
        val txvTries = findViewById<TextView>(R.id.txvMessage)

        val theGoodNumber = (0 .. 100).random()

        txvMessage.text = getString(R.string.msgGame, intent.getStringExtra(PLAYER_NAME_EXTRA))
        //Logic
        nbrPicker.minValue = 0
        nbrPicker.maxValue = 100

        btnValidate.setOnClickListener {
            nbTries ++
 /*           if (nbrPicker.value == theGoodNumber)
            {
                Snackbar.make(btnValidate, getString(R.string.msgWin,nbTries.toString()), Snackbar.LENGTH_LONG).show()
                txvTries.text = getString(R.string.msgWin,nbTries.toString())
            }else if (nbrPicker.value > theGoodNumber)
            {
                Snackbar.make(btnValidate, "Le nombre recherché est plus petit", Snackbar.LENGTH_LONG).show()
                txvTries.text = applicationContext.resources.getQuantityString(R.plurals.msgTries,nbTries,nbTries)
            }else
            {
                Snackbar.make(btnValidate, "Le nombre recherché est plus grand", Snackbar.LENGTH_LONG).show()
                txvTries.text = applicationContext.resources.getQuantityString(R.plurals.msgTries,nbTries,nbTries)

            }
*/
            when{
                nbrPicker.value == theGoodNumber ->{
                    Snackbar.make(btnValidate, getString(R.string.msgWin,nbTries.toString()), Snackbar.LENGTH_LONG).show()
                    txvTries.text = getString(R.string.msgWin,nbTries.toString())
                    finish()
                }
                nbrPicker.value > theGoodNumber ->{
                    Snackbar.make(btnValidate, "Le nombre recherché est plus petit", Snackbar.LENGTH_LONG).show()
                    txvTries.text = applicationContext.resources.getQuantityString(R.plurals.msgTries,nbTries,nbTries)
                }
                nbrPicker.value < theGoodNumber ->{
                    Snackbar.make(btnValidate, "Le nombre recherché est plus grand", Snackbar.LENGTH_LONG).show()
                    txvTries.text = applicationContext.resources.getQuantityString(R.plurals.msgTries,nbTries,nbTries)
                }
             }
        }
    }
    //La partie statique de notre classe
    companion object {
         const val PLAYER_NAME_EXTRA = "PLAYER_NAME"
        fun newIntent(context: Context, playerName: String): Intent {
            val intent = Intent(context,GameActivity::class.java)
            //Dictionary string, object
            intent.putExtra(PLAYER_NAME_EXTRA, playerName)

            return intent
        }

    }
}