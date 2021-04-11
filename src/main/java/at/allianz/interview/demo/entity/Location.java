package at.allianz.interview.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    public long getId() {
        return id;
    }

    public Location setId(long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Location setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Location setDistrict(String district) {
        this.district = district;
        return this;
    }
}
