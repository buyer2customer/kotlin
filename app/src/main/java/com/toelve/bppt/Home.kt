package com.toelve.bppt

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONException
import org.json.JSONObject

class Home : AppCompatActivity() {
    lateinit var boyprefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boyprefs = getSharedPreferences(resources.getString(R.string.prefensi), MODE_PRIVATE)
        setContentView(R.layout.activity_home)
        val response = boyprefs.getString("response", "")
        etresop.setText(response)
        try {
            val jsonObj = JSONObject(response!!)
            val ass: String = jsonObj.getString("data")
            val jsonObj2 = JSONObject(ass)
            etNama.setText(jsonObj2.getString("fullName"))
            etToken.setText(jsonObj2.getString("jwtToken"))
        } catch (e: JSONException) {
            e.printStackTrace()
        }


    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this@Home)
        alertDialogBuilder.setMessage("Keluar?")
            .setCancelable(false)
            .setPositiveButton(
                "Ya"
            ) { dialog: DialogInterface, id: Int ->
                dialog.cancel()
                finish()
            }
        alertDialogBuilder.setNegativeButton(
            "Tidak"
        ) { dialog: DialogInterface, id: Int -> dialog.cancel() }
        val alert = alertDialogBuilder.create()
        alert.show()
    }
}