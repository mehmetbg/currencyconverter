package com.mehmetbg.currencyconvertkotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getRates(view: View) {

        val download = Download()

        try {
            val url = "http://data.fixer.io/api/latest?access_key=f8cf47068c2a0f68edfea684a19e1052"
            download.execute(url)

        } catch (e:Exception){

        }

    }
    inner class Download : AsyncTask<String, Void, String>(){



        override fun doInBackground(vararg params: String?): String {
            var result = ""
            var url : URL
            var httpURLConnection : HttpURLConnection

            try {

                url = URL(params[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while(data > 0){
                    val character = data.toChar()
                    result += character
                    data = inputStreamReader.read()
                }
                return result

            } catch (e: Exception){
                return result
            }
        }

        override fun onPostExecute(result: String?) {

            try {
                val jSonObject = JSONObject(result)
                val base = jSonObject.getString("base")
                println(base)
                val rates = jSonObject.getString("rates")
                println(rates)

                val jSonObject2 = JSONObject(rates)
                val lira = jSonObject2.getString("TRY")
                println(lira)
                val gbp = jSonObject2.getString("GBP")
                val chf = jSonObject2.getString("CHF")
                val usd = jSonObject2.getString("USD")
                val cad = jSonObject2.getString("CAD")

                tryText.text = "TRY: " + lira
                cadText.text = "CAD: " + cad
                usdText.text = "USD: " + usd
                chfText.text = "CHF: " + chf
                gbpText.text = "GBP: " + gbp


            } catch (e: Exception){

            }
            super.onPostExecute(result)
        }


    }


}