package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "silniki")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_silnika", unique = true)
    private Long id;

    @Column(name = "nazwa_silnika", unique = true, nullable = false)
    private String nazwa;

    @Column(name = "pojemnosc")
    private Integer pojemnosc;

    @Column(name = "km", nullable = false)
    private Integer KM;

    @Column(name = "moment_obrotowy", nullable = false)
    private Integer momentObrotowy;

    @Column(name = "stopien_sprezania")
    private Float stopienSprezania;

    @Column(name = "turbina")
    private String turbina;

    @Column(name = "rodzaj_paliwa", nullable = false)
    private String paliwo;

    @Column(name = "ilosc_cylindrow")
    private Integer cylindry;

    @Column(name = "olej_silnikowy_rodzaj")
    private String rodzajOleju;

    @Column(name = "olej_silnikowy_ilosc")
    private Integer iloscOleju;

    @Column(name = "masa", nullable = false)
    private Integer masa;

    public Engine(String nazwa, Integer KM, Integer momentObrotowy, String paliwo, Integer masa) {
        this.nazwa = nazwa;
        this.KM = KM;
        this.momentObrotowy = momentObrotowy;
        this.paliwo = paliwo;
        this.masa = masa;
    }
}
