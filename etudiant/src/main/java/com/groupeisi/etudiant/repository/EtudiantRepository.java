package com.groupeisi.etudiant.repository;

import com.groupeisi.etudiant.model.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<EtudiantEntity, Long> {

}
