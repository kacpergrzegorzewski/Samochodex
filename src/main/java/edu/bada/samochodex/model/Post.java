package edu.bada.samochodex.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "poczty")
public class Post {

    @Id
    @SequenceGenerator(name = "post_gen", sequenceName = "pocztyseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @Column(name = "id_poczty", unique = true)
    private Long id;

    @Column(name = "kod_pocztowy", unique = true, nullable = false)
    private String kodPocztowy;

    @Column(name = "poczta", nullable = false)
    private String poczta;

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<Address> adresy = new ArrayList<>();

    public Post(String kodPocztowy, String poczta) {
        this.kodPocztowy = kodPocztowy;
        this.poczta = poczta;
    }

//    public void addAddress(Address address) {
//        address.setPost(this);
//        adresy.add(address);
//    }
}
