package com.radik.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "store_id")
    private Store store;
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventory{");
        sb.append("id=").append(id);
        sb.append(", film=").append(film);
        sb.append(", store=").append(store);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        if (!Objects.equals(id, inventory.id)) return false;
        if (!Objects.equals(film, inventory.film)) return false;
        return Objects.equals(store, inventory.store);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        return result;
    }
}
