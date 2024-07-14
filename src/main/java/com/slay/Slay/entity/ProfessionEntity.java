package com.slay.Slay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profession")
public class ProfessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле название - обязательно")
    @Size(min = 4, max = 70)
    private String name;

    @NotBlank(message = "Поле описание - обязательно")
    @Size(max = 2000)
    private String description;

    @NotBlank(message = "Поле год обучения - обязательно")
    @Max(10)
    @Column(name = "year_study")
    private int yearStudy = 0;

    @NotBlank(message = "Поле год обучения - обязательно")
    @Max(12)
    @Column(name = "months_study")
    private int monthsStudy = 0;

    @NotBlank(message = "Поле год обучения - обязательно")
    @Max(10000000)
    private Float price = 0F;

    private String image;

    @Column(name = "detail_url")
    private String detailUrl;

    @ManyToOne()
    @JoinColumn(name = "institution_id")
    private InstitutionEntity institution;

    public ProfessionEntity(Long id, String name, String description, int yearStudy, int monthsStudy, Float price, String image, String detailUrl, InstitutionEntity institution) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearStudy = yearStudy;
        this.monthsStudy = monthsStudy;
        this.price = price;
        this.image = image;
        this.detailUrl = detailUrl;
        this.institution = institution;
    }
}
