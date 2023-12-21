package com.radik.entity;

import com.radik.entity.converters.RatingConverter;
import com.radik.entity.converters.SpecialFeaturesConverter;
import com.radik.entity.enums.Rating;
import com.radik.entity.enums.SpecialFeatures;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "film")
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private String releaseYear;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    @Column(name = "rental_duration")
    private Integer rentalDuration;
    @Column(name = "rental_rate")
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Integer lenght;
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;
    @Column(name = "rating")
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    @Column(name = "special_features")
    @Convert(converter = SpecialFeaturesConverter.class)
    private Set<SpecialFeatures> specialFeatures = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "film_category",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<>();
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actors = new HashSet<>();
    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public void addCategory(Category category) {
        categories.add(category);
        category.getFilms().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getFilms().remove(this);
    }
    public void addActor(Actor actor) {
        actors.add(actor);
        actor.getFilms().add(this);
    }
    public void removeActor(Actor actor) {
        actors.remove(actor);
        actor.getFilms().remove(this);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<SpecialFeatures> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(Set<SpecialFeatures> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", language=").append(language);
        sb.append(", originalLanguage=").append(originalLanguage);
        sb.append(", rentalDuration=").append(rentalDuration);
        sb.append(", rentalRate=").append(rentalRate);
        sb.append(", lenght=").append(lenght);
        sb.append(", replacementCost=").append(replacementCost);
        sb.append(", rating=").append(rating);
        sb.append(", specialFeatures=").append(specialFeatures);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (!Objects.equals(id, film.id)) return false;
        if (!Objects.equals(title, film.title)) return false;
        if (!Objects.equals(description, film.description)) return false;
        if (!Objects.equals(releaseYear, film.releaseYear)) return false;
        if (!Objects.equals(language, film.language)) return false;
        if (!Objects.equals(originalLanguage, film.originalLanguage))
            return false;
        if (!Objects.equals(rentalDuration, film.rentalDuration))
            return false;
        if (!Objects.equals(rentalRate, film.rentalRate)) return false;
        if (!Objects.equals(lenght, film.lenght)) return false;
        if (!Objects.equals(replacementCost, film.replacementCost))
            return false;
        if (rating != film.rating) return false;
        if (!Objects.equals(specialFeatures, film.specialFeatures))
            return false;
        if (!Objects.equals(categories, film.categories)) return false;
        if (!Objects.equals(actors, film.actors)) return false;
        return Objects.equals(lastUpdate, film.lastUpdate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (originalLanguage != null ? originalLanguage.hashCode() : 0);
        result = 31 * result + (rentalDuration != null ? rentalDuration.hashCode() : 0);
        result = 31 * result + (rentalRate != null ? rentalRate.hashCode() : 0);
        result = 31 * result + (lenght != null ? lenght.hashCode() : 0);
        result = 31 * result + (replacementCost != null ? replacementCost.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (specialFeatures != null ? specialFeatures.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
