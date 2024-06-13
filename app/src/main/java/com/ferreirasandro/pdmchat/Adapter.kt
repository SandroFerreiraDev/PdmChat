package com.ferreirasandro.pdmchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val messages: List<Message>) :
    RecyclerView.Adapter<Adapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.container_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val senderTextView: TextView = itemView.findViewById(R.id.authorTv)
        private val timestampTextView: TextView = itemView.findViewById(R.id.dateTv)
        private val messageTextView: TextView = itemView.findViewById(R.id.messageTv)

        fun bind(message: Message) {
            senderTextView.text = message.author
            timestampTextView.text = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                .format(java.util.Date(message.timestamp))
            messageTextView.text = message.conteudo
        }
    }
}
