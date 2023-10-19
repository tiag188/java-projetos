package com.br.crm.repository;

import com.br.crm.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {}
