package com.groupeisi.etudiant.controller;

import com.groupeisi.etudiant.dto.EtudiantRequest;
import com.groupeisi.etudiant.dto.EtudiantResponse;
import com.groupeisi.etudiant.service.EtudiantService;
import com.groupeisi.etudiant.utils.exception.ApiResponse;
import com.groupeisi.etudiant.utils.exception.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EtudiantResponse>>> getAll(){
        return ResponseEntity.ok(ResponseUtil.success(etudiantService.getAllEtudiants(), ""));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EtudiantResponse>> save(@RequestBody EtudiantRequest etudiantRequest){
        return ResponseEntity.ok(ResponseUtil.success(etudiantService.saveEtudiant(etudiantRequest), "Etudiant créé avec succès !"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EtudiantResponse>> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(ResponseUtil.success(etudiantService.getEtudiantById(id).get(), ""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EtudiantResponse>> update(@PathVariable("id") Long id, @RequestBody EtudiantRequest etudiantRequest){
        return ResponseEntity.ok(ResponseUtil.success(etudiantService.updateEtudiant(id, etudiantRequest).get(), "Etudiant modifié avec succès !"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> update(@PathVariable("id") Long id){
        return ResponseEntity.ok(ResponseUtil.success(etudiantService.deleteEtudiant(id), "Etudiant supprimé avec succès !"));
    }
}
