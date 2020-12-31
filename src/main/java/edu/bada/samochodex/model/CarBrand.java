package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "marki")
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_marki", unique = true)
    private Long id;

    @Column(name = "nazwa_marki", nullable = false, unique = true)
    private String nazwa;

    public CarBrand(String nazwa) {
        this.nazwa = nazwa;
    }
}
