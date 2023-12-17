package com.example.caminhao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.caminhao.entidades.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista,Long> {
	

}
