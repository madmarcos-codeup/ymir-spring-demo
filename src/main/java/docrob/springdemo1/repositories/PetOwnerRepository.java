package docrob.springdemo1.repositories;

import docrob.springdemo1.models.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

}
