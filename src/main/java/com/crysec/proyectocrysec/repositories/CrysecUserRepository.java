package com.crysec.proyectocrysec.repositories;

import com.crysec.proyectocrysec.entities.CrysecUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrysecUserRepository extends JpaRepository<CrysecUser, Long> {
}
