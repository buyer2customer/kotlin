package com.toelve.bppt

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class VerifikasiTask()
{
    private lateinit var load:Dialog
    private lateinit var context:Context
    constructor(context: Context, load: Dialog) : this() {
        this.load = load
        this.context = context
        val boyprefs=context.getSharedPreferences(
            context.getResources().getString(R.string.prefensi), Context.MODE_PRIVATE
        );
    }

     fun create(){
         Handler(Looper.getMainLooper()).postDelayed({
             Toast.makeText(context, "masuk ke create", Toast.LENGTH_LONG).show()
             load.dismiss()

         }, 3000)
    }

    fun requestAction(token: String?) {
        load.dismiss()
       val boyprefs = context.getSharedPreferences(
           context.getResources().getString(R.string.prefensi),
           Context.MODE_PRIVATE
       )
        val queue = Volley.newRequestQueue(context)
        val POST_ORDER: String = context.getResources().getString(R.string.veremail)
        try {
            val requestQueue = Volley.newRequestQueue(context)
            val jsonBody = JSONObject()
            jsonBody.put("token", token)
            val requestBody = jsonBody.toString()
            val stringRequest: StringRequest = object : StringRequest(
                Method.POST,
                POST_ORDER,
                Response.Listener { response ->
                    Log.i("VOLLEY", response!!)
                    boyprefs.edit().putString("response", response).apply()
                    context.startActivity(Intent(context, MainActivity::class.java))
                    (context as Activity).finish()
                },
                Response.ErrorListener { error ->
                    val response = error.networkResponse
                    if (error is ServerError && response != null) {
                        try {
                            val json = String(
                                response.data ?: ByteArray(0),
                                Charset.forName(
                                    HttpHeaderParser.parseCharset(
                                        response.headers,
                                        "utf-8"
                                    )
                                )
                            )
                            // Now you can use any deserializer to make sense of data
                            val obj = JSONObject(json)
                            Log.e("res", obj.toString())
                            Log.e("obj", obj.getString("message"))
                                Toast.makeText(
                                    context,
                                    obj.getString("message"),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                context.startActivity(Intent(context, MainActivity::class.java))
                                (context as Activity).finish()

                        } catch (e1: UnsupportedEncodingException) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace()
                        } catch (e2: JSONException) {
                            // returned data is not JSONObject?
                            e2.printStackTrace()
                        }
                    }
                }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                @Throws(AuthFailureError::class)
                override fun getBody(): ByteArray? {
                    return try {
                        requestBody.toByteArray(charset("utf-8"))
                    } catch (uee: UnsupportedEncodingException) {
                        VolleyLog.wtf(
                            "Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody,
                            "utf-8"
                        )
                        null
                    }
                }



                override fun deliverResponse(response: String) {
                    super.deliverResponse(response)
                    Log.e("d", response)
                }
            }
            requestQueue.add(stringRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}