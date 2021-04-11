package at.allianz.interview.demo.service;

import at.allianz.interview.demo.dto.SensorReadingDto;

import java.util.List;

public interface SensorReadingService {
    void addSensorReadings(long sensorId, List<SensorReadingDto> readings);
}
