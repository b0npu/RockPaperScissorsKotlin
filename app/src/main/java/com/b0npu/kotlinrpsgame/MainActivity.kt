package com.b0npu.kotlinrpsgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    /**
     * プロパティの宣言
     *
     * レイアウトに設置したwidgetのidを格納する変数とじゃんけんで使う定数を宣言する
     * widgetのidを格納する変数はonCreateで初期化するのでlateinitで宣言
     */
    private lateinit var rockButton: ImageButton
    private lateinit var scissorButton: ImageButton
    private lateinit var paperButton: ImageButton
    private lateinit var playerMove: ImageView
    private lateinit var enemyMove: ImageView
    private lateinit var resultText: TextView

    private val handSigns = mapOf(
            "Rock" to R.drawable.rock,
            "Scissor" to R.drawable.scissor,
            "Paper" to R.drawable.paper
    )
    private val handsList = handSigns.keys.toList()

    /**
     * アプリの画面を生成
     *
     * アプリを起動するとonCreateが呼ばれてActivityが初期化され
     * setContentViewでレイアウトビューに表示される
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * widgetのidを取得
         *
         * レイアウトに設置したwidgetのidを変数に格納する
         */
        rockButton = findViewById(R.id.rockButton)
        scissorButton = findViewById(R.id.scissorButton)
        paperButton = findViewById(R.id.paperButton)
        playerMove = findViewById(R.id.playerMove)
        enemyMove = findViewById(R.id.enemyMove)
        resultText = findViewById(R.id.resultText)

        /**
         * ButtonをClickしてじゃんけん
         *
         * ClickしたButtonのImageをRPSGameメソッドに渡してじゃんけんする
         */
        rockButton.setOnClickListener {
            playRPSGame(handsList.indexOf("Rock"))
        }
        scissorButton.setOnClickListener {
            playRPSGame(handsList.indexOf("Scissor"))
        }
        paperButton.setOnClickListener {
            playRPSGame(handsList.indexOf("Paper"))
        }
    }

    /**
     * じゃんけんのメソッド
     *
     * 引数でplayerのhandSignを受け取ってRandomでenemyのhandSignを決める
     * handSignはRock: 0, Scissor: 1, Paper: 2の数字を割り当てているので
     * ((playerSign - enemySign) + 3 ) % 3の結果で勝敗が決まる
     * 0: Draw, 1: Lose, 2: Win
     */
    private fun playRPSGame(playerSign: Int) {
        val gameResult = mapOf(
                0 to "Draw",
                1 to "Oh Your Lose..",
                2 to "You Win!!"
        )
        val enemySign: Int = Random().nextInt(3)
        val result = (playerSign - enemySign + 3) % 3

        // MapのhandSignsはNullableTypeのInt?を返すので!!演算子でNon-nullTypeのIntに変換
        playerMove.setImageResource(handSigns[handsList[playerSign]]!!)
        enemyMove.setImageResource(handSigns[handsList[enemySign]]!!)
        resultText.text = gameResult[result]
    }
}
