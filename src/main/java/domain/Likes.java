package domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Likes  extends DomainEntity{

    private Boolean isLike;
    private UserOrNutritionist userOrNutritionist;
    private Recipe recipe;

    @NotNull
    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        isLike = like;
    }

    @ManyToOne
    public UserOrNutritionist getUserOrNutritionist() {
        return userOrNutritionist;
    }

    public void setUserOrNutritionist(UserOrNutritionist userOrNutritionist) {
        this.userOrNutritionist = userOrNutritionist;
    }

    @ManyToOne
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
