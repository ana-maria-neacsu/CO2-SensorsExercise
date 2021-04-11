package at.allianz.interview.demo.service;

import at.allianz.interview.demo.dto.SensorReadingDto;
import at.allianz.interview.demo.entity.Sensor;
import at.allianz.interview.demo.entity.SensorReading;
import at.allianz.interview.demo.repository.SensorReadingRepository;
import at.allianz.interview.demo.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private static final Logger LOG = LoggerFactory.getLogger(SensorReadingServiceImpl.class);

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Override
    @Transactional
    public void addSensorReadings(long sensorId, List<SensorReadingDto> readings) {
        LOG.info("Received readings from sensor {}: {}", sensorId, readings);

        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new EntityNotFoundException("Unknown sensor!"));

        sensorReadingRepository.saveAll(
                readings.stream()
                        .map(it ->
                                new SensorReading()
                                        .setTimestamp(it.getTimestamp())
                                        .setValue(it.getValue())
                                        .setSensor(sensor)
                        )
                        .collect(toList())
        );
    }
}
