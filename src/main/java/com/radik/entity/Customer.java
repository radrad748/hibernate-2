package com.radik.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Column(name = "email", length = 50)
    private String email;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "active", columnDefinition = "BIT", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean active;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private LocalDateTime creaeDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getCreaeDate() {
        return creaeDate;
    }

    public void setCreaeDate(LocalDateTime creaeDate) {
        this.creaeDate = creaeDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", store=").append(store);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address=").append(address);
        sb.append(", active=").append(active);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", creaeDate=").append(creaeDate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (active != customer.active) return false;
        if (!Objects.equals(id, customer.id)) return false;
        if (!Objects.equals(store, customer.store)) return false;
        if (!Objects.equals(firstName, customer.firstName)) return false;
        if (!Objects.equals(lastName, customer.lastName)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }
}
