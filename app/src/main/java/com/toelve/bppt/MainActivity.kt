package com.toelve.bppt

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btMasuk.setOnClickListener {
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()
            val load = Dialog(this@MainActivity)
            load.setContentView(R.layout.dialogload2)
            load.setCancelable(false)
            load.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            val ivX = load.findViewById<ImageView>(R.id.ivX)
            load.show()
            val loginTask=LoginTask(this, load)
            loginTask.requestAction(email, password)
        }
        tvDaftar.setOnClickListener {
            startActivity(Intent(this@MainActivity, Daftar::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        val builder=AlertDialog.Builder(this)
        builder.setMessage("Yakin Mau Keluar?")
        builder.setCancelable(false)
        builder.setPositiveButton("yes"){ _, which ->
            Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()
            finish()
        }


        builder.setNegativeButton("no") { _, which ->
            Toast.makeText(
                applicationContext,
                "no", Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNeutralButton("Maybe") { _, which ->
            Toast.makeText(
                applicationContext,
                "Maybe", Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()

        }

}