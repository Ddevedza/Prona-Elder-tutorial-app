package com.gmail.dusandevedzic.prona

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import com.gmail.dusandevedzic.prona.Uvod

class Podesavanja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_podesavanja)
        //text and seek bar 1
        val GlasTextView: TextView = findViewById(R.id.GlasTextView)
        val GlasSeekBar: SeekBar = findViewById(R.id.GlasSeekBar)
        //text and seek bar 2
        val FontSlovaTextView: TextView = findViewById(R.id.FontSlovaTextView)
        val FontSlovaSeekBar: SeekBar = findViewById(R.id.FontSlovaPrikazSeekBar)
        //glas switch one defining
        val GlasSwitch: Switch = findViewById(R.id.GlasSwitch)
        val TextSwitch: Switch = findViewById(R.id.TextSwitch)
        val PauzaTextView: TextView = findViewById(R.id.PauzaTextView)
        val FontTextView: TextView = findViewById(R.id.FontTextView)

        //making switch always on
        TextSwitch.setChecked(true)

        val textView3: TextView = findViewById(R.id.PrikazFontaTextView)
        //if podesavanja are reached from tutorial button, this tutorial part will be activated
        val myFunctions = Manager()
        if (check) {
//tutorial things declaration
            //tutorial checks
            var glasSwitchCheck = false
            var glasSeekBarCheck = false
            var textSwitchCheck = false
            var textSeekBarCheck = false


            val textMessage: TextView = findViewById(R.id.myTextView)
            val tinyHandImageView: ImageView = findViewById(R.id.tinyHand)
            val overlay: View = findViewById(R.id.overlay)

//gives what text will say and makes it visible
            textMessage.text =
                "Dodirom na ovaj prekidac mozete omoguciti ili onemugiciti naraciju koja sluzi da Vam objasni sta radite i zasto"
            tinyHandImageView.visibility = View.VISIBLE
            textMessage.visibility = View.VISIBLE
            overlay.visibility = View.VISIBLE

            textMessage.translationY = 1170f
            tinyHandImageView.translationX = 350f
            tinyHandImageView.translationY = -500f
            tinyHandImageView.rotation = 23f

            GlasSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    // Switch is on
                    glasSwitchCheck = true
                } else {
                    // Switch is off
                }

                if (glasSwitchCheck) {
                    textMessage.text =
                        "Prevlacenjem ovog bara mozete povecati ili smanjiti ponavljanje naracije u shodno sa Vasim potrebama. Prevlacenjem u desno cete povecati broj, prevlacenjem u levo cete smanjiti broj. Broj ispod indukuje na koliko ce biti ponavljanje."
                    tinyHandImageView.x = 100f
                    tinyHandImageView.y = 300f
                    tinyHandImageView.rotation = -23f
                    val animationLeftRight =
                        AnimationUtils.loadAnimation(this, R.anim.hand_left_right_animation)
                    tinyHandImageView.startAnimation(animationLeftRight)
                    val animationRightLeft =
                        AnimationUtils.loadAnimation(this, R.anim.hand_right_left_animation)
                    tinyHandImageView.startAnimation(animationRightLeft)

                    GlasSeekBar.setOnSeekBarChangeListener(object :
                        SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(
                            seekBar: SeekBar?,
                            progress: Int,
                            fromUser: Boolean
                        ) {
                            // This method will be called when the seekbar progress changes
                            // `progress` parameter contains the new progress value
                            // `fromUser` parameter is true if the change was initiated by the user, false otherwise
                            val value = when {
                                //5 10 15 20 30 45 60
                                progress < 14 -> 5
                                progress < 28 -> 10
                                progress < 43 -> 15
                                progress < 57 -> 20
                                progress < 71 -> 30
                                progress < 85 -> 45
                                else -> 60
                            }
                            GlasTextView.text = value.toString()
                        }


                        override fun onStartTrackingTouch(seekBar: SeekBar?) {
                            // This method will be called when the user starts touching the seekbar
                            glasSeekBarCheck = true
                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar?) {
                            tinyHandImageView.clearAnimation()
                            tinyHandImageView.x = 380f
                            tinyHandImageView.y = 480f
                            tinyHandImageView.rotation = 30f
                            glasSeekBarCheck = true
                            textMessage.text="Dodirom na ovaj prekidac mozete omoguciti ili onemugiciti text koji sluzi da Vam objasni sta radite i zasto"
                            if (glasSeekBarCheck == true) {
                                TextSwitch.setOnCheckedChangeListener { _, isCheckedText ->
                                    if (isCheckedText) {
                                    } else {
                                        // Switch is off
                                        tinyHandImageView.x = -100f
                                        tinyHandImageView.y = 670f
                                        tinyHandImageView.startAnimation(animationRightLeft)
                                        textMessage.text = "Pomeranjem ovog bara u desno mozete povecati velicinu slova, dok pomeranjem u desno mozete smanjiti ovaj text"
                                    }
                                }
                            }
                        }
                    })
                }
            }

        }

        //first seek bar progress and sliding work
        GlasSeekBar?.progress = 21
        GlasSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (progress < 10) {
//                    seekBar?.progress = 10
//                } else if (progress > 90) {
//                    seekBar?.progress = 90
//                }

                // you can also segment the SeekBar and map it to values
                val value = when {
                    //5 10 15 20 30 45 60
                    progress < 14 -> 5
                    progress < 28 -> 10
                    progress < 43 -> 15
                    progress < 57 -> 20
                    progress < 71 -> 30
                    progress < 85 -> 45
                    else -> 60
                }
                GlasTextView.text = value.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // do something when user starts sliding
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // do something when user stops sliding
            }
        })
        //second seek bar progress and sliding work
        FontSlovaSeekBar?.progress = 35
        FontSlovaSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (progress < 10) {
//                    seekBar?.progress = 10
//                } else if (progress > 90) {
//                    seekBar?.progress = 90
//                }

                // you can also segment the SeekBar and map it to values
                val value = when {
                    //5 10 15 20 30 45 60
                    progress < 2 -> 10
                    progress < 11 -> 11
                    progress < 20 -> 12
                    progress < 30 -> 13
                    progress < 40 -> 14
                    progress < 50 -> 15
                    progress < 60 -> 16
                    progress < 70 -> 17
                    progress < 80 -> 18
                    progress < 90 -> 19
                    else -> 20
                }
                FontSlovaTextView.text = value.toString()
                //in case slider is used, text will become bigger
                val myFloat = (value.toFloat())
                textView3.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                FontSlovaTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                GlasTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                GlasSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                TextSwitch.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                FontTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
                PauzaTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, myFloat)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // do something when user starts sliding
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // do something when user stops sliding
            }
        })


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