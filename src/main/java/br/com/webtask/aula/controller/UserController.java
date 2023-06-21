package br.com.webtask.aula.controller;

import br.com.webtask.aula.config.security.user.UserLogado;
import br.com.webtask.aula.controller.service.UserService;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.util.LogadoUtil;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    
    
    @Autowired
    private PasswordEncoder password;
    @Autowired
   private UserService userService;
    
    @Autowired
    LogadoUtil userLogado;
   
    @GetMapping("/editar")
    public String editar(Authentication authentication, ModelMap model) {
        //
        UserClient uc = userService.buscarUsuario( userLogado.getIdUserLogado(authentication) );
        uc.setSenha("");
        model.addAttribute("usuario", uc);
        return "edit";
    }

    @PostMapping("/editar")
    public String editarSave(@Valid UserClient usuario, BindingResult result, RedirectAttributes attr, ModelMap model) {

        if (result.hasErrors()) {
            return "edit";
        }
        usuario.setSenha(password.encode(usuario.getSenha()));
        userService.salvar(usuario);
        attr.addFlashAttribute("success", "Dados alterados com sucesso.");
        return "redirect:/home";
    }

}
