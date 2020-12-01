package com.toelve.bppt

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_verifikasi.*

class Verifikasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)
        btMasuk.setOnClickListener(View.OnClickListener { view ->
            val token = etToken.getText().toString()
            if (token.isEmpty()) {
                Snackbar.make(view, "Masukan Token", Snackbar.LENGTH_SHORT).show()
            } else {
                val load = Dialog(this@Verifikasi)
                load.setContentView(R.layout.dialogload2)
                load.setCancelable(false)
                load.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                val ivX = load.findViewById<ImageView>(R.id.ivX)
                load.show()
                val verifikasiTask=VerifikasiTask(this@Verifikasi, load)
                verifikasiTask.requestAction(token)
            }
        })

    }

    override fun onBackPressed() {
        startActivity(Intent(this@Verifikasi, MainActivity::class.java))
        finish()
    }
}