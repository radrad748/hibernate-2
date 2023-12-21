package com.radik.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id")
    private Staff staff;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rental_id")
    private Rental rental;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", customer=").append(customer);
        sb.append(", staff=").append(staff);
        sb.append(", rental=").append(rental);
        sb.append(", amount=").append(amount);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (!Objects.equals(id, payment.id)) return false;
        if (!Objects.equals(customer, payment.customer)) return false;
        if (!Objects.equals(staff, payment.staff)) return false;
        if (!Objects.equals(rental, payment.rental)) return false;
        if (!Objects.equals(amount, payment.amount)) return false;
        return Objects.equals(paymentDate, payment.paymentDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (rental != null ? rental.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        return result;
    }
}
