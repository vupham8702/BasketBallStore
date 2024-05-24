package com.project.basketballstore.model.news;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.basketballstore.model.image.ImageModel;
import com.project.basketballstore.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "create-at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createAt;


    @Column(name = "desscribe")
    private String describe;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    @JoinTable(name = "news_images", joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageModel> newsImage;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createAt=" + createAt +
                ", describe='" + describe + '\'' +
                ", content='" + content + '\'' +
                ", newsImage=" + newsImage +
                ", user=" + user +
                '}';
    }
}
