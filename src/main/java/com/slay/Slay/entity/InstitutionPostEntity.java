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
@Table(name = "institution_post")
public class InstitutionPostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле название - обязательно")
    @Size(max = 200)
    private String title;

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    @Size(max = 1000)
    private String text = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insitution_id")
    private InstitutionEntity institution;

    @ElementCollection
    @CollectionTable(name = "post_institution_images")
    private List<String> images;

    public InstitutionPostEntity(Long id, String title, Date dateCreate, String text, UserEntity author, InstitutionEntity institution, List<String> images) {
        this.id = id;
        this.title = title;
        this.dateCreate = dateCreate;
        this.text = text;
        this.author = author;
        this.institution = institution;
        this.images = images;
    }
}

