

Request:
{
"sensors": [
{
"sensorid":1,
"sensorname":"basement",
"metrics":["temperature","humidity"],
"statistic":[ "min", "max", "sum" , "average"]
}
]

}

Response: 

timeRange include  : Hourly :1h, Daily :1d, Weekly 1w, Monthly:1m 
{
"sensorData": [
{
        "sensorid": 501,
        "sensorname": "Home",
        "timeRange" :"1m",
        "starTimeStamp" : "date",
        "endTimeStamp" : "date",
        "temperature":
        {

        "min": 297.56,
        "max": 300.05,
        "sum": 300.05,
        "avg": 300.05

         },
      "humidity":{

        "min": 297.56,
        "max": 300.05,
        "sum": 300.05,
        "avg": 300.05

      }



    }

]
}





