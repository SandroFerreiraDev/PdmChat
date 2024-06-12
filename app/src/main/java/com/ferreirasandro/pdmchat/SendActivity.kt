package com.ferreirasandro.pdmchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ferreirasandro.pdmchat.Message
import com.ferreirasandro.pdmchat.databinding.ActivitySendBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SendActivity : AppCompatActivity() {
    private val asb: ActivitySendBinding by lazy {
        ActivitySendBinding.inflate(layoutInflater)
    }

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)

        db = FirebaseDatabase.getInstance().reference.child("messages")

        asb.SendSubmitBt.setOnClickListener {
            val author = asb.AuthorEt.text.toString()
            val date = System.currentTimeMillis()
            val message = asb.messageEt.text.toString()


            if (author.isBlank() || message.isBlank()) {
                Toast.makeText(this, "Autor ou mensagem vazia.", Toast.LENGTH_SHORT).show()
            }
            else if (message.length > 99){
                Toast.makeText(this, "Mensagem muito longa", Toast.LENGTH_SHORT).show()
            }
            else{
                val newMessage = Message(author, date, message)
                db.push().setValue(newMessage)
                finish()
            }
        }
    }
}