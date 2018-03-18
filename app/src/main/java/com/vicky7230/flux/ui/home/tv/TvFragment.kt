package com.vicky7230.flux.ui.home.tv


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatSpinner
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject
import android.widget.AdapterView.OnItemSelectedListener


/**
 * A simple [Fragment] subclass.
 */
class TvFragment : BaseFragment(), TvMvpView {

    @Inject
    lateinit var presenter: TvMvpPresenter<TvMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var itemOffsetDecoration: ItemOffsetDecoration
    @Inject
    lateinit var tvAdapter: TvAdapter

    var isLoading = false
    var sortBy = "popularity.desc"
    var ratingMoreThan = 0

    private val sortByList = listOf(
        "Popularity",
        "Rating",
        "Air Date"
    )

    private val sortByMap = hashMapOf(
        "Popularity" to "popularity.desc",
        "Rating" to "vote_average.desc",
        "Air Date" to "first_air_date.desc"
    )

    private val rating = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    private var dialog: Dialog? = null

    companion object {
        fun newInstance() = TvFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {

        val layoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = layoutInflater.inflate(R.layout.filters_dialog_view, null, false)

        setUpSortBySpinner(dialogView)
        setUpRatingSpinner(dialogView)

        RxView.clicks(dialogView.findViewById(R.id.apply_button)).subscribe({
            progress.visibility = VISIBLE
            tvtList.visibility = GONE
            tvAdapter.clearItems()
            presenter.resetPageVariable()
            presenter.getTvs(sortBy, ratingMoreThan)
            dialog?.dismiss()
        })

        RxView.clicks(dialogView.findViewById(R.id.cancel_button)).subscribe({
            dialog?.dismiss()
        })

        dialog = Dialog(activity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(dialogView)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (tvAdapter.getItem(position)?.type) {
                    "LOADING" -> 2
                    "RESULT" -> 1
                    else -> 1
                }
            }
        }

        tvtList.layoutManager = gridLayoutManager
        tvtList.addItemDecoration(itemOffsetDecoration)
        tvtList.adapter = tvAdapter

        tvtList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    tvAdapter.addItem(
                        Result(
                            type = "LOADING"
                        )
                    )
                    presenter.getTvs(sortBy, ratingMoreThan)
                    isLoading = true
                }
            }
        })

        presenter.getTvs(sortBy, ratingMoreThan)
    }

    private fun setUpSortBySpinner(dialogView: View) {
        val sortBySpinner = dialogView.findViewById(R.id.sort_by_spinner) as AppCompatSpinner
        val adapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            sortByList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sortBySpinner.adapter = adapter
        sortBySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                sortBy = sortByMap[sortByList[position]] ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun setUpRatingSpinner(dialogView: View) {
        val ratingSpinner = dialogView.findViewById(R.id.rating_spinner) as AppCompatSpinner
        val adapter = ArrayAdapter(
            activity,
            android.R.layout.simple_spinner_item,
            rating
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        ratingSpinner.adapter = adapter
        ratingSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                ratingMoreThan = rating[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun updateTvList(results: MutableList<Result>) {
        progress.visibility = GONE
        tvtList.visibility = VISIBLE
        if (tvAdapter.itemCount > 0)
            tvAdapter.removeItem()
        if (results.size > 0) {
            tvAdapter.addItems(results)
            isLoading = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filters -> {
                dialog?.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}
