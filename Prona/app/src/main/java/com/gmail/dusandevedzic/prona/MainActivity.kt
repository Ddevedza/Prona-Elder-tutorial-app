package com.gmail.dusandevedzic.prona


import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.TypefaceSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        defining buttons for opening other menus
        val btnUvod: Button = findViewById(R.id.btnUvod)
        val btnFacebook: Button = findViewById(R.id.btnFacebook)
        val btnGmail: Button = findViewById(R.id.btnGmail)
        val btnInstagram: Button = findViewById(R.id.btnInstagram)
        val btnWhatsapp: Button = findViewById(R.id.btnWhatsapp)
        val btnViber: Button = findViewById(R.id.btnViber)

// text inside of the button implementation
        //text
        val spannableStringUvod = SpannableString("Uvod\nkako koristiti aplikaciju i telefon")
        //different parts of button size changing
        spannableStringUvod.setSpan(RelativeSizeSpan(0.8f), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringUvod.setSpan(RelativeSizeSpan(0.2f), 5, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        btnUvod.text = spannableStringUvod
// text inside of the button implementation
        //text
        val spannableStringFacebook =
            SpannableString("Facebook\n DRUSTVENA MREZA ZA POVEZIVANJE SA LJUDIMA")
        //different parts of button size changing
        spannableStringFacebook.setSpan(
            RelativeSizeSpan(0.8f),
            0,
            8,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringFacebook.setSpan(
            RelativeSizeSpan(0.2f),
            9,
            51,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        btnFacebook.text = spannableStringFacebook
// text inside of the button implementation
        //text
        val spannableStringGmail = SpannableString("Gmail\n MESTO ZA SLANJE MEJLOVA")
        //different parts of button size changing
        spannableStringGmail.setSpan(RelativeSizeSpan(0.8f), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringGmail.setSpan(
            RelativeSizeSpan(0.2f),
            6,
            30,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        btnGmail.text = spannableStringGmail
// text inside of the button implementation
        //text
        val spannableStringInstagram =
            SpannableString("Instagram\n DRUSTVENA MREZA ZA DELJENJE FOTOGRAFIJA I VIDEO SNIMAKA")
        //different parts of button size changing
        spannableStringInstagram.setSpan(
            RelativeSizeSpan(0.8f),
            0,
            9,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringInstagram.setSpan(
            RelativeSizeSpan(0.2f),
            10,
            66,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringInstagram.setSpan(
            TypefaceSpan("monospace"),
            10,
            66,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        btnInstagram.text = spannableStringInstagram
// text inside of the button implementation
        //text
        val spannableStringViber =
            SpannableString("Viber\n APLIKACIJA ZA RAZMENU TEKSTUALNIH PORUKA, POZIVA I DATOTEKA")
        //different parts of button size changing
        spannableStringViber.setSpan(RelativeSizeSpan(0.9f), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringViber.setSpan(
            RelativeSizeSpan(0.25f),
            6,
            66,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringInstagram.setSpan(
            TypefaceSpan("monospace"),
            6,
            66,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        btnViber.text = spannableStringViber
// text inside of the button implementation
        //text
        val spannableStringWhatsapp =
            SpannableString("Whatsapp\n APLIKACIJA ZA RAZMENU TEKSTUALNIH PORUKA, POZIVA I DATOTEKA")
        spannableStringWhatsapp.setSpan(
            RelativeSizeSpan(0.8f),
            0,
            8,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringWhatsapp.setSpan(
            RelativeSizeSpan(0.2f),
            9,
            69,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        btnWhatsapp.text = spannableStringWhatsapp

// declaring first start functions
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)

//        calling of the function for first time opening message
        if (firstStart) {
            showStartDialog()
        }


// function that opens on click menus
        btnUvod.setOnClickListener {
            val intent = Intent(this, Uvod::class.java)
            startActivity(intent)
        }

        btnFacebook.setOnClickListener {
            val intent = Intent(this, facebook::class.java)
            startActivity(intent)
        }

        btnGmail.setOnClickListener {
            val intent = Intent(this, gmail::class.java)
            startActivity(intent)
        }

//        btnInstagram.setOnClickListener {
//            val intent = Intent(this, gmail::class.java)
//            startActivity(intent)
//        }


//color changing function
        val htmlString = "<font color=\"#FFFFFFFF\">UcionicaZnanja</font>"
        val spanned = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)
        supportActionBar?.title = spanned


    }

    // creation of the function for message of the first time opening of an app
    private fun showStartDialog() {
        AlertDialog.Builder(this)
            .setTitle("    Poruka od kreatora")
            .setMessage("Hvala Vam sto ste izabrali bas nas kao svoje prvo digitalno putovanje! Zamolili bi smo Vas da pre pocetka ovog tutorijala procitate ovo uputstvo:\n\n    Prilikom prvog koriscenja, savetujemo Vam da ovu aplikaciju koristite u prisustvu jos jedne osobe radi pokretanja prvobitnog tutorijala. Nakon predjenog treceg uvodnog tutorijala, imacete dovoljno znanja da otvorate i koristite ovu aplikaciju, stoga dalje prisustvo neke osobe nije potrebno.\n\n   Takodje kako bi aplikacija mogla funkcionisati, zamolili bi smo Vas da prihvatite osnovne uslove koriscenja, o pravima i namenama uslova koriscenja mozete procitati u dokumentaciji aplikacije.\n\n\nHvala jos jednom sto bas nas odabrali kao Vase prvo iskustvo! - Prona tim")
            .setPositiveButton("Zapocni digitalnu avanturu") { dialog, which -> dialog.dismiss() }
            .create().show()

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
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


