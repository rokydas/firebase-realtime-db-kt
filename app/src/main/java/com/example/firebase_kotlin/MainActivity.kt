package com.example.firebase_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitBtn.setOnClickListener() {
            val name = editName.text.toString().trim()
            if(name.isEmpty()) {
                editName.error = "Please enter your name"
            }

            val ratingNumber = ratingBar.rating.toInt()

            val database = Firebase.database.reference
            val ref = FirebaseDatabase.getInstance().getReference("ratings")

            val userRating = Rating(name, ratingNumber)
            val entryId = ref.push().key
                ref.child(entryId.toString()).setValue(userRating)
                .addOnSuccessListener {
                    Toast.makeText(this,
                        "Your rating is saved successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,
                        "something went wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

    }
}