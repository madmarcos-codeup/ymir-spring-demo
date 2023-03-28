package docrob.springdemo1.repositories;

import docrob.springdemo1.models.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Long> {

}
