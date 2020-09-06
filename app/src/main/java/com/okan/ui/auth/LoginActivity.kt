package com.okan.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.okan.cryptocurrency.R
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



    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun validateFieldsAndLogin() {
        if (!TextUtils.isEmpty(username.text.toString())
            && !TextUtils.isEmpty(password.text.toString())
        ) {
            Toast.makeText(
                this,
                getString(R.string.validate_message), Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.setStateEvent(
                AuthStateEvent.Login(
                    email = username.text.toString(),
                    password = password.text.toString()
                )
            )
        }
    }
}