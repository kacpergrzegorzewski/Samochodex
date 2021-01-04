package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "stanowiska")
public class Position {

    @Id
    @SequenceGenerator(name = "position_gen", sequenceName = "stanowiskaseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_gen")
    @Column(name = "id_stanowiska", unique = true)
    private Long id;

    @Column(name = "nazwa_stanowiska", unique = true, nullable = false)
    private String nazwa;

    @Column(name = "zakres_obowiazkow")
    private String obowiazki;

    public Position(String nazwa) {
        this.nazwa = nazwa;
    }
}
