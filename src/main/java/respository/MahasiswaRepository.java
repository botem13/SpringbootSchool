package respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Mahasiswa;


@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long>{

	Optional<Mahasiswa> findAll(String mahasiswaId);

}
