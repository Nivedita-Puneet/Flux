package com.vicky7230.flux.ui.youtubePlayer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.vicky7230.flux.R
import com.vicky7230.flux.data.Config
import com.vicky7230.flux.utils.AppConstants
import kotlinx.android.synthetic.main.activity_youtube.*


class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val RECOVERY_DIALOG_REQUEST = 1

    companion object {
        fun getStartIntent(context: Context, key: String?): Intent {
            val intent = Intent(context, YoutubeActivity::class.java)
            intent.putExtra(AppConstants.VIDEO_KEY, key)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_youtube)
        init()
    }

    private fun init() {
        youtube_view.initialize(Config.GOOGLE_KEY, this)

    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player?.loadVideo(intent.getStringExtra(AppConstants.VIDEO_KEY))

            // Hiding player controls
            //player?.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason?.isUserRecoverableError == true) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider()?.initialize(Config.GOOGLE_KEY, this)
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayerView? {
        return youtube_view
    }
}
