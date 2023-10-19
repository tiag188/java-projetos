package com.br.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.crm.model.Membro;

@Repository
@Transactional
public interface MembroRepository extends CrudRepository<Membro, Long> {}
