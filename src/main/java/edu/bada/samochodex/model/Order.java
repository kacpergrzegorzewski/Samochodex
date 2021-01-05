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
@Table(name = "zamowienia")
public class Order {

    @Id
    @SequenceGenerator(name = "order_gen", sequenceName = "zamowieniaseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @Column(name = "id_zamowienia", unique = true)
    private Long id;

    @Column(name = "data", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_pracownika", foreignKey = @ForeignKey(name = "podpisuje_zamowienie"), nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_samochodu", foreignKey = @ForeignKey(name = "jest_w_zamowieniu"), nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_klienta", foreignKey = @ForeignKey(name = "sklada_zamowienie"), nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_salonu", foreignKey = @ForeignKey(name = "obsluguje_zamowienie"), nullable = false)
    private CarDealer carDealer;

    public Order(Date data, Employee employee, Car car, Client client, CarDealer carDealer) {
        this.data = data;
        this.employee = employee;
        this.car = car;
        this.client = client;
        this.carDealer = carDealer;
    }
}
