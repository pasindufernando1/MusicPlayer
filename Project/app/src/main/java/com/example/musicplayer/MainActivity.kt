package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        // Initialize the MediaPlayer instance and start playing a default audio file.
        mediaPlayer = MediaPlayer.create(this, R.raw.default_audio)
        mediaPlayer.start()

        val playButton = findViewById<Button>(R.id.playButton)
        val pauseButton = findViewById<Button>(R.id.pauseButton)

        playButton.setOnClickListener {
            Log.d("MainActivity", "playButton clicked")
            mediaPlayer.start()
            isPaused = false
        }

        pauseButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPaused = true
            }
        }

    }

    override fun onStart() {
        super.onStart()

        if (isPaused) {
            mediaPlayer.start()
            isPaused = false
        }
    }

    override fun onPause() {
        super.onPause()

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPaused = true
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}