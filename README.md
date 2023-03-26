CodingAssignment
Summary: Get Sensor Data:

Use command to Run the application mvn spring-boot:run 
Sample Req:

http://localhost:8080/sensors/data?ids=S1,S2,S3&timesteps=1w&fromDate=2023-03-16&toDate=2023-03-31&stats=AVG,MIN,MAX&metrics=WND,TMP

Req Attribute :

timestep=1h,1w,1d,1m.
stats=AVG,MIN,MA
&metrics=WND,TMP
ids=S1,S2,S3


Response:


{
    "starTimeStamp": "date",
    "endTimeStamp": "date",
    "sensorsData": [
        {
            "id": "S1",
            "name": "Home",
            "temperature": {
                "min": 297.56,
                "max": 300.05,
                "sum": 300.05,
                "avg": 300.05
            },
            "humidity": {
                "min": 297.56,
                "max": 300.05,
                "sum": 300.05,
                "avg": 300.05
            }
        },
        {
            "id": "S2",
            "name": "garage",
            "temperature": {
                "min": 297.56,
                "max": 300.05,
                "sum": 300.05,
                "avg": 300.05
            },
            "humidity": {
                "min": 297.56,
                "max": 300.05,
                "sum": 300.05,
                "avg": 300.05
            }
        }
    ]
}

Repsponse Code:
200 Success
400+ Data validation.
500 Error.

