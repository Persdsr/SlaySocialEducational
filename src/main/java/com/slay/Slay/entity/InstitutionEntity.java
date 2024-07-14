package com.slay.Slay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "institution")
@Setter
@Getter
@NoArgsConstructor
public class InstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле название - обязательно")
    @Size(min = 3, max = 70)
    private String name;

    @Size(max = 2000)
    private String description = "";

    @NotNull(message = "Поле дата открытия - обязательно")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "opening_date")
    private Date openingDate;

    private String icon;

    private Long rating = 0L;

    @ElementCollection
    @CollectionTable(name = "institution_screenshots", joinColumns = @JoinColumn(name = "institution_id"))
    private List<String> screenshots;

    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
    private Set<UserEntity> students = new HashSet<>();

    @ManyToMany(mappedBy = "institutionSubscription")
    private Set<UserEntity> subscribers = new HashSet<>();

    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
    private List<ProfessionEntity> professions;

    @OneToMany(mappedBy = "institution")
    private List<InstitutionPostEntity> posts;

    @ManyToMany(mappedBy = "editing")
    private List<UserEntity> editors;

    public InstitutionEntity(Long id, String name, String description, Date openingDate, String icon, Long rating, List<String> screenshots, Set<UserEntity> students, Set<UserEntity> subscribers, List<ProfessionEntity> professions, List<InstitutionPostEntity> posts, List<UserEntity> editors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openingDate = openingDate;
        this.icon = icon;
        this.rating = rating;
        this.screenshots = screenshots;
        this.students = students;
        this.subscribers = subscribers;
        this.professions = professions;
        this.posts = posts;
        this.editors = editors;
    }
}