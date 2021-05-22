package com.example.friendsr

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.friendsr.InfUsers.Companion.FRIEND_EXTRA
import com.example.friendsr.databinding.ActivityListaAmigosBinding
import kotlinx.android.synthetic.main.activity_lista_amigos.*

class ListaAmigos : AppCompatActivity(), Adapter.PeopleListener {
    private lateinit var binding: ActivityListaAmigosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaAmigosBinding.inflate(layoutInflater)
        //val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val friends = mutableListOf<ViewList>(
            ViewList(
                "Ester Expósito",
                "Ester Expósito es una actriz española de cine y televisión, especialmente reconocida por sus interpretaciones en las series de Netflix Élite y Alguien tiene que morir.",
                R.drawable.ester,
                0F
            )
        )
        friends.add(
            ViewList(
                    "Megan Fox",
                    "Megan Denise Fox es una actriz y modelo estadounidense. Fox logró mayor reconocimiento al interpretar a Mikaela Banes en Transformers, papel que le supuso varias nominaciones a los premios Teen Choice Awards y que repetiría con Transformers: la venganza de los caídos.",
                    R.drawable.megan,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Jessica Alba",
                    "Jessica Marie Alba, más conocida como Jessica Alba, es una actriz estadounidense de cine y televisión. Comenzó sus apariciones para televisión y cine a los trece años en Camp Nowhere y The Secret World of Alex Mack en 1994. Alba saltó a la fama como actriz principal en la serie Dark Angel en 2000 hasta 2002.",
                    R.drawable.jessica,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Melina Ramirez",
                    " Melina Ramírez Serna es una actriz y reina de belleza colombiana. Fue la segunda finalista del concurso Señorita Colombia 2011-2012 en 2011 y representó a su país en Top Model of the World 2012, concurso en el cual se ubicó como segunda finalista.",
                    R.drawable.melina,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Scarlett Johansson",
                    "Scarlett Ingrid Johansson (Nueva York; 22 de noviembre de 1984) es una actriz, cantante y modelo estadounidense. Comenzó a mostrar intereses por la música y la actuación desde temprana edad, y a lo largo de su infancia y adolescencia se formó en distintos institutos como actriz.",
                    R.drawable.scarlett,
                    0F
                )
            )
        friends.add(
            ViewList(
                    "Shakira Mebarak",
                    "Shakira Isabel Mebarak Ripoll (Barranquilla, Colombia; 2 de febrero de 1977), conocida artísticamente como Shakira, es una cantautora, productora discográfica, actriz, bailarina, empresaria, embajadora de buena voluntad de UNICEF y filántropa colombiana.",
                    R.drawable.shakira,
                    0F
                )
            )

        val adapter = Adapter(friends)
        adapter.setOnFriendListener(this)
        binding.lista.adapter = adapter
    }

    override fun onFriendClick(friend: ViewList) {
        super.onFriendClick(friend)
        val intent = Intent(this, InfUsers::class.java)
        intent.putExtra(FRIEND_EXTRA, friend)
        startActivityForResult(intent, InfUsers.DETAIL_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == InfUsers.DETAIL_CODE && resultCode == RESULT_OK) {
            data?.let {
                if (it.hasExtra(FRIEND_EXTRA)) {
                    val updateFriend = it.getParcelableExtra(FRIEND_EXTRA) as ViewList
                    updateFriendAtRecycler(updateFriend)
                    Toast.makeText(
                        applicationContext,
                        "Se actualizo a ${updateFriend.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateFriendAtRecycler(updateFriend: ViewList) {
        (lista.adapter as Adapter).updatePerson(friend = updateFriend)
    }
}

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/
//}
