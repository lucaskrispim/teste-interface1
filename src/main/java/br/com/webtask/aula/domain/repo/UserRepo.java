package br.com.webtask.aula.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webtask.aula.domain.model.UserClient;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserClient, Long>{

    public Optional<UserClient> findByName(String nome);

    public Optional<UserClient> findByCpf(String cpf);

    public Optional<UserClient> findById(Long id);

}
