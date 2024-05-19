package com.gmail.dusandevedzic.prona

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat


var PressedMenu = false
var PressedMenu2 = false
var check=false

class Uvod : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uvod)


        // font and color creation of a function for later calling
        val htmlString = "<font color=\"#FFFFFFFF\">Ucionica Znanja</font>"
        val spanned = HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY)

//        val overlay:View=findViewById(R.id.overlay)

        val btnPocetak: Button = findViewById(R.id.btnPocetak)
        var buttonClicked = false
        val textMessage: TextView = findViewById(R.id.myTextView)



        val tinyHandImageView: ImageView = findViewById(R.id.tinyHand)

        val overlay: View = findViewById(R.id.overlay)


        //progress bar setting
        val ProgressBar: ProgressBar = findViewById(R.id.uvodProgressBar)

        ProgressBar.max = 7

        ObjectAnimator.ofInt(ProgressBar, "progress", 3).setDuration(0).start()

// set on-click listener
        // checking if button is clicked, so no other button can be pressed
        if (!buttonClicked) {
            btnPocetak.setOnClickListener {
                //check if button was clicked
                buttonClicked = true

                //making pressedmenu true so option menu item 2 cannot be clicked
                PressedMenu = true

                //making pressedmenu true so option menu item 1 cannot be clicked
                PressedMenu2 = true


//                overlay.visibility = View.VISIBLE

//gives what text will say and makes it visible
                textMessage.text =
                    "Pokusajmo prevuci ekran do donje granice ekrana kao na animaciji ruke"
                tinyHandImageView.visibility = View.VISIBLE
                textMessage.visibility = View.VISIBLE
                overlay.visibility = View.VISIBLE


// bool for check if animation has reached up or down
                var shouldPlayAnimation = true
                var shouldPlayAnimationUp = true

// Get a reference to the ImageView and load the animation

                tinyHandImageView.elevation = 10f
                val animation_up =
                    AnimationUtils.loadAnimation(this, R.anim.hand_scrolling_down_animation)
                val animation_down =
                    AnimationUtils.loadAnimation(this, R.anim.hand_scrolling_up_animation)

// Set an OnScrollChangeListener on the ScrollView
                val scrollView: ScrollView = findViewById(R.id.scrollView)
                tinyHandImageView.startAnimation(animation_down)
                tinyHandImageView.x = 300f
                scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    // Check if the user has scrolled to the bottom
                    val isAtBottom =
                        scrollY == scrollView.getChildAt(0).measuredHeight - scrollView.measuredHeight
                    if (isAtBottom) {
                        // Stop the animation and set the flag to false
                        tinyHandImageView.clearAnimation()
                        shouldPlayAnimation = false
                    } else if (shouldPlayAnimation) {
                        // Start or resume the animation
                        if (animation_down != null && !animation_down.hasEnded()) {
                            tinyHandImageView.clearAnimation()
                            tinyHandImageView.startAnimation(animation_down)
                        } else {
                            tinyHandImageView.startAnimation(animation_down)
                        }
                    }

//                     Check if the user has scrolled to the top
                    if (!shouldPlayAnimation && shouldPlayAnimationUp) {
                        val isAtTop = !scrollView.canScrollVertically(-1)
                        tinyHandImageView.startAnimation(animation_up)
                        textMessage.text =
                            "A sada pokusajmo prevuci ekran do gornje granice na nacin kao na animaciji"
                        if (isAtTop) {
                            // Stop the animation and set the flag to false
                            tinyHandImageView.clearAnimation()
                            shouldPlayAnimationUp = false
                            // moving hand up to three dots in order to point user to reaching configurations
                            textMessage.text = "Sledece sto cemo uraditi je podesiti aplikaciju."
//                            Thread.sleep(5000)
                            textMessage.y = 900f
                            tinyHandImageView.y = 0f
                            tinyHandImageView.rotation = 40f
                            textMessage.text =
                                "Podesavanjima UcionicaZnanja aplikacije mozete pristupiti preko ove 3 tackice."
                            tinyHandImageView.x = 320f
                            //after the user gets onto this stage of this tutorial, we activate the menuListener down below


                            val actionBar = supportActionBar
                            actionBar!!.setDisplayShowCustomEnabled(true)
                            actionBar!!.setCustomView(R.layout.custom_action_bar_layout)
                            val customActionBarView = actionBar!!.customView
                            customActionBarView.x = 400f
                            customActionBarView.visibility = View.VISIBLE
                            customActionBarView.setOnClickListener {
                                PressedMenu2 = false
                                customActionBarView.visibility = View.GONE
                                tinyHandImageView.x = -100f
                                textMessage.text =
                                    "A sada samo odaberete opciju podesavanje."
                                val myFunctions = Manager()
                                myFunctions.changemyBooleanPocetakSettings(true)
                                check=true
                            }


                        } else if (shouldPlayAnimationUp) {
                            // Start or resume the animation
                            if (animation_up != null && !animation_up.hasEnded()) {
                                tinyHandImageView.clearAnimation()
                                tinyHandImageView.startAnimation(animation_up)
                            } else {
                                tinyHandImageView.startAnimation(animation_up)
                            }
                        }
                    }
                }


            }
        }
        // calling on color and font of tittle changing that was defined earlier
        supportActionBar?.title = spanned

        // declaring first start functions
        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStart", true)

        //        calling of the function for first time opening message
        if (firstStart) {
            showStartDialog()
        }
    }

    // creation of the function for message of the first time opening of an app
    private fun showStartDialog() {
        AlertDialog.Builder(this)
            .setTitle("    Poruka od kreatora")
            .setMessage("Kako pustiti tutorijale UcionicaZnanja?\n\nPustanje nasih tutorijala mozete izvrsiti ili dodiram na prvo dugme koje nosi ime tutorijala i na taj nacin pustiti sve tutorijale (mozete u bilo kom trenutku izaci iz ove opcije i nastaviti odakle ste stigli ponovnim ulaskom) ili pustanjem segmenata tog tutorijala koji se nalaze ispod. \n\nHvala Vam jos jednom sto koristite nasu aplikaciju UcionicaZnanja!")
            .setPositiveButton("U redu") { dialog, which -> dialog.dismiss() }
            .create().show()

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()


    }

    //three dots menu part function creation
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //defining what created menu posesses and what it opens on click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.podesavanja -> {
                if (PressedMenu2 == false) {
                    // Handle menu item 1 click
                    val intent = Intent(this, Podesavanja::class.java)
                    startActivity(intent)
                }
                return true
            }
            R.id.oNama -> {
                if (PressedMenu == false) {
                    // Handle menu item 2 click
                    val intent = Intent(this, ONama::class.java)
                    startActivity(intent)
                }
                return true
            }
            R.id.informacija -> {
                // Handle informacije click
                val intent = Intent(this, Informacije::class.java)
                startActivity(intent)
                return true
            }
            R.id.Dozvole -> {
                // Handle informacije click
                val intent = Intent(this, Dozvole::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}


