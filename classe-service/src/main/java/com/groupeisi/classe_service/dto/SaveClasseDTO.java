package com.groupeisi.classe_service.dto;

import java.util.List;


public record SaveClasseDTO (String libelle, List<Long> professeursIds){}
