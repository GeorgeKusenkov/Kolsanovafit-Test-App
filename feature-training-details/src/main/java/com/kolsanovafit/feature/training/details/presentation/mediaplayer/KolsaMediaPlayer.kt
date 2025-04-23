package com.kolsanovafit.feature.training.details.presentation.mediaplayer

import android.content.Context
import android.net.Uri
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

/**
 * Обертка для VideoView, облегчющая работу с видеоконтентом.
 * Тут интерфейсы для базовых операций воспроизведения,
 * паузы и управления состоянием видеоплеера.
 */
class KolsaMediaPlayer(private val context: Context) {

    private var videoView: VideoView? = null

    fun setupWithVideoView(videoView: VideoView) {
        this.videoView = videoView
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
    }

    fun playVideo(uri: Uri) {
        videoView?.apply {
            setVideoURI(uri)
            setOnPreparedListener { it.start() }
            setOnErrorListener { _, _, _ ->
                Toast.makeText(context, "Ошибка воспроизведения", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    fun isPlaying(): Boolean {
        return videoView?.isPlaying == true
    }

    fun getCurrentPosition(): Int {
        return videoView?.currentPosition ?: 0
    }

    fun pause() {
        videoView?.pause()
    }

    fun resume() {
        videoView?.start()
    }

    fun release() {
        videoView?.suspend()
        videoView = null
    }
}