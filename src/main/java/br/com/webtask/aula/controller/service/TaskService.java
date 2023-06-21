/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.repo.TaskRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daves
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepo tasks;

    public Task salvar(Task tarefa) {

        return tasks.save(tarefa);

    }

    public List<Task> minhaLista(long id) {
        
        return tasks.findByUserIdOrderByPlannedDateDesc(id);
    
    }

    public Task finalizar(Task t) {
       return tasks.save(t);
    }

    public Task getTask(long id) {
    return tasks.getOne(id);
    }

}
