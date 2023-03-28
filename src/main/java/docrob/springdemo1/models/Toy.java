package docrob.springdemo1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "toys")
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="cats_toys",
            joinColumns={@JoinColumn(name="toy_id")},
            inverseJoinColumns={@JoinColumn(name="cat_id")}
    )
    @ToString.Exclude
    private List<Cat> cats;
}
