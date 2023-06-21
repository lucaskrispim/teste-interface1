package br.com.webtask.aula;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class WebTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTaskApplication.class, args);
    }

//    @Autowired
//    private PasswordEncoder password;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaskRepo taskRepo;

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {

            if (userRepo.count() == 0) {
                UserClient uc1 = UserClient.builder()
                        .ativo(true).cpf("123").email("admin@admin")
                        .name("admin").senha(passwordEncoder().encode("123")).build();
                userRepo.save(uc1);

                UserClient uc2 = UserClient.builder()
                        .ativo(true).cpf("111").email("ze@ze")
                        .name("Zezin da Silva").senha(passwordEncoder().encode("111")).build();
                userRepo.save(uc2);

                Task t1 = Task.builder().plannedDate(LocalDate.now().plusDays(5)).user(uc2).taskDescription("Estudar Teste").build();
                Task t2 = Task.builder().plannedDate(LocalDate.now().minusDays(3)).user(uc2).taskDescription("Estudar Teste").build();
                Task t3 = Task.builder().plannedDate(LocalDate.now().minusDays(5)).finishedDate(LocalDate.now().minusDays(6)).user(uc2).taskDescription("Estudar Teste").build();
                Task t4 = Task.builder().plannedDate(LocalDate.now().minusDays(7)).finishedDate(LocalDate.now()).user(uc2).taskDescription("Estudar Teste").build();
                taskRepo.save(t1);
                taskRepo.save(t2);
                taskRepo.save(t3);
                taskRepo.save(t4);
            }

            try{
                System.out.println(userRepo.findByName("admin"));
            }catch (Exception e){
                System.out.println("erro"+e);
            }


            log.info("####### Web Task - Started #######");
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
