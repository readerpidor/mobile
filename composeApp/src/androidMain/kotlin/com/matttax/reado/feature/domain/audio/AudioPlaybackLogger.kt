package com.matttax.reado.feature.domain.audio

import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.HttpDataSource

internal class AudioPlaybackLogger : Player.Listener {

  override fun onIsPlayingChanged(playing: Boolean) {
    Log.i(TAG, "onIsPlayingChanged=$playing")
  }

  override fun onPlaybackStateChanged(state: Int) {
    Log.i(TAG, "onPlaybackStateChanged=${stateName(state)}")
  }

  @OptIn(UnstableApi::class)
  override fun onPlayerError(error: PlaybackException) {
    Log.e(TAG, "onPlayerError code=${error.errorCodeName} msg=${error.message}", error)
    (error.cause as? HttpDataSource.InvalidResponseCodeException)?.let { http ->
      Log.e(
        TAG,
        "HTTP ${http.responseCode} ${http.responseMessage} url=${http.dataSpec.uri}\n" +
          "responseHeaders=${http.headerFields}\n" +
          "body=${String(http.responseBody)}",
      )
    }
    (error.cause as? HttpDataSource.HttpDataSourceException)?.let { http ->
      Log.e(TAG, "HttpDataSourceException url=${http.dataSpec.uri} type=${http.type} cause=${http.cause}")
    }
  }

  private fun stateName(state: Int): String = when (state) {
    Player.STATE_IDLE -> "IDLE"
    Player.STATE_BUFFERING -> "BUFFERING"
    Player.STATE_READY -> "READY"
    Player.STATE_ENDED -> "ENDED"
    else -> "UNKNOWN($state)"
  }

  private companion object {
    const val TAG = "AndroidAudioPlayer"
  }
}
