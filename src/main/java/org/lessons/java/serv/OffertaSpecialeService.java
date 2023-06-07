package org.lessons.java.serv;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pojo.OffertaSpeciale;
import org.lessons.java.pojo.Pizza;
import org.lessons.java.repo.OffertaSpecialeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaSpecialeService {
	
	@Autowired
	private OffertaSpecialeRepo offertaSpecialeRepo;
	
	public List<OffertaSpeciale> findAll() {
		
		return offertaSpecialeRepo.findAll();
	}
	public Optional<OffertaSpeciale> findById(int id) {
		
		return offertaSpecialeRepo.findById(id);
	}
	public OffertaSpeciale save(OffertaSpeciale offertaSpeciale) {
		
		return offertaSpecialeRepo.save(offertaSpeciale);
	}
	public void delete(OffertaSpeciale offerta) {
		
		offertaSpecialeRepo.delete(offerta);
	}
}
