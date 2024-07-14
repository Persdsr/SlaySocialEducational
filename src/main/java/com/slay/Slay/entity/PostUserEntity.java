package com.slay.Slay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_post")
public class PostUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле название - обязательно")
    @Size(min = 2, max = 70)
    private String title;

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    @Size(max = 2000)
    private String text = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ElementCollection
    @CollectionTable(name = "post_user_images")
    private List<String> images;

    public PostUserEntity(Long id, String title, Date dateCreate, String text, UserEntity author, List<String> images) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.text = text;
        this.author = author;
        this.images = images;
    }
}
