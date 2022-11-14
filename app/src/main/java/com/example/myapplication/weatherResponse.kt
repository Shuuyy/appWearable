package com.example.myapplication

data class weatherResponse(var name:String,
                           var coord:List<String>,
                           var weather:List<String>,
                           var base:String,
                           var main:List<String>,
                           var visibility:String,
                           var wind:List<String>,
                           var clouds:List<String>,
                           var dt:String,
                           var sys:List<String>,
                           var timezone:String,
                           var id:String,
                           var cod:String,
)