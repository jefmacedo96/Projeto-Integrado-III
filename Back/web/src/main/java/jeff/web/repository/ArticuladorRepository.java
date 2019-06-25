package jeff.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jeff.web.model.Articulador;

	public interface ArticuladorRepository extends JpaRepository<Articulador, Integer> {
		@Query("from Articulador where nome = ?1")
		Articulador findByNome(String nome);
	}
	
	

