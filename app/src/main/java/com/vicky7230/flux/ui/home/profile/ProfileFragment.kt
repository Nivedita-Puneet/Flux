package com.vicky7230.flux.ui.home.profile


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.account.Account
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.ui.home.LoginSuccessfulEventGetProfile
import com.vicky7230.flux.ui.home.LoginSuccessfulEventGetWatchlist
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment(), ProfileMvpView {

    @Inject
    lateinit var presenter: ProfileMvpPresenter<ProfileMvpView>

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        presenter.getAccountDetails()
    }

    override fun showAccountDetails(account: Account) {
        profile_progress_bar.visibility = GONE
        name.text = account.name
        user_name.text = account.username
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoginSuccessfulEvent(eventGetProfile: LoginSuccessfulEventGetProfile) {
        val loginSuccessfulEventGetProfile = EventBus.getDefault().getStickyEvent(LoginSuccessfulEventGetProfile::class.java)
        if (loginSuccessfulEventGetProfile != null) {
            showMessage("Got Profile Event.")
            EventBus.getDefault().removeStickyEvent(loginSuccessfulEventGetProfile)
            presenter.getAccountDetails()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}// Required empty public constructor
