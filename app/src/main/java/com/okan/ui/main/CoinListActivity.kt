package com.okan.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.okan.cryptocurrency.R
import com.okan.model.main.Coin
import com.okan.ui.main.MainStateEvent.GetCoinListEvent
import com.okan.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_coin_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CoinListActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)
        subscribeObservers()
        viewModel.setStateEvent(GetCoinListEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Coin>> -> {
                    displayProgressBar(false)
                    appendNames(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            text.text = message
        } else {
            text.text = getString(R.string.unknown_error)
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendNames(coinList: List<Coin>) {
        val sb = StringBuilder()
        for (coin in coinList) {
            sb.append(coin.name + "\n")
        }
        text.text = sb.toString()
    }

}