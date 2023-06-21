package br.com.webtask.aula.domain.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(name = "task", nullable = false, length = 80)
    private String taskDescription;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate plannedDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Column(nullable = true, columnDefinition = "DATE")
    private LocalDate finishedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserClient user;

    
    public boolean isFinish(){
        return finishedDate != null;
    }
    
    public boolean isLate(){
        return plannedDate.isBefore(LocalDate.now());
    }
    
    public EStatus getStatus(){
        if (isFinish() && plannedDate.isBefore(finishedDate)){
            return EStatus.CONCLUIDO_ATRASADO;
        }else if (isFinish() && !plannedDate.isBefore(finishedDate)){
            return EStatus.CONCLUIDO_PRAZO;            
        } else if (!isFinish() && isLate() ){
            return EStatus.ATRASADO;            
        } else{
            return EStatus.NOVO; 
        }
    }
    
    
}
