package at.allianz.interview.demo.dto;

import java.time.Instant;
import java.util.Objects;

public class SensorReadingDto {
    private Instant timestamp;
    private double value;

    public SensorReadingDto() {
    }

    public SensorReadingDto(Instant timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public SensorReadingDto setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public double getValue() {
        return value;
    }

    public SensorReadingDto setValue(double value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorReadingDto that = (SensorReadingDto) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, value);
    }

    @Override
    public String toString() {
        return "SensorReadingDto{" +
                "timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}
