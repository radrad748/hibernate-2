package com.radik.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Column(name = "address2", length = 50)
    private String address2;
    @Column(name = "district", nullable = false, length = 20)
    private String district;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @Column(name = "postal_code", length = 10)
    private String postal_code;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", address='").append(address).append('\'');
        sb.append(", address2='").append(address2).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", city=").append(city);
        sb.append(", postal_code='").append(postal_code).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (!Objects.equals(id, address1.id)) return false;
        if (!Objects.equals(address, address1.address)) return false;
        if (!Objects.equals(address2, address1.address2)) return false;
        if (!Objects.equals(district, address1.district)) return false;
        if (!Objects.equals(city, address1.city)) return false;
        if (!Objects.equals(postal_code, address1.postal_code))
            return false;
        return Objects.equals(phone, address1.phone);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postal_code != null ? postal_code.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
