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
@Table(name = "klienci")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_klienta", unique = true)
    private Long id;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    @Column(name = "nr_telefonu")
    private String nrTelefonu;

    @Column(name = "adres_email", nullable = false)
    private String email;

    @Column(name = "data_urodzenia")
    private Date dataUrodzenia;

    @Column(name = "plec")
    private String plec;

    @Column(name = "pesel")
    private String PESEL;

    @ManyToOne
    @JoinColumn(name = "id_adresu", foreignKey = @ForeignKey(name = "klient_ma_adres"), nullable = false)
    private Address address;

    public Client(String imie, String nazwisko, String email, Address address) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.address = address;
    }
}
