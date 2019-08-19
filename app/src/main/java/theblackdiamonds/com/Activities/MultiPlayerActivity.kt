package theblackdiamonds.com.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import theblackdiamonds.com.R

class MultiPlayerActivity : AppCompatActivity(), View.OnClickListener {

    private val buttons = Array<Array<Button?>>(3) { arrayOfNulls(3) }
    private var isPlayer1Turn = true
    private var roundCount: Int = 0
    private var player1Points: Int = 0
    private var player2Points: Int = 0
    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_player)

        textViewPlayer1 = findViewById(R.id.text_view_p1)
        textViewPlayer2 = findViewById(R.id.text_view_p2)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<Button>(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }

        val buttonReset = findViewById<Button>(R.id.button_reset)
        buttonReset.setOnClickListener { resetGame() }
    }

    override fun onClick(v: View) {
        if ((v as Button).text.toString() != "") {
            return
        }

        if (isPlayer1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }

        roundCount++

        if (checkForWin()) {
            if (isPlayer1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            isPlayer1Turn = !isPlayer1Turn
        }

    }

    private fun checkForWin(): Boolean {
        val field = Array<Array<String?>>(3) { arrayOfNulls(3) }

        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]?.text.toString()
            }
        }

        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }

        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }

        return if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            true
        } else field[0][2] == field[1][1]
                && field[0][2] == field[2][0]
                && field[0][2] != ""

    }

    private fun player1Wins() {
        player1Points++
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun player2Wins() {
        player2Points++
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
        updatePointsText()
        resetBoard()
    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
        resetBoard()
    }

    private fun updatePointsText() {
        textViewPlayer1!!.text = "Player 1: $player1Points"
        textViewPlayer2!!.text = "Player 2: $player2Points"
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]?.text = ""
            }
        }

        roundCount = 0
        isPlayer1Turn = true
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("player2Points", player2Points)
        outState.putBoolean("isPlayer1Turn", isPlayer1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        player2Points = savedInstanceState.getInt("player2Points")
        isPlayer1Turn = savedInstanceState.getBoolean("isPlayer1Turn")
    }
}