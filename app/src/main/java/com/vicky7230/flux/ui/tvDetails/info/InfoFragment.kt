package com.vicky7230.flux.ui.tvDetails.info


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.google.android.flexbox.FlexboxLayout
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.tvDetails.Cast
import com.vicky7230.flux.data.network.model.tvDetails.Genre
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.utils.AppConstants
import com.vicky7230.flux.utils.GlideApp
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class InfoFragment : BaseFragment(), InfoMvpView {

    @Inject
    lateinit var presenter: InfoMvpPresenter<InfoMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var castListAdapter: CastListAdapter

    companion object {
        fun newInstance(
                networkLogo: String?,
                numberOfSeasons: Int?,
                numberOfEpisodes: Int?,
                overview: String?,
                genres: ArrayList<Genre>?,
                cast: ArrayList<Cast>?): InfoFragment {
            val infoFragment = InfoFragment()
            val args = Bundle()
            args.putString(AppConstants.NETWORK_LOGO, networkLogo)
            args.putInt(AppConstants.NUM_SEASONS, numberOfSeasons ?: 0)
            args.putInt(AppConstants.NUM_EPISODES, numberOfEpisodes ?: 0)
            args.putString(AppConstants.OVER_VIEW, overview)
            args.putParcelableArrayList(AppConstants.GENRE_LIST, genres)
            args.putParcelableArrayList(AppConstants.CAST_LIST, cast)
            infoFragment.arguments = args
            return infoFragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)
        presenter.onAttach(this)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun setUp(view: View) {

        if (arguments != null) {

            GlideApp
                    .with(this)
                    .load("https://image.tmdb.org/t/p/w300/" + arguments?.getString(AppConstants.NETWORK_LOGO))
                    .transition(withCrossFade())
                    .centerInside()
                    .into(network_logo)

            seasons_episodes_num.text = "${arguments?.getInt(AppConstants.NUM_SEASONS)} Seasons / " +
                    "${arguments?.getInt(AppConstants.NUM_EPISODES)} Episodes"

            for (genre in arguments?.getParcelableArrayList<Genre>(AppConstants.GENRE_LIST)
                    ?: arrayListOf()) {
                val chip = AppCompatButton(this.activity)
                styleChip(chip)
                chip.text = genre.name
                genre_chips.addView(chip)
            }

            stoyline.text = arguments?.getString(AppConstants.OVER_VIEW)

            cast_list.layoutManager = linearLayoutManager
            cast_list.adapter = castListAdapter
            val cast = arguments?.getParcelableArrayList<Cast>(AppConstants.CAST_LIST)
            cast?.sortBy { it.order }
            castListAdapter.addItems(cast)
        }
    }

    private fun styleChip(chip: AppCompatButton): View? {
        chip.setPadding(resources.getDimensionPixelOffset(R.dimen.chipPadding), 0,
                resources.getDimensionPixelOffset(R.dimen.chipPadding), 0)
        chip.setTextColor(ContextCompat.getColor(this.activity!!, android.R.color.white))
        chip.background = ContextCompat.getDrawable(this.activity!!, R.drawable.curved_orange)
        val params = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, resources.getDimensionPixelOffset(R.dimen.chipMargin),
                resources.getDimensionPixelOffset(R.dimen.chipMarginBig), resources.getDimensionPixelOffset(R.dimen.chipMargin))
        params.height = resources.getDimensionPixelOffset(R.dimen.chipHeight)
        chip.layoutParams = params
        return chip
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
