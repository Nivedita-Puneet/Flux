package com.vicky7230.flux.ui.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.jakewharton.rxbinding2.view.RxView
import com.vicky7230.flux.R
import com.vicky7230.flux.ui.base.BaseActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMvpView {
    @Inject
    lateinit var presenter: LoginMvpPresenter<LoginMvpView>

    var requestToken: String? = null

    companion object {

        fun getStartIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        login_button.visibility = GONE
        progress_bar.visibility = VISIBLE
        presenter.getSessionId(requestToken)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)
        init()
    }

    private fun init() {
        RxView.clicks(close_btn).subscribe({ onBackPressed() })
        RxView.clicks(login_button).subscribe({
            presenter.requestAuthenticationToken()
        })
    }

    override fun launchBrowserForLogin(requestToken: String?) {
        this.requestToken = requestToken
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(
            "https://www.themoviedb.org/authenticate/" +
                    "$requestToken" +
                    "?redirect_to=Flux://com.vicky7230.flux"
        )
        intent.`package` = "com.android.chrome"
        startActivity(intent)
    }

    override fun finishLosinScreen() {
        finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
