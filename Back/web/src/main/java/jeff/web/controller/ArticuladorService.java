package jeff.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jeff.web.model.Articulador;
import jeff.web.repository.ArticuladorRepository;
import jeff.web.util.Upload;

@RestController
@RequestMapping(path = "/api/cursos")
@CrossOrigin
public class ArticuladorService {
	@Autowired
	private ArticuladorRepository cursos;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Articulador>> getCursos() {
		return new ResponseEntity<List<Articulador>>(
				cursos.findAll(new Sort(Sort.Direction.ASC, "id")), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Articulador> getCurso(@PathVariable("id") Integer id) {
		Optional<Articulador> articulador = cursos.findById(id);
		if (articulador.isPresent()) {
			return new ResponseEntity<Articulador>(articulador.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Articulador> getCurso(@RequestParam("nome") String nome) {
		Articulador articulador = cursos.findByNome(nome);
		if (articulador != null) {
			return new ResponseEntity<Articulador>(articulador, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Articulador> addCurso(String nome, String matricula, String celula, String senha, MultipartFile image) {
		if (nome == null || matricula == null || celula == null || nome.equals("null") || matricula.equals("null") || celula.equals("null") || matricula.length() != 6 || !matricula.substring(0).matches("[0-9]*") || image == null || senha.length() < 6) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Articulador articulador = new Articulador(null, nome, matricula, celula, senha);
		Articulador cursoAux = cursos.save(articulador);
		try {
			Upload.uploadFile(image.getInputStream(), cursoAux.getId());
		} catch (IOException e) {
		}
		return new ResponseEntity<Articulador>(articulador, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Articulador> atualizarCurso(@PathVariable("id") int id, String nome, String matricula, String celula, String senha,
			MultipartFile image) {
		if (nome == null || matricula == null || celula == null || nome.equals("null") || matricula.equals("null") || celula.equals("null") || matricula.length() != 6 || !matricula.substring(0).matches("[0-9]*")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Optional<Articulador> articulador = cursos.findById(id);
		if (articulador.isPresent()) {
			articulador.get().setNome(nome);
			articulador.get().setMatricula(matricula);
			articulador.get().setCelula(celula);
			cursos.save(articulador.get());
			try {
				if (image != null) {
					Upload.uploadFile(image.getInputStream(), id);
				}
			} catch (IOException e) {
			}
			return new ResponseEntity<Articulador>(articulador.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletarCurso(@PathVariable("id") int id) {
		if (cursos.existsById(id)) {
			cursos.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}


