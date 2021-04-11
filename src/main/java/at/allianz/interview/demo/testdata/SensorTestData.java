package at.allianz.interview.demo.testdata;

import at.allianz.interview.demo.entity.Location;
import at.allianz.interview.demo.entity.Sensor;
import at.allianz.interview.demo.repository.LocationRepository;
import at.allianz.interview.demo.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

@Component
public class SensorTestData {

    private static final Logger LOG = LoggerFactory.getLogger(SensorTestData.class);

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @PostConstruct
    @Transactional
    public void init() {
        List<Location> locations = locationRepository.saveAll(asList(
                new Location().setCity("Barcelona").setDistrict("Gracia"),
                new Location().setCity("Barcelona").setDistrict("Eixample"),
                new Location().setCity("Wien").setDistrict("Währing"),
                new Location().setCity("Wien").setDistrict("Penzing"),
                new Location().setCity("München").setDistrict("Maxvorstadt")
        ));

        List<Sensor> sensors = sensorRepository.saveAll(asList(
                new Sensor().setLocation(locations.get(0)),
                new Sensor().setLocation(locations.get(0)),
                new Sensor().setLocation(locations.get(1)),
                new Sensor().setLocation(locations.get(2)),
                new Sensor().setLocation(locations.get(3)),
                new Sensor().setLocation(locations.get(3)),
                new Sensor().setLocation(locations.get(4)),
                new Sensor().setLocation(locations.get(4)),
                new Sensor().setLocation(locations.get(4))
        ));

        logSensors(sensors);
    }

    private void logSensors(List<Sensor> sensors) {
        LOG.info("\n\n Available sensors:\n\n{}\n",
                sensors.stream()
                        .map(sensor -> "\tSensor " + sensor.getId() + " - " + sensor.getLocation().getCity() + ", " + sensor.getLocation().getDistrict())
                        .collect(joining("\n"))
        );
    }
}
