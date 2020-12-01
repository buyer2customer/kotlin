package com.toelve.bppt

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_daftar.*

class Daftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        btMasuk.setOnClickListener(View.OnClickListener { view ->
           val email = etEmail.getText().toString()
            val  password = etPassword.getText().toString()
            val title = etTitle.getText().toString()
            val fullname = etFullname.getText().toString()
            val confirm = etConfirm.getText().toString()
            if (title.isEmpty()) {
                Snackbar.make(view, "Masukan title", Snackbar.LENGTH_SHORT).show()
            } else if (fullname.isEmpty()) {
                Snackbar.make(view, "Masukan Full Name", Snackbar.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Snackbar.make(view, "Masukan email", Snackbar.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Snackbar.make(view, "Masukan password", Snackbar.LENGTH_SHORT).show()
            } else if (confirm.isEmpty()) {
                Snackbar.make(view, "Masukan password", Snackbar.LENGTH_SHORT).show()
            } else if (confirm != password) {
                Snackbar.make(
                    view,
                    "Password dan confirmasi password tidak sama",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                val load = Dialog(this@Daftar)
                load.setContentView(R.layout.dialogload2)
                load.setCancelable(false)
                load.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                val ivX = load.findViewById<ImageView>(R.id.ivX)
                load.show()
                val registertask=RegisterTask(this@Daftar, load)
                registertask.requestAction(title,
                    fullname,
                    password,
                    email,
                    confirm)
            }
        })
    }

    override fun onBackPressed() {
        startActivity(Intent(this@Daftar, MainActivity::class.java))
        finish()
    }
}