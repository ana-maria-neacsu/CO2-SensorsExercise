# Run with:
```
./gradlew bootRun
```


# API call examples:
* Publish CO2 reading from sensor:
```
curl --location --request POST 'http://localhost:8080/sensor-readings' \
--header 'X-sensor-id: 13' \
--header 'Content-Type: application/json' \
--data-raw '[{
"timestamp": "1618171766",
"value": 1.0
}]'
```