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
@Table(name = "salony")
public class CarDealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_salonu", unique = true)
    private Long id;

    @Column(name = "nazwa_salonu", unique = true, nullable = false)
    private String nazwa;

    @Column(name = "data_zalozenia", nullable = false)
    private Date dataZalozenia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresu", foreignKey = @ForeignKey(name = "salon_ma_adres"), nullable = false)
    private Address address;

    public CarDealer(String nazwa, Date dataZalozenia, Address address) {
        this.nazwa = nazwa;
        this.dataZalozenia = dataZalozenia;
        this.address = address;
    }
}
