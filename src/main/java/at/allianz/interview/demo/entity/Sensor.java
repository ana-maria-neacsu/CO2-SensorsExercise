package at.allianz.interview.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_fk")
    private Location location;

    public long getId() {
        return id;
    }

    public Sensor setId(long id) {
        this.id = id;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Sensor setLocation(Location location) {
        this.location = location;
        return this;
    }
}
