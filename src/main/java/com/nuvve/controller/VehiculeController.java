package com.nuvve.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nuvve.model.VehiculeEntity;
import com.nuvve.repository.VehiculeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VehiculeController {
	
	@Autowired
	VehiculeRepository vehiculeRepository;

	@GetMapping("/vehicules")
	public ResponseEntity<List<VehiculeEntity>> getAllVehicules(@RequestParam(required = false) String model) {
		try {
			List<VehiculeEntity> vehicules = new ArrayList<VehiculeEntity>();

			if (Objects.isNull(model)) {
				vehiculeRepository.findAll().forEach(vehicules::add);
			} else {
				vehiculeRepository.findByModelContaining(model).forEach(vehicules::add);
			}

			if (vehicules.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(vehicules, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/vehicules/{id}")
	public ResponseEntity<VehiculeEntity> getVehiculeById(@PathVariable("id") long id) {
		Optional<VehiculeEntity> vehiculeData = vehiculeRepository.findById(id);

		if (vehiculeData.isPresent()) {
			return new ResponseEntity<>(vehiculeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/vehicules")
	public ResponseEntity<VehiculeEntity> createVehicule(@RequestBody VehiculeEntity vehiculeEntity) {
		try {
			VehiculeEntity _vehiculeEntity = vehiculeRepository
					.save( new VehiculeEntity(vehiculeEntity.getCompany(), vehiculeEntity.getModel(), vehiculeEntity.getYear()));
			return new ResponseEntity<>(_vehiculeEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/vehicules/{id}")
	public ResponseEntity<VehiculeEntity> updateVehicule(@PathVariable("id") long id, @RequestBody VehiculeEntity vehiculeEntity) {
		Optional<VehiculeEntity> vehiculeData = vehiculeRepository.findById(id);

		if (vehiculeData.isPresent()) {
			VehiculeEntity _vehiculeEntity = vehiculeData.get();
			_vehiculeEntity.setCompany(vehiculeEntity.getCompany());
			_vehiculeEntity.setModel(vehiculeEntity.getModel());
			_vehiculeEntity.setYear(vehiculeEntity.getYear());
			return new ResponseEntity<>(vehiculeRepository.save(_vehiculeEntity), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/vehicule/{id}")
	public ResponseEntity<HttpStatus> deleteVehicule(@PathVariable("id") long id) {
		try {
			vehiculeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/vehicules")
	public ResponseEntity<HttpStatus> deleteAllVehicule() {
		try {
			vehiculeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/vehicules/published")
	public ResponseEntity<List<VehiculeEntity>> findByCompany(@RequestParam(required = true) String company) {
		
		if(Objects.isNull(company) || company.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			List<VehiculeEntity> vehicules = vehiculeRepository.findByCompany(company);

			if (vehicules.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(vehicules, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
