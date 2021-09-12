package com.demirli.a58boolebotsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var botList: ArrayList<Bot>
    private lateinit var botAdapter: BotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
    }

    private fun setUi(){
        addBotsForStartScreen()

        setBotAdapter()

        setAddBotSpinners()

        add_bot_btn.setOnClickListener {
            addBot()
        }

        random_collision_btn.setOnClickListener{
            randomBotCollision(botList)
        }
    }

    private fun addBotsForStartScreen(){
        var bot1 = Bot("Bot1", true, Bot.booleanOperationAnd())
        var bot2 = Bot("Bot2", true, Bot.booleanOperationOr())
        var bot3 = Bot("Bot3", false, Bot.booleanOperationXor())
        botList = arrayListOf(bot1, bot2, bot3)
    }

    private fun setBotAdapter(){
        botAdapter = BotAdapter(botList)
        bots_recyclerView.layoutManager = LinearLayoutManager(this)
        bots_recyclerView.adapter = botAdapter
    }

    private fun addBot(){
        if(botName_et.text.toString() != "" &&
            booleanValue_spinner.selectedItem.toString() != "Boolean Value: " &&
            booleanOperation_spinner.selectedItem.toString() != "Boolean Operation: "){
            addBotAndToList(botName_et.text.toString(), booleanValue_spinner.selectedItem.toString().toBoolean(), booleanOperation_spinner.selectedItem.toString())
        }
    }

    private fun setAddBotSpinners(){
        booleanValue_spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("Boolean Value: " , "false", "true"))
        booleanOperation_spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("Boolean Operation: ", "AND", "OR", "XOR"))
    }

    private fun addBotAndToList(botName: String, booleanValue: Boolean, booleanOperation: String){
        val bot = Bot(botName, booleanValue, booleanOperation)
        botList.add(bot)
        botAdapter.notifyDataSetChanged()
    }

    private fun randomBotCollision(botList: List<Bot>){
        var randomInt1 = (0..botList.size-1).random()
        var randomInt2 = (0..botList.size-1).random()

        if(randomInt1 == randomInt2){
            randomBotCollision(botList)
        }else{
            if(botList[randomInt1].isAlive && botList[randomInt2].isAlive){
                winner_tv.setText("Winner: " + Bot.collision(botList[randomInt1], botList[randomInt2]))
                vs_textView.setText(botList[randomInt1].botName + " vs " + botList[randomInt2].botName)
            }else{
                randomBotCollision(botList)
            }
        }

        botAdapter.notifyDataSetChanged()
    }
}
