package com.example.rigorousbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rigorousbot.R
import com.example.rigorousbot.data.Message
import com.example.rigorousbot.utils.Constants.RECEIVE_ID
import com.example.rigorousbot.utils.Constants.SEND_ID

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>(){

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itermView: View) : RecyclerView.ViewHolder(itermView){
        init{
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))

    }

    override fun getItemCount(): Int {
        return messagesList.size
    }



    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                    holder.itemView.findViewById<TextView>(R.id.tv_message).apply {
                        text = currentMessage.message
                        visibility = View.VISIBLE
                    }
                    holder.itemView.findViewById<TextView>(R.id.tv_bot_message).visibility = View.GONE
                }
                    RECEIVE_ID -> {
                        holder.itemView.findViewById<TextView>(R.id.tv_bot_message).apply { text = currentMessage.message
                            visibility = View.VISIBLE
                        }

                        holder.itemView.findViewById<TextView>(R.id.tv_message).visibility = View.GONE
                    }
            }
        }


    fun insertMessage(message: Message){
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
         }
    }





