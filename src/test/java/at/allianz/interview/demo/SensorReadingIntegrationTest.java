package at.allianz.interview.demo;

import at.allianz.interview.demo.dto.SensorReadingDto;
import at.allianz.interview.demo.entity.Location;
import at.allianz.interview.demo.entity.Sensor;
import at.allianz.interview.demo.entity.SensorReading;
import at.allianz.interview.demo.repository.LocationRepository;
import at.allianz.interview.demo.repository.SensorReadingRepository;
import at.allianz.interview.demo.repository.SensorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.util.List;

import static at.allianz.interview.demo.controller.SensorReadingController.HEADER_SENSOR_ID;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SensorReadingIntegrationTest {

    private static final EasyRandom random = new EasyRandom();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        Location location = locationRepository.save(random.nextObject(Location.class));
        sensor = sensorRepository.save(new Sensor().setLocation(location));
    }

    @Test
    public void storeSensorReadings() throws Exception {
        List<SensorReadingDto> sensorReadings = random.objects(SensorReadingDto.class, 10).collect(toList());

        publishSensorReadings(sensor.getId(), sensorReadings)
                .andExpect(status().isNoContent());

        assertReflectionEquals(
                sensorReadings.stream()
                        .map(this::toEntity)
                        .collect(toList()),
                sensorReadingRepository.findBySensor(sensor),
                IGNORE_DEFAULTS
        );
    }

    @Test
    public void returnsNotFoundForUnknownSensor() throws Exception {
        publishSensorReadings(-1, emptyList())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Unknown sensor!"));
    }


    private SensorReading toEntity(SensorReadingDto dto) {
        return new SensorReading()
                .setTimestamp(dto.getTimestamp())
                .setValue(dto.getValue())
                .setSensor(sensor);
    }

    private ResultActions publishSensorReadings(long sensorId, List<SensorReadingDto> readings) throws Exception {
        return mvc.perform(
                post("/sensor-readings")
                        .header(HEADER_SENSOR_ID, sensorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(readings))
        );
    }
}
