package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "samochody")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_samochodu", unique = true)
    private Long id;

    @Column(name = "rok_produkcji", nullable = false)
    private Integer rokProdukcji;

    @Column(name = "kraj_produkcji", nullable = false)
    private String krajProdukcji;

    @Column(name = "rodzaj_napedu", nullable = false,
            columnDefinition = "CHECK (rodzaj_napedu IN ('przod', 'tyl', '4x4'))")
    private String naped;

    @Column(name = "skrzynia_biegow",
            columnDefinition = "CHECK (skrzynia_biegow IN ('automatyczna', 'manualna'))")
    private String skrzyniaBiegow;

    @Column(name = "pojemnosc_baku")
    private Integer pojemnoscBaku;

    @Column(name = "pojemnosc_bagaznika")
    private Integer pojemnoscBagaznika;

    @Column(name = "pojemnosc_baterii")
    private Float pojemnoscBaterii;

    @Column(name = "ilosc_miejsc", nullable = false)
    private Integer iloscMiejsc;

    @Column(name = "ilosc_drzwi", nullable = false)
    private Integer iloscDrzwi;

    @Column(name = "opony", nullable = false)
    private String opony;

    @Column(name = "rodzaj_nadwozia", nullable = false)
    private String nadwozie;

    @Column(name = "kolor_nadwozia", nullable = false)
    private String kolorNadwozia;

    @Column(name = "kolor_tapicerki", nullable = false)
    private String kolorTapicerki;

    @Column(name = "numer_vin", nullable = false)
    private String VIN;

    @Column(name = "na_sprzedaz", nullable = false)
    private Boolean naSprzedaz;

    @Column(name = "cena", nullable = false)
    private Float cena;

    @Column(name = "znizka")
    private Integer znizka;

    @Column(name = "rata")
    private Float rata;

    @ManyToOne
    @JoinColumn(name = "id_salonu", foreignKey = @ForeignKey(name = "posiada_samochody"), nullable = false)
    private CarDealer carDealer;

    @ManyToOne
    @JoinColumn(name = "id_wyposazenia", foreignKey = @ForeignKey(name = "ma_wyposazenie"), nullable = false)
    private EquipmentVersion equipmentVersion;

    @ManyToOne
    @JoinColumn(name = "id_silnika", foreignKey = @ForeignKey(name = "ma_silnik"), nullable = false)
    private Engine engine;

    @ManyToOne
    @JoinColumn(name = "id_modelu", foreignKey = @ForeignKey(name = "samochod_ma_model"), nullable = false)
    private CarModel carModel;

    public Car(Integer rokProdukcji, String krajProdukcji, String naped, Integer iloscMiejsc, Integer iloscDrzwi,
               String opony, String nadwozie, String kolorNadwozia, String kolorTapicerki, String VIN, Boolean naSprzedaz,
               Float cena, CarDealer carDealer, EquipmentVersion equipmentVersion, Engine engine, CarModel carModel) {
        this.rokProdukcji = rokProdukcji;
        this.krajProdukcji = krajProdukcji;
        this.naped = naped;
        this.iloscMiejsc = iloscMiejsc;
        this.iloscDrzwi = iloscDrzwi;
        this.opony = opony;
        this.nadwozie = nadwozie;
        this.kolorNadwozia = kolorNadwozia;
        this.kolorTapicerki = kolorTapicerki;
        this.VIN = VIN;
        this.naSprzedaz = naSprzedaz;
        this.cena = cena;
        this.carDealer = carDealer;
        this.equipmentVersion = equipmentVersion;
        this.engine = engine;
        this.carModel = carModel;
    }
}
