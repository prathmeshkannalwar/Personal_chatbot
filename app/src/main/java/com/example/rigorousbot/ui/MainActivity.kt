package com.example.rigorousbot.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rigorousbot.R
import com.example.rigorousbot.data.Message
import com.example.rigorousbot.utils.BotResponse
import com.example.rigorousbot.utils.Constants.OPEN_GOOGLE
import com.example.rigorousbot.utils.Constants.OPEN_SEARCH
import com.example.rigorousbot.utils.Constants.RECEIVE_ID
import com.example.rigorousbot.utils.Constants.SEND_ID
import com.example.rigorousbot.utils.Time
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("PRATHAMESH","PRATHAMESH","PRATHAMESH","PRATHAMESH")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView()


        clickEvents()

        val random = (0..3).random()
        customMessage("Hello today you are speaking with ${botList[random]}, how may i help?")
    }

        private fun clickEvents(){
            findViewById<Button>(R.id.btn_send).setOnClickListener {
                sendMessage()
            }
            findViewById<EditText>(R.id.et_message).setOnClickListener{
                GlobalScope.launch {
                    delay(100)
                    withContext(Dispatchers.Main){
                        findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

                    }

                }
            }
        }


        private fun recyclerView(){
            adapter = MessagingAdapter()
            findViewById<RecyclerView>(R.id.rv_messages).adapter = adapter
            findViewById<RecyclerView>(R.id.rv_messages).layoutManager = LinearLayoutManager(applicationContext)

        }

        private fun sendMessage(){
            val message = findViewById<EditText>(R.id.et_message).text.toString()
            val timestamp = Time.timeStamp()


            if(message.isNotEmpty()){
                findViewById<EditText>(R.id.et_message).setText("")
                    adapter.insertMessage(Message(message, SEND_ID,timestamp))
                    findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

                botResponse(message)
            }
        }

        private fun botResponse(message: String){

            val timeStamp = Time.timeStamp()

            GlobalScope.launch {
                delay(1000)

                withContext(Dispatchers.Main){
                    val response = BotResponse.basicResponses(message)
                    adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))
                    findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

                    when (response){
                        OPEN_GOOGLE -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            site.data = Uri.parse("https://www.google.com/")
                            startActivity(site)
                        }
                        OPEN_SEARCH -> {
                            val site = Intent(Intent.ACTION_VIEW)
                            val searchTerm: String? = message.substringAfter("search")
                            site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                            startActivity(site)
                        }
                    }
                }
            }

        }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main){
                findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)

            }
        }
    }

        private fun customMessage(message: String){
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){
                    val timestamp = Time.timeStamp()
                    adapter.insertMessage(Message(message,RECEIVE_ID, timestamp))
                    findViewById<RecyclerView>(R.id.rv_messages).scrollToPosition(adapter.itemCount-1)
                }
            }
        }
    }
