package com.br.crm.model;

import com.br.crm.model.enums.ClassificacaoRisco;
import com.br.crm.model.enums.StatusProjeto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotEmpty(message = "Nome não pode ser vazio")
  @NotNull(message = "Nome não pode ser nulo")
  private String nome;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date dataInicio;

  private String gerenteResponsavel;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date previsaoTermino;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  private Date dataRealTermino;

  private BigDecimal orcamentoTotal;
  private String descricao;

  @Enumerated(EnumType.STRING)
  private StatusProjeto status;

  @Enumerated(EnumType.STRING)
  private ClassificacaoRisco classificacaoRisco;

  @ManyToMany
  @JoinTable(
    name = "projeto_membro",
    joinColumns = @JoinColumn(name = "projeto_id"),
    inverseJoinColumns = @JoinColumn(name = "membro_id")
  )
  private List<Membro> membros;

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

  public Date getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  public String getGerenteResponsavel() {
    return gerenteResponsavel;
  }

  public void setGerenteResponsavel(String gerenteResponsavel) {
    this.gerenteResponsavel = gerenteResponsavel;
  }

  public Date getPrevisaoTermino() {
    return previsaoTermino;
  }

  public void setPrevisaoTermino(Date previsaoTermino) {
    this.previsaoTermino = previsaoTermino;
  }

  public Date getDataRealTermino() {
    return dataRealTermino;
  }

  public void setDataRealTermino(Date dataRealTermino) {
    this.dataRealTermino = dataRealTermino;
  }

  public BigDecimal getOrcamentoTotal() {
    return orcamentoTotal;
  }

  public void setOrcamentoTotal(BigDecimal orcamentoTotal) {
    this.orcamentoTotal = orcamentoTotal;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public StatusProjeto getStatus() {
    return status;
  }

  public void setStatus(StatusProjeto status) {
    this.status = status;
  }

  public void getMembros(List<Membro> membros) {
    this.membros = membros;
  }

  public List<Membro> getMembros() {
    return membros;
  }
}
