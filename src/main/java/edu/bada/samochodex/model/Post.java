package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "poczty")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_poczty", unique = true)
    private Long id;

    @Column(name = "kod_pocztowy", unique = true, nullable = false)
    private String kod;

    @Column(name = "poczta", nullable = false)
    private String poczta;

    public Post(String kod, String poczta) {
        this.kod = kod;
        this.poczta = poczta;
    }
}
