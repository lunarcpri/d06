package domain;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity {

    private String ticker;
    private String title;
    private Date created_at;
    private Date updated_at;
    private boolean read_only;
    private String picture;
    private String summary;
    private List<Quantity> quantities;
    private List<Step> steps;
    private User author;
    private Collection<Likes> likes;
    private Category category;
    private Collection<Comment> comments;
    private Collection<Contest> winnedContests;
    private Collection<Contest> qualifiedContests;

    public Recipe() {
        super();
    }

    @Column(unique = true)
    @Pattern(regexp = "^([0-9]{2})([0-9]{2})([0-9]{2})-([a-zA-Z]{4})$")
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @NotBlank
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @NotBlank
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    //Relationship

    @Valid
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    public List<Quantity> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Quantity> quantities) {
        this.quantities = quantities;
    }

    @Valid
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Valid
    @ManyToOne(optional = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    public Collection<Likes> getLikes() {
        return likes;
    }

    public void setLikes(Collection<Likes> likes) {
        this.likes = likes;
    }

    @Valid
    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @OneToMany(mappedBy = "recipe")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }


    public boolean isRead_only() {
        return read_only;
    }

    public void setRead_only(boolean read_only) {
        this.read_only = read_only;
    }

    @ManyToMany(cascade = CascadeType.DETACH)
    public Collection<Contest> getWinnedContests() {
        return winnedContests;
    }

    public void setWinnedContests(Collection<Contest> winnedContests) {
        this.winnedContests = winnedContests;
    }

    @ManyToMany(mappedBy = "recipesQualified")
    public Collection<Contest> getQualifiedContests() {
        return qualifiedContests;
    }

    public void setQualifiedContests(Collection<Contest> qualifiedContests) {
        this.qualifiedContests = qualifiedContests;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ticker='" + ticker + '\'' +
                ", title='" + title + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", read_only=" + read_only +
                ", picture='" + picture + '\'' +
                ", summary='" + summary + '\'' +
                ", quantities=" + quantities +
                ", steps=" + steps +
                ", author=" + author +
                ", likes=" + likes +
                ", category=" + category +
                ", comments=" + comments +
                ", winnedContests=" + winnedContests +
                '}';
    }
}

