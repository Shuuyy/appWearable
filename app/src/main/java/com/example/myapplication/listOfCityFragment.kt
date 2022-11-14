package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import retrofit2.Response


class listOfCityFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_city, container, false);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerCity = view.findViewById<Spinner>(R.id.spinner);


        val city = ListOfcity().list;
        val list = ArrayAdapter(requireActivity(),R.layout.spinneritem,city);
        spinnerCity.adapter=list;
        spinnerCity.onItemSelectedListener= object:AdapterView.OnItemSelectedListener{
            @SuppressLint("SuspiciousIndentation")
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i:Int, l:Long) {
                var res= city[i];

                GlobalClass.city=res;

                Toast.makeText(requireActivity(),  GlobalClass.city,Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun searchByName(res: String) {

        val url = "https://api.openweathermap.org/data/2.5/weather?appid=55f62d54b6328d9accf3fa97aa9eb53a&units=metric&lang=sp&q=$res"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                val json = JSONObject(response);

               val value= json.get("name");
                val namecity = activity?.findViewById<TextView>(R.id.cityName);
                namecity?.setText("jajaja");

                Toast.makeText(requireActivity(),"siuu",Toast.LENGTH_SHORT).show()
            },
            { Toast.makeText(requireActivity(),"Error",Toast.LENGTH_SHORT).show()},
        )

// Add the request to the RequestQueue.
            Volley.newRequestQueue(requireActivity()).add(stringRequest);

    }


//    private fun getRetrofit(): Retrofit {
//        //q=Saltillo
//        return Retrofit.Builder()
//            .baseUrl()
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    private fun searchByName(query:String){
//        CoroutineScope(Dispatchers.IO).launch{
//            val call: Response<weatherResponse> = getRetrofit().create(ApiService::class.java).getCurrentWheater("");
//            val weather = call.body();
//            if(call.isSuccessful){
//                if (weather != null) {
//                    setInformation(weather)
//                }
//            }else{
//                showError()
//            }
//        }
//    }

//    private fun setInformation(weather: weatherResponse) {
//
//        val name = weather.name.toString();
//        text?.setText(name);
//    }

    private fun showError() {
        Toast.makeText(requireActivity(),"Ha Ocurrido un error",Toast.LENGTH_SHORT).show();
    }


}