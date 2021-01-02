package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pracownicy")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pracownika", unique = true)
    private Long id;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    @Column(name = "data_zatrudnienia", nullable = false)
    private Date dataZatrudniena;

    @Column(name = "nr_konta", nullable = false)
    private String nrKonta;

    @Column(name = "nr_telefonu", nullable = false)
    private String nrTelefonu;

    @Column(name = "adres_email", nullable = false)
    private String email;

    @Column(name = "data_urodzenia", nullable = false)
    private Date dataUrodzenia;

    @Column(name = "plec", nullable = false)
    private String plec;

    @Column(name = "pesel", nullable = false)
    private String PESEL;

    @ManyToOne
    @JoinColumn(name = "id_salonu", foreignKey = @ForeignKey(name = "pracuja_w_salonie"), nullable = false)
    private CarDealer carDealer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresu", foreignKey = @ForeignKey(name = "pracownik_ma_adres"), nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "id_stanowiska", foreignKey = @ForeignKey(name = "pracuje_na_stanowisku"), nullable = false)
    private Position position;

    public Employee(String imie, String nazwisko, Date dataZatrudniena, String nrKonta, String nrTelefonu, String email,
                    Date dataUrodzenia, String plec, CarDealer carDealer, Address address, Position position) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudniena = dataZatrudniena;
        this.nrKonta = nrKonta;
        this.nrTelefonu = nrTelefonu;
        this.email = email;
        this.dataUrodzenia = dataUrodzenia;
        this.plec = plec;
        this.carDealer = carDealer;
        this.address = address;
        this.position = position;
    }
}
