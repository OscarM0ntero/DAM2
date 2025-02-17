package com.example.animacion

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var clickButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // obtenemos referencias de los elementos de la interfaz
        imageView = findViewById(R.id.imageView)
        clickButton = findViewById(R.id.clickButton)

        // creamos el reproductor de sonido
        mediaPlayer = MediaPlayer.create(this, R.raw.coin_sound)

        // configuramos el evento de clic del boton
        clickButton.setOnClickListener {
            playSound()
            moveImage()
        }
    }

    private fun moveImage() {
        // creamos la animacion para mover 300 pixeles a la derecha
        val moveRight = TranslateAnimation(0f, 300f, 0f, 0f)
        moveRight.duration = 2000 // 2 segundos

        // creamos la animacion para mover 600 pixeles a la izquierda
        val moveLeft = TranslateAnimation(300f, -300f, 0f, 0f)
        moveLeft.duration = 2000 // 2 segundos

        // creamos la animacion para mover 300 pixeles a la derecha otra vez
        val moveRightAgain = TranslateAnimation(-300f, 0f, 0f, 0f)
        moveRightAgain.duration = 1000 // 1 segundo

        // usamos un handler para encadenar las animaciones
        val handler = Handler()

        // iniciamos la primera animacion
        imageView.startAnimation(moveRight)

        // despues de que termine la primera animacion, iniciamos la segunda
        handler.postDelayed({
            imageView.startAnimation(moveLeft)
        }, moveRight.duration)

        // despues de que termine la segunda animacion, iniciamos la tercera
        handler.postDelayed({
            imageView.startAnimation(moveRightAgain)
        }, moveRight.duration + moveLeft.duration)
    }

    private fun playSound() {
        // reproducimos el sonido si no se esta reproduciendo
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // liberamos recursos del reproductor de sonido
        mediaPlayer.release()
    }
}
