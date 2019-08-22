package respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Dosen;


@Repository
public interface DosenRepository extends JpaRepository<Dosen, Long>{

	Optional<Dosen> findAll(String dosenId);

}
