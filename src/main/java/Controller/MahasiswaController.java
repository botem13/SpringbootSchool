package Controller;



import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Mahasiswa;
import respository.MahasiswaRepository;


@RestController
@RequestMapping("/api/v3")
public class MahasiswaController {
	@Autowired
	private MahasiswaRepository MahasiswaRepository;

	@GetMapping("/mahasiswa")
	public List<Mahasiswa> getAllMahasiswa() {
		return MahasiswaRepository.findAll();
	}

	@GetMapping("/mahasiswa/{id}")
	public ResponseEntity<Mahasiswa> getMahasiswaByid(@PathVariable(value = "Id") long MahasiswaId)
			throws ResourceNotFoundException {
		Mahasiswa mahasiswa = MahasiswaRepository.findById(MahasiswaId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + MahasiswaId));
		return ResponseEntity.ok().body(mahasiswa);
	}

	@PostMapping("/mahasiswa")
	public Mahasiswa createMahasiswa(@Valid @RequestBody Mahasiswa mahasiswa) {
		return MahasiswaRepository.save(mahasiswa);
	}

	@PutMapping("/mahasiswa/{id}")
	public ResponseEntity<Mahasiswa> updateMahasiswa(@PathVariable(value = "id") Long mahasiswaId,
			@Valid @RequestBody Mahasiswa mahasiswaDetails) throws ResourceNotFoundException {
		Mahasiswa mahasiswa = MahasiswaRepository.findById(mahasiswaId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + mahasiswaId));

		Mahasiswa.setNama(mahasiswaDetails.getNama());
		Mahasiswa.setAlamat(mahasiswaDetails.getAlamat());
		
		final Mahasiswa updatedMahasiswa = MahasiswaRepository.save(mahasiswa);
		return ResponseEntity.ok(updatedMahasiswa);
	}

	@DeleteMapping("/mahasiswa/{id}")
	public Map<String, Boolean> deleteMahasiswa(@PathVariable(value = "id") Long MahasiswaId)
			throws ResourceNotFoundException {
		Mahasiswa mahasiswa = MahasiswaRepository.findById(MahasiswaId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + MahasiswaId));

		MahasiswaRepository.delete(mahasiswa);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
