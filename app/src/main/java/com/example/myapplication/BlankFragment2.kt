package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext


class BlankFragment2 : Fragment(){




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            },
            { Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show()},
        )
// Add the request to the RequestQueue.
        Volley.newRequestQueue(requireActivity()).add(stringRequest);
    }




}