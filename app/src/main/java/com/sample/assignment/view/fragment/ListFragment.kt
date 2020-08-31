package com.sample.assignment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.assignment.R
import com.sample.assignment.databinding.ListFragmentBinding
import com.sample.assignment.db.data.Row
import com.sample.assignment.view.activity.MainActivity
import com.sample.assignment.view.adapter.ListAdapter
import com.sample.assignment.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
Created by Ajay Arya on 28/08/20
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    private lateinit var bind: ListFragmentBinding
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.list_fragment, container, false
        )
        val mRootView = bind.root
        bind.lifecycleOwner = this
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity?)?.setToolbarText("Title")
        setup()
    }

    /**
     *      Setup UI and Observables
     *
     */
    private fun setup() {

        adapter =
            ListAdapter(requireActivity()) { selectedVenueItem: Row ->
                itemClicked(
                    selectedVenueItem
                )
            }
        bind.list.layoutManager = LinearLayoutManager(activity)
        bind.list.adapter = adapter

        /**
         *      Observe list change
         */
        viewModel.mainList.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = View.GONE
            refresh.isRefreshing = false
            if (it != null) {
                (activity as MainActivity?)?.setToolbarText(it.title)
                if (it.rows.isNotEmpty()) {
                    hideErrorLayout()
                    adapter.setVenueListData(it.rows)
                    Toast.makeText(activity, "Item/s added", Toast.LENGTH_LONG).show()
                    bind.list.post { bind.list.scrollToPosition(0) }
                }
            }
        })

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it?.let {
                showErrorLayout(it)
                refresh.isRefreshing = false
                //Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        })

        refresh.isRefreshing = false
        viewModel.getList()

        refresh.setOnRefreshListener {
            refresh.isRefreshing = true
            viewModel.getList()
        }

    }

    /**
     *      Item click from adapter
     *
     * @param row     Venue Item on which click event had happened
     */
    private fun itemClicked(row: Row) {
    }

    private fun hideErrorLayout(){
        error_constraint.visibility = View.GONE
    }

    private fun showErrorLayout(error : String){
        error_constraint.visibility = View.VISIBLE
        tv_error.text = error + "\n Pull to refresh"
    }
}