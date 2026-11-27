package br.com.unisal.samuelaraujo.carros.repository;

import br.com.unisal.samuelaraujo.carros.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

// O JpaRepository ja oferece save, findAll, findById, deleteById, etc.
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
