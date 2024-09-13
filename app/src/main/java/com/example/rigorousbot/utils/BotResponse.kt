package com.example.rigorousbot.utils

import com.example.rigorousbot.utils.Constants.OPEN_GOOGLE
import com.example.rigorousbot.utils.Constants.OPEN_SEARCH
import java.util.Locale

object BotResponse {
    fun basicResponses(_message: String): String{
        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when{
            //hello
            message.contains("hello")->{
                when (random) {
                    0 -> "Hello Prathamesh here, how can i help you?"
                    1 -> "Hello Prathamesh here, how can i help you?"
                    2 -> "Hello Prathamesh here, how can i help you?"
                    else -> "error"
                }
            }

            //hi
            message.contains("hi")->{
                when (random) {
                    0 -> "Jay Shree Ram"
                    1 -> "Jay Shree Ram"
                    2 -> "Ram Ram bola kay mhantay?"
                    else -> "error"
                }
            }

            //special
            message.contains("gdsc")&&message.contains("lead")->{
                var r = (0..1).random()
                val result = if (r == 0) "Aryan Sinha" else "Aryan Sinha"

                "The cureent lead of GDSC ADYPU is $result"
            }

            //special
            message.contains("guidance")&&message.contains("gdsc")->{
                var r = (0..1).random()
                val result = if (r == 0) "Dr.Vishal Shirsath" else "Dr.Vishal Shirsath"

                "Under the guidance of $result"
            }

            //hii
            message.contains("hii")->{
                when (random) {
                    0 -> "Jay Shree Ram"
                    1 -> "Jay Shree Ram"
                    2 -> "Ram Ram bola kay mhantay?"
                    else -> "error"
                }
            }


            //hiii
            message.contains("hiii")->{
                when (random) {
                    0 -> "Jay Shree Ram"
                    1 -> "Jay Shree Ram"
                    2 -> "Ram Ram bola kay mhantay?"
                    else -> "error"
                }
            }


            //lagna karu ka?
            message.contains("lagna karu ka?")->{
                when (random) {
                    0 -> "Tuzi na laykich naiye bot use karnyachi"
                    1 -> "Tuzi na laykich naiye bot use karnyachi"
                    2 -> "Tuzi na laykich naiye bot use karnyachi"
                   else -> "error"
                }
            }

            //How are you?
            message.contains("How are you?")->{
                when (random) {
                    0 -> "Ekdam Mast Tu Kasa Ahes??"
                    1 -> "Bhava!! mi tr majet asto nehmi"
                    2 -> "Ekdmmmmm Kadkkkk.."
                    else -> "error"
                }
            }


            //how are you?
            message.contains("how are you?")->{
                when (random) {
                    0 -> "Ekdam Mast Tu Kasa Ahes??"
                    1 -> "Bhava!! mi tr majet asto nehmi"
                    2 -> "Ekdmmmmm Kadkkkk.."
                    else -> "error"
                }
            }


            message.contains("flip")&&message.contains("coin")->{
                var r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }
            //Solves Maths
            message.contains("solve") -> {
                val equation: String? = message.substringAfter("solve")
                return  try{
                    val answer = SolveMath.solveMath(equation?:"0")
                    answer.toString()

                }catch (e: Exception){
                    "Sorry, I Can't Solve that!"
                }
            }
            //Gets the current time
            message.contains("time")&& message.contains("goggle")->{
                Time.timeStamp()
            }

            //Gets the current Time
            message.contains("Time")&& message.contains("goggle")->{
                Time.timeStamp()
            }

            //Open Google
            message.contains("open") && message.contains("goggle")->{
                OPEN_GOOGLE
            }

                    message.contains("search") ->{
                OPEN_SEARCH
            }


            else ->{
                when (random) {
                    0 -> "I Don't Understand"
                    1 -> "IDK"
                    2 -> "Try Asking Me Something Diffrent!"
                    else -> "error"
                }
            }
        }


    }
}