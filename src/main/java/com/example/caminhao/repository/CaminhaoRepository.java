package com.example.caminhao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Caminhao;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

	
}
  