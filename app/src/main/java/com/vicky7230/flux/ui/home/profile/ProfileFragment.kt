package com.vicky7230.flux.ui.home.profile


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.account.Account
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.ui.home.LoginSuccessfulEventGetProfile
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
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var favouriteAdapter: FavouriteAdapter

    var isLoading = false

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

        favourite_list.layoutManager = linearLayoutManager
        favourite_list.addItemDecoration(DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL))
        favourite_list.adapter = favouriteAdapter

        favourite_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    favouriteAdapter.addItem(
                            Result(
                                    type = "LOADING"
                            )
                    )
                    presenter.getFavourites()
                    isLoading = true
                }
            }
        })

        presenter.getFavourites()
    }

    override fun showAccountDetails(account: Account) {
        profile_progress_bar.visibility = GONE
        favourite_list.visibility = VISIBLE
        name.text = account.name
        user_name.text = account.username
    }

    override fun showFavourites(results: MutableList<Result>) {
        profile_progress_bar.visibility = GONE
        favourite_list.visibility = VISIBLE
        if (favouriteAdapter.itemCount > 0)
            favouriteAdapter.removeItem()
        if (results.size > 0) {
            favouriteAdapter.addItems(results)
            isLoading = false
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoginSuccessfulEvent(eventGetProfile: LoginSuccessfulEventGetProfile) {
        val loginSuccessfulEventGetProfile = EventBus.getDefault().getStickyEvent(LoginSuccessfulEventGetProfile::class.java)
        if (loginSuccessfulEventGetProfile != null) {
            showMessage("Got Profile Event.")
            EventBus.getDefault().removeStickyEvent(loginSuccessfulEventGetProfile)
            profile_progress_bar.visibility = VISIBLE
            favourite_list.visibility = GONE
            favouriteAdapter.clearItems()
            presenter.resetPageVariable()
            presenter.getAccountDetails()
            presenter.getFavourites()
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
