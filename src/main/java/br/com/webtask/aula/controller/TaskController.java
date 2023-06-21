package br.com.webtask.aula.controller;

import br.com.webtask.aula.config.security.user.UserLogado;
import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.util.LogadoUtil;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/task")
public class TaskController {
    
    
    @Autowired
   private TaskService taskService;
    
    @Autowired
    LogadoUtil userLogado;
    
    
    
    @GetMapping("")
    public String task(@ModelAttribute("tarefa") Task tarefa,
            ModelMap model) {

        return "task/newTask";
    }
    
    @GetMapping("/list")
    public String listaTask(Authentication authentication,ModelMap model) {

        model.addAttribute("lista", taskService.minhaLista( userLogado.getIdUserLogado(authentication) ));
        return "task/listTask";
    }
    
    @GetMapping("/{id}/close")
    public String closeTask(@PathVariable(name = "id", required = false) long id, 
            Authentication authentication,ModelMap model) {

        Task t = taskService.getTask(id);
        
        t.setFinishedDate(LocalDate.now());
        
        taskService.finalizar(t);
        
        
        return "redirect:/task/list";
    }

    @PostMapping("")
    public String saveTask( @Valid Task tarefa, BindingResult result, 
            RedirectAttributes attr, ModelMap model,Authentication authentication) {

        if (result.hasErrors()) {
            return "task/newTask";
        }
        System.out.println(userLogado.getIdUserLogado(authentication));
        tarefa.setUser( UserClient.builder().name(userLogado.getNomeUserLogado( authentication )).email(userLogado.getEmailUserLogado(authentication) ).id( userLogado.getIdUserLogado(authentication) ).build() );
        tarefa.setFinishedDate(null);
        taskService.salvar(tarefa);
        attr.addFlashAttribute("success", "Dados alterados com sucesso.");
        return "redirect:/home";
    }

}
