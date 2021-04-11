package at.allianz.interview.demo.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "value")
    private double value;

    @Column(name = "timestamp")
    private Instant timestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sensor_fk")
    private Sensor sensor;

    public long getId() {
        return id;
    }

    public SensorReading setId(long id) {
        this.id = id;
        return this;
    }

    public double getValue() {
        return value;
    }

    public SensorReading setValue(double value) {
        this.value = value;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public SensorReading setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public SensorReading setSensor(Sensor sensor) {
        this.sensor = sensor;
        return this;
    }
}
