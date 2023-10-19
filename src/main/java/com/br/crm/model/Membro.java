package com.br.crm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Membro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String nome;
  private String atribuicao;

  @ManyToMany(mappedBy = "membros")
  private List<Projeto> projetos;

  // Getters e Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getAtribuicao() {
    return atribuicao;
  }

  public void setAtribuicao(String atribuicao) {
    this.atribuicao = atribuicao;
  }
  // Outros métodos relevantes, se necessário
}
