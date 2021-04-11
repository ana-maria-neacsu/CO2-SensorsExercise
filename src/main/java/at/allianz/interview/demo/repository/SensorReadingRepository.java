package at.allianz.interview.demo.repository;

import at.allianz.interview.demo.entity.Sensor;
import at.allianz.interview.demo.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensor(Sensor sensor);

}
