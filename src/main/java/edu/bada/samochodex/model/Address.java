package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "adresy")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adresu", unique = true)
    private Long id;

    @Column(name = "miejscowosc", nullable = false)
    private String miejscowosc;

    @Column(name = "ulica", nullable = false)
    private String ulica;

    @Column(name = "nr_budynku", nullable = false)
    private String nrBudynku;

    @Column(name = "nr_mieszkania")
    private String nrMieszkania;

    @ManyToOne
    @JoinColumn(name = "id_poczty", foreignKey = @ForeignKey(name = "ma_poczte"), nullable = false)
    private Post post;

    public Address(String miejscowosc, String ulica, String nrBudynku, Post post) {
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
        this.post = post;
    }
}
