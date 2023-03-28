package docrob.springdemo1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 10)
    private String gender;

    @Column
    private int age;

    @ManyToOne
    private PetOwner owner;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="cats_toys",
            joinColumns={@JoinColumn(name="cat_id")},
            inverseJoinColumns={@JoinColumn(name="toy_id")}
    )
    private List<Toy> toys;

}
