package com.radik.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "film_text")
public class FilmText {

    @Id
    private Integer id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    @MapsId
    private Film film;
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    private String description;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FilmText{");
        sb.append("id=").append(id);
        sb.append(", film=").append(film);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmText filmText = (FilmText) o;

        if (!Objects.equals(id, filmText.id)) return false;
        if (!Objects.equals(film, filmText.film)) return false;
        if (!Objects.equals(title, filmText.title)) return false;
        return Objects.equals(description, filmText.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
