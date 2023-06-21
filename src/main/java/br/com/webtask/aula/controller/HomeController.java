package br.com.webtask.aula.controller;

import br.com.webtask.aula.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    LogadoUtil userLogado;
        
    
    @RequestMapping(path = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Authentication authentication, ModelMap model) {
        model.addAttribute("nome", userLogado.getNomeUserLogado(authentication) );
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        try{
            return "login";
        }catch (Exception e){
            System.out.println("erro "+e);
            return "home";
        }

    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
