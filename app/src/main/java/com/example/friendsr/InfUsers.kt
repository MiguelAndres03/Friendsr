package com.example.friendsr

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.friendsr.databinding.ActivityInfUserBinding

class InfUsers : AppCompatActivity() {
    private lateinit var friend: ViewList
    lateinit var binding: ActivityInfUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra(FRIEND_EXTRA)) {
            friend = intent.getParcelableExtra<ViewList>(FRIEND_EXTRA)
        }
        fillData()
        binding.calificacion.setOnRatingBarChangeListener { _, newScore, _ ->
            friend.score = newScore
            goBackToMain()
        }
    }

    fun fillData() {
        binding.calificacion.rating = friend.score
        binding.descripcion.text = friend.description
        binding.foto2.setImageResource(friend.photo)
    }

    fun goBackToMain() {
        val intent = Intent(this, ListaAmigos::class.java)
        intent.putExtra(FRIEND_EXTRA, friend)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val DETAIL_CODE = 55
        const val FRIEND_EXTRA = "FRIEND"

    }
}


/*class InfUsers : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}*/
