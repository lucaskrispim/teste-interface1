/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.model;

import lombok.Data;

/**
 *
 * @author daves
 */
public enum EStatus {

    NOVO("badge badge-pill badge-success", "tarefa no prazo"),
    ATRASADO("badge badge-pill badge-warning", "tarefa atrasada"),
    CONCLUIDO_PRAZO("badge badge-pill badge-primary", "concluída no prazo"),
    CONCLUIDO_ATRASADO("badge badge-pill badge-danger","concluída com atraso");

    private String cor;
    private String descricao;

    EStatus(String cor, String descricao) {
        this.cor = cor;
        this.descricao = descricao;

    }

    public String getCor() {
        return cor;
    }

    public String getDescricao() {
        return descricao;
    }

}
