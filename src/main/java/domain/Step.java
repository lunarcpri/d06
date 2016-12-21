package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;


import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Step extends domain.DomainEntity {

    private String picture;
    private String description;
    private String hints;
    private Recipe recipe;

    public Step()
    {
        super();
    }

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }


    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Step{" +
                "picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", hints='" + hints + '\'' +
                ", recipe=" + recipe +
                '}';
    }
}
