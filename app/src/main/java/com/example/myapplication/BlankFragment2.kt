package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager

import android.content.Context

import android.os.Build
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONObject

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.core.app.NotificationManagerCompat



class BlankFragment2 : Fragment(){



    val channelId="101";
    val notificationId=101;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank2, container, false);
        return view
    }

    override fun onResume() {
        super.onResume()
        val nameCity = view?.findViewById<TextView>(R.id.cityName);
        val degreeText = view?.findViewById<TextView>(R.id.cityDegrees);
        val now = nameCity?.getText();
        val cityvalue = GlobalClass.city;
    if (now != cityvalue){
        searchByName(cityvalue);

    }
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onStart() {
        super.onStart()
        val global = GlobalClass();
        searchByName(GlobalClass.city);
    }
    public fun searchByName(res: String) {

        val url = "https://api.openweathermap.org/data/2.5/weather?appid=55f62d54b6328d9accf3fa97aa9eb53a&units=metric&lang=sp&q=$res"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                val json = JSONObject(response);
                val nameCity = view?.findViewById<TextView>(R.id.cityName);
                val degreeText = view?.findViewById<TextView>(R.id.cityDegrees);
                val desc = view?.findViewById<TextView>(R.id.descrWeather);
                val tempmax =view?.findViewById<TextView>(R.id.tempMaxVal);
                val tempmin = view?.findViewById<TextView>(R.id.tempMinVal);
                val cloud = view?.findViewById<TextView>(R.id.cloudsVal);
                val wind = view?.findViewById<TextView>(R.id.windval);

                val name= json.get("name").toString();
                val degree = json.getJSONObject("main").get("temp").toString().subSequence(0,2);
                val descriptionval = json.getJSONArray("weather").getJSONObject(0).get("description").toString();

                val tempMaxval= json.getJSONObject("main").get("temp_max").toString();
                val tempMinval= json.getJSONObject("main").get("temp_min").toString();
                val cloudval =json.getJSONObject("clouds").get("all").toString();
                val windval = json.getJSONObject("wind").get("speed").toString();
;                nameCity?.text = name;
                degreeText?.text ="$degree °C";
                desc?.text=descriptionval;
                tempmax?.text="$tempMaxval °C";
                tempmin?.text="$tempMinval °C";
                cloud?.text="$cloudval %";
                wind?.text="$windval km/h";
                Toast.makeText(requireActivity(),"siuu",Toast.LENGTH_SHORT).show()


                    sendNotification()

            },
            { Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show()},
        )
// Add the request to the RequestQueue.
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {


        val name = "title"
        val desc = "desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =NotificationChannel(channelId,name,importance).apply {
            description = desc
        }
        val notificationManager:NotificationManager = activity?.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    private fun sendNotification() {
        val nameCity = view?.findViewById<TextView>(R.id.cityName);
        val nameCityval = nameCity?.getText();
        val degree = view?.findViewById<TextView>(R.id.cityDegrees);
        val degreeval = degree?.getText();
        val builder= Builder(requireActivity(),channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Clima Saber Hacer")
            .setContentText("$nameCityval \n $degreeval")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireActivity())){
            notify(notificationId,builder.build())
        }



    }


}