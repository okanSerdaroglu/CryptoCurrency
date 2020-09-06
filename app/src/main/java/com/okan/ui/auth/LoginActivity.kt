package com.okan.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.okan.cryptocurrency.R
import com.okan.model.auth.LoginResult
import com.okan.ui.main.CoinListActivity
import com.okan.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_coin_list.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        subscribeObservers()
        login.setOnClickListener {
            validateFieldsAndLogin()
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<LoginResult> -> {
                    displayProgressBar(false)
                    navigateToCoinList()
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    showErrorMessage(getString(R.string.invalid_user))
                }

            }
        })


    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(
            this,
            message, Toast.LENGTH_SHORT
        ).show()
    }

    private fun validateFieldsAndLogin() {
        if (TextUtils.isEmpty(username.text.toString())
            || TextUtils.isEmpty(password.text.toString())
        ) {
            showErrorMessage(getString(R.string.validate_message))
        } else {
            viewModel.setStateEvent(
                AuthStateEvent.Login(
                    email = username.text.toString(),
                    password = password.text.toString()
                )
            )
        }
    }

    private fun navigateToCoinList() {
        val intent = Intent(this, CoinListActivity::class.java)
        startActivity(intent)
    }
}