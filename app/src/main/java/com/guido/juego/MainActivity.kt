package com.guido.juego

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.ImageFormat
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore.Video.Media
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.guido.juego.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //<editor-fold desc="IMAGENES GUI">
    lateinit var img_11:ImageView
    lateinit var img_12:ImageView
    lateinit var img_13:ImageView
    lateinit var img_14:ImageView

    lateinit var img_21:ImageView
    lateinit var img_22:ImageView
    lateinit var img_23:ImageView
    lateinit var img_24:ImageView

    lateinit var img_31:ImageView
    lateinit var img_32:ImageView
    lateinit var img_33:ImageView
    lateinit var img_34:ImageView

    //</editor-fold>
    //<editor-fold desc="OTROS GUI">
    lateinit var tv_jugador1:TextView
    lateinit var tv_jugador2:TextView
    lateinit var btn_sonido:ImageButton

    lateinit var mp:MediaPlayer
    lateinit var mpfondo:MediaPlayer
    lateinit var imagen1:ImageView
    lateinit var imagen2:ImageView
    //</editor-fold>
    //<editor-fold desc="VARIABLES">
      var imagenes= arrayOf(11,12,13,14,15,16,21,22,23,24,25,26)

     var homero=0
     var bart=0
     var lisa=0
     var familia=0
     var juntos=0
     var comida=0

     var turno=1
     var puntoj1=0
     var puntoj2=0
    var numero_imagen=1
    var escuchar=true
    //</editor-fold>

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enlazarGUI()






    }

    private fun enlazarGUI() {
        btn_sonido=binding.btnSonido
        btn_sonido.setColorFilter(Color.GREEN)

        img_11=binding.viewCustom.img12
        img_12=binding.viewCustom.img13
        img_13=binding.viewCustom.img14
        img_14=binding.viewCustom.img11

        img_21=binding.viewCustom.img21
        img_22=binding.viewCustom.img22
        img_23=binding.viewCustom.img23
        img_24=binding.viewCustom.img24

        img_31=binding.viewCustom.img31
        img_32=binding.viewCustom.img32
        img_33=binding.viewCustom.img33
        img_34=binding.viewCustom.img34


        sonido("background",true)
        img_11.tag="0"
        img_12.tag="1"
        img_13.tag="2"
        img_14.tag="3"
        img_21.tag="4"
        img_22.tag="5"
        img_23.tag="6"
        img_24.tag="7"
        img_31.tag="8"
        img_32.tag="9"
        img_33.tag="10"
        img_34.tag="11"

        homero= R.drawable.homero
        bart= R.drawable.bart
        lisa= R.drawable.lisa
        familia= R.drawable.familia
        juntos= R.drawable.juntos
        comida= R.drawable.comida
        imagenes.shuffle()
        tv_jugador1=binding.tvJugador1
        tv_jugador2=binding.tvJugador2
        tv_jugador1.setTextColor(Color.GRAY)
        tv_jugador2.setTextColor(Color.WHITE)
    }
    private fun sonido(nombre_sonido: String, loop: Boolean=false) {
        var resID=resources.getIdentifier(
            nombre_sonido,"raw",packageName
        )
        if (nombre_sonido=="background"){
            mpfondo= MediaPlayer.create(this,resID)
            mpfondo.isLooping=loop
            mpfondo.setVolume(0.04F,0.04F)
            if (!mpfondo.isPlaying){
                mpfondo.start()
            }
        }else{
            mp= MediaPlayer.create(this,resID)
            mp.setOnCompletionListener(MediaPlayer.OnCompletionListener {mediaPlayer ->
                mediaPlayer.stop()
                mediaPlayer.release()
            })
            if (!mp.isPlaying){
                mp.start()
            }

        }



    }

    fun musicadefondo(v: View){
        if (escuchar){
            mpfondo.pause()
            btn_sonido.setImageResource(R.drawable.volumen_off)
            btn_sonido.setColorFilter(Color.GRAY)
        }else{
            mpfondo.start()
            btn_sonido.setImageResource(R.drawable.volumen_up)
            btn_sonido.setColorFilter(Color.GREEN)
        }
        escuchar= !escuchar
    }

    fun seleccionar(imagen:View){
        sonido("touch")
        verificar(imagen )
    }

    private fun verificar(imagen: View) {
        var iv=(imagen as ImageView)
        var tag= imagen.tag.toString().toInt()
        if (imagenes[tag]==11){
            iv.setImageResource(homero)

        }else if (imagenes[tag]==12){
            iv.setImageResource(bart)

        }else if (imagenes[tag]==13){
            iv.setImageResource(lisa)

        }else if (imagenes[tag]==14){
            iv.setImageResource(familia)

        }else if (imagenes[tag]==15){
            iv.setImageResource(comida)

        }else if (imagenes[tag]==16){
            iv.setImageResource(juntos)

        }else   if (imagenes[tag]==21){
            iv.setImageResource(homero)

        }else if (imagenes[tag]==22){
            iv.setImageResource(bart)

        }else if (imagenes[tag]==23){
            iv.setImageResource(lisa)

        }else if (imagenes[tag]==24){
            iv.setImageResource(familia)

        }else if (imagenes[tag]==25){
            iv.setImageResource(comida)

        }else if (imagenes[tag]==26){
            iv.setImageResource(juntos)

        }
        if(numero_imagen==1){
            imagen1=iv
            numero_imagen=2
            iv.isEnabled=false
        }else if(numero_imagen==2){
            imagen2=iv
            numero_imagen=1
            iv.isEnabled=false
            desahibilitar_imagen()
            val h=Handler(Looper.getMainLooper())
             h.postDelayed({sonImagenesIguales()},1000)
        }


    }

    private fun sonImagenesIguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState){
             sonido("success")
             if (turno==1){
                puntoj1++
                tv_jugador1.text="j1: $puntoj1"
             }else  if (turno==2){
                puntoj2++
                tv_jugador2.text="j2: $puntoj2"
             }

            imagen1.isEnabled=false
            imagen2.isEnabled=false
            imagen1.tag=""
            imagen2.tag=""
        }else{
            sonido("no")
            imagen1.setImageResource(R.drawable.oculta)
            imagen2.setImageResource(R.drawable.oculta)

            if (turno==1){
                turno=2
                tv_jugador1.setTextColor(Color.GRAY)
                tv_jugador2.setTextColor(Color.WHITE)
                Toast.makeText(this, "turno deljugador2", Toast.LENGTH_SHORT).show()

            }else if (turno==2){
                turno=1
                tv_jugador1.setTextColor(Color.WHITE)
                tv_jugador2.setTextColor(Color.GRAY)
                Toast.makeText(this, "turno deljugador1", Toast.LENGTH_SHORT).show()
            }
        }
        img_11.isEnabled=!img_11.tag.toString().isEmpty()
        img_12.isEnabled=!img_12.tag.toString().isEmpty()
        img_13.isEnabled=!img_13.tag.toString().isEmpty()
        img_14.isEnabled=!img_14.tag.toString().isEmpty()
        img_21.isEnabled=!img_21.tag.toString().isEmpty()
        img_22.isEnabled=!img_22.tag.toString().isEmpty()
        img_23.isEnabled=!img_23.tag.toString().isEmpty()
        img_24.isEnabled=!img_24.tag.toString().isEmpty()
        img_31.isEnabled=!img_31.tag.toString().isEmpty()
        img_32.isEnabled=!img_32.tag.toString().isEmpty()
        img_33.isEnabled=!img_33.tag.toString().isEmpty()
        img_34.isEnabled=!img_34.tag.toString().isEmpty()
        verificarFinjuego()

    }

    private fun verificarFinjuego() {
        if(img_11.tag.toString().isEmpty()&&
            img_12.tag.toString().isEmpty()&&
            img_13.tag.toString().isEmpty()&&
            img_14.tag.toString().isEmpty()&&
            img_21.tag.toString().isEmpty()&&
            img_22.tag.toString().isEmpty()&&
            img_23.tag.toString().isEmpty()&&
            img_24.tag.toString().isEmpty()&&
            img_31.tag.toString().isEmpty()&&
            img_32.tag.toString().isEmpty()&&
            img_33.tag.toString().isEmpty()&&
            img_34.tag.toString().isEmpty()){
            mp.stop()
            mp.release()
            sonido("win")
            val builder= AlertDialog.Builder(this)
            builder
                .setTitle("FIN DEL JUEGO")
                .setMessage("PUNTAJE\n j1: "+puntoj1+"\nj2: "+puntoj2)
                .setCancelable(false)
                .setPositiveButton("NUEVO JUEGO", DialogInterface.OnClickListener{
                    dialogInterface, i -> startActivity(Intent(this,MainActivity::class.java))
                    finish()
                })
                .setNegativeButton("SALIR", DialogInterface.OnClickListener{
                    dialogInterface, i ->
                finish()
            })
            val ad =builder.create()
            ad.show()


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mpfondo.stop()
        mpfondo.release()
    }

    private fun desahibilitar_imagen() {
        img_11.isEnabled=false
        img_12.isEnabled=false
        img_13.isEnabled=false
        img_14.isEnabled=false
        img_21.isEnabled=false
        img_22.isEnabled=false
        img_23.isEnabled=false
        img_24.isEnabled=false
        img_31.isEnabled=false
        img_32.isEnabled=false
        img_33.isEnabled=false
        img_34.isEnabled=false
    }



}