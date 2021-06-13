package com.example.wanandroid.ui.login_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.wanandroid.R
import com.example.wanandroid.logic.Repository
import com.example.wanandroid.logic.dao.UserDao
import com.example.wanandroid.ui.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class login : AppCompatActivity() {
    lateinit var loginButton: Button
    lateinit var registerButton: Button
    lateinit var username: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var repassword: TextInputEditText
    lateinit var repasswordLayout: TextInputLayout
    lateinit var note: TextView
    lateinit var rememberPassword: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.btn_login)
        username = findViewById(R.id.tiet_username)
        password = findViewById(R.id.tiet_password)
        repassword = findViewById(R.id.tiet_repassword)
        registerButton = findViewById(R.id.btn_register)
        repasswordLayout = findViewById(R.id.textInputLayout_repassword)
        rememberPassword = findViewById(R.id.cb_rememberPassworrd)
        note = findViewById(R.id.tv_note)
        if (UserDao.isUserSaved()) {
            username.setText(UserDao.getUsername())
            password.setText(UserDao.getPassword())
            rememberPassword.isChecked = true
        }
        loginButton.setOnClickListener {
            Toast.makeText(this, "登陆中", Toast.LENGTH_SHORT).show()
            if (username.text.toString() != "null" && password.text.toString() != "null") {
                Repository.login(username.text.toString(), password.text.toString()).observe(this,
                    Observer {
                        val result = it.getOrNull()
                        if (result != null) {
                            if (result.errorCode == -1) {
                                Toast.makeText(this, result.errorMsg, Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                if (rememberPassword.isChecked) {
                                    UserDao.saveUserInfo(
                                        username.text.toString(),
                                        password.text.toString()
                                    )
                                } else UserDao.clearUserInfo()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    })
            }
        }
        registerButton.setOnClickListener {
            Repository.register(
                username.text.toString(),
                password.text.toString(),
                repassword.text.toString()
            ).observe(this, Observer {
                val result = it.getOrNull()
                if (result != null) {
                    if (result.errorCode == -1) {
                        Toast.makeText(this, result.errorMsg, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }

        note.setOnClickListener {
            if (repasswordLayout.visibility == View.INVISIBLE) {
                repasswordLayout.visibility = View.VISIBLE
                note.text = "已经注册？返回登陆"
            } else if (repasswordLayout.visibility == View.VISIBLE) {
                repasswordLayout.visibility = View.INVISIBLE
                note.visibility = View.INVISIBLE
            }
        }
    }

}