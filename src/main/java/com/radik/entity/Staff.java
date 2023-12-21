package com.radik.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Lob
    @Column(name = "picture", columnDefinition = "BLOB")
    private byte[] picture;
    @Column(name = "email", length = 50)
    private String email;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(name = "active", columnDefinition = "BIT", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;
    @Column(name = "username", nullable = false, length = 16)
    private String username;
    @Column(name = "password", length = 40)
    private String password;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Staff{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", email='").append(email).append('\'');
        sb.append(", store=").append(store);
        sb.append(", active=").append(active);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (active != staff.active) return false;
        if (!Objects.equals(id, staff.id)) return false;
        if (!Objects.equals(firstName, staff.firstName)) return false;
        if (!Objects.equals(lastName, staff.lastName)) return false;
        if (!Objects.equals(address, staff.address)) return false;
        if (!Arrays.equals(picture, staff.picture)) return false;
        if (!Objects.equals(email, staff.email)) return false;
        if (!Objects.equals(store, staff.store)) return false;
        if (!Objects.equals(username, staff.username)) return false;
        return Objects.equals(password, staff.password);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
