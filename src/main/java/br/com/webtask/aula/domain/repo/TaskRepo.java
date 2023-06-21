package br.com.webtask.aula.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webtask.aula.domain.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{

	List<Task> findByTaskDescription(String taskName);

	List<Task> findByUserId(long idUser);

    public List<Task> findByUserIdOrderByPlannedDateDesc(long id);

}
