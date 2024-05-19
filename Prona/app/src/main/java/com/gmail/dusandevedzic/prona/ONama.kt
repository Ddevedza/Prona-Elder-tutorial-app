package com.gmail.dusandevedzic.prona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class ONama : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onama)
    }

    // creation of menu on action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //defining what created menu posesses
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.podesavanja -> {
                val intent = Intent(this, Podesavanja::class.java)
                startActivity(intent)
                return true
            }
            R.id.oNama -> {
                // Handle menu item 2 click
                return true
            }
            R.id.informacija-> {
                // Handle informacije click
                val intent = Intent(this, Informacije::class.java)
                startActivity(intent)
                return true
            }
            R.id.Dozvole-> {
                // Handle informacije click
                val intent = Intent(this, Dozvole::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}