package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "wersje_wyposazenia")
public class EquipmentVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_wyposazenia", unique = true)
    private Long id;

    @Column(name = "nazwa_wyposazenia", unique = true, nullable = false)
    private String nazwa;

    @Column(name = "fotele_z_masazem", nullable = false)
    private Boolean foteleZMasazem;

    @Column(name = "zegary_elektoroniczne", nullable = false)
    private Boolean zegaryElektroniczne;

    @Column(name = "system_multimedialny", nullable = false)
    private Boolean systemMultimedialny;

    @Column(name = "nawigacja_gps", nullable = false)
    private Boolean GPS;

    @Column(name = "swiatla", nullable = false)
    private String swiatla;

    @Column(name = "climatronic", nullable = false)
    private Boolean climationic;

    @Column(name = "ilosc_stref_klimatyzacji", nullable = false)
    private Integer strefyKlimatyzacji;

    @Column(name = "system_audio", nullable = false)
    private String audio;

    @Column(name = "podswietlenie_ambient", nullable = false)
    private Boolean podswietlenie;

    @Column(name = "ilosc_portow_usb", nullable = false)
    private Integer portyUSB;

    @Column(name = "bluetooth", nullable = false)
    private Boolean bluetooth;

    @Column(name = "aux", nullable = false)
    private Boolean AUX;

    public EquipmentVersion(String nazwa, Boolean foteleZMasazem, Boolean zegaryElektroniczne, Boolean systemMultimedialny,
                            Boolean GPS, String swiatla, Boolean climationic, Integer strefyKlimatyzacji, String audio,
                            Boolean podswietlenie, Integer portyUSB, Boolean bluetooth, Boolean AUX) {
        this.nazwa = nazwa;
        this.foteleZMasazem = foteleZMasazem;
        this.zegaryElektroniczne = zegaryElektroniczne;
        this.systemMultimedialny = systemMultimedialny;
        this.GPS = GPS;
        this.swiatla = swiatla;
        this.climationic = climationic;
        this.strefyKlimatyzacji = strefyKlimatyzacji;
        this.audio = audio;
        this.podswietlenie = podswietlenie;
        this.portyUSB = portyUSB;
        this.bluetooth = bluetooth;
        this.AUX = AUX;
    }
}
