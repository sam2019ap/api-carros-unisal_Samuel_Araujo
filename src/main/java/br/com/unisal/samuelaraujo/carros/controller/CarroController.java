package br.com.unisal.samuelaraujo.carros.controller;

import br.com.unisal.samuelaraujo.carros.model.Carro;
import br.com.unisal.samuelaraujo.carros.repository.CarroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository repository;

    public CarroController(CarroRepository repository) {
        this.repository = repository;
    }

    // POST /carros - cadastra um novo veiculo
    @PostMapping
    public ResponseEntity<Carro> cadastrar(@RequestBody Carro carro) {
        // Garante que sera criado um registro novo, mesmo que o JSON traga um id
        carro.setId(null);
        Carro salvo = repository.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /carros - lista todos os veiculos
    @GetMapping
    public List<Carro> listar() {
        return repository.findAll();
    }

    // GET /carros/{id} - consulta um veiculo pelo id (404 se nao existir)
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /carros/{id} - atualiza os dados de um veiculo (404 se nao existir)
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable Long id, @RequestBody Carro dados) {
        return repository.findById(id)
                .map(carro -> {
                    carro.setMarca(dados.getMarca());
                    carro.setModelo(dados.getModelo());
                    carro.setAnoFabricacao(dados.getAnoFabricacao());
                    carro.setAnoModelo(dados.getAnoModelo());
                    carro.setMotorizacao(dados.getMotorizacao());
                    return ResponseEntity.ok(repository.save(carro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /carros/{id} - exclui um veiculo (204 se excluiu, 404 se nao existir)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
