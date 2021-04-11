package at.allianz.interview.demo.controller;

import at.allianz.interview.demo.dto.SensorReadingDto;
import at.allianz.interview.demo.service.SensorReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor-readings")
public class SensorReadingController {

    public static final String HEADER_SENSOR_ID = "X-sensor-id";

    @Autowired
    private SensorReadingService sensorReadingService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSensorReadings(@RequestHeader(HEADER_SENSOR_ID) long sensorId,
                                  @RequestBody List<SensorReadingDto> readings) {
        sensorReadingService.addSensorReadings(sensorId, readings);
    }

}
