package docrob.springdemo1.repositories;

import docrob.springdemo1.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
