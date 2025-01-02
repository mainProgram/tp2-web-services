package com.groupeisi.classe_service.repository;

import com.groupeisi.classe_service.model.ClasseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, Long> {}
