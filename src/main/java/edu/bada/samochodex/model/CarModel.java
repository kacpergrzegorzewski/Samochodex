package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "modele")
public class CarModel {

    @Id
    @SequenceGenerator(name = "carModel_gen", sequenceName = "modeleseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carModel_gen")
    @Column(name = "id_modelu", unique = true)
    private Long id;

    @Column(name = "nazwa_modelu", nullable = false)
    private String nazwa;

    @Column(name = "generacja")
    private Integer generacja;

    @ManyToOne
    @JoinColumn(name = "id_marki", foreignKey = @ForeignKey(name = "nalezy_do_marki"), nullable = false)
    private CarBrand carBrand;

    public CarModel(String nazwa, CarBrand carBrand) {
        this.nazwa = nazwa;
        this.carBrand = carBrand;
    }
}
