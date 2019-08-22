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
import model.Dosen;
import respository.DosenRepository;


@RestController
@RequestMapping("/api/v4")
public class DosenController {
	@Autowired
	private DosenRepository DosenRepository;

	@GetMapping("/dosen")
	public List<Dosen> getAllDosen() {
		return DosenRepository.findAll();
	}

	@GetMapping("/dosen/{id}")
	public ResponseEntity<Dosen> getDosenByid(@PathVariable(value = "Id") long DosenId)
			throws ResourceNotFoundException {
		Dosen dosen = DosenRepository.findById(DosenId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + DosenId));
		return ResponseEntity.ok().body(dosen);
	}

	@PostMapping("/Dosen")
	public Dosen createDosen(@Valid @RequestBody Dosen dosen) {
		return DosenRepository.save(dosen);
	}

	@PutMapping("/dosen/{id}")
	public ResponseEntity<Dosen> updateDosen(@PathVariable(value = "id") Long DosenId,
			@Valid @RequestBody Dosen dosenDetails) throws ResourceNotFoundException {
		Dosen dosen = DosenRepository.findById(DosenId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + DosenId));

		Dosen.setNama(dosenDetails.getNama());
		Dosen.setAlamat(dosenDetails.getAlamat());
		
		final Dosen updatedDosen = DosenRepository.save(dosen);
		return ResponseEntity.ok(updatedDosen);
	}

	@DeleteMapping("/dosen/{id}")
	public Map<String, Boolean> deleteDosen(@PathVariable(value = "id") Long DosenId)
			throws ResourceNotFoundException {
		Dosen dosen = DosenRepository.findById(DosenId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + DosenId));

		DosenRepository.delete(dosen);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
