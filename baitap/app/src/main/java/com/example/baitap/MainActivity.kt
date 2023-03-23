package com.example.baitap

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var signup: Button? = null
    var signin: Button? = null
    var DB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById<View>(R.id.username) as EditText
        password = findViewById<View>(R.id.password) as EditText
        repassword = findViewById<View>(R.id.repassword) as EditText
        signup = findViewById<View>(R.id.btnsignup) as Button
        signin = findViewById<View>(R.id.btnsignin) as Button
        DB = DBHelper(this)
        signup!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            val repass = repassword!!.text.toString()
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@MainActivity,
                "vui lòng điền đủ thông tin",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass  ) {
                    val checkuser = DB!!.checkusername(user)
                    if (checkuser == false) {
                        val insert = DB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@MainActivity,
                                "Đăng ký thành công",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Đăng ký thất bại",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Tên đăng nhập đã tồn tại vui lòng đăng nhập",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        signin!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}