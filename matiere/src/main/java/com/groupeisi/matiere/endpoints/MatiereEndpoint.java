package com.groupeisi.matiere.endpoints;

import com.groupeisi.matiere.model.Matiere;
import com.groupeisi.matiere.service.MatiereService;
import com.groupeisi.matiere.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;


@Endpoint
@Component
public class MatiereEndpoint {
    private static final String NAMESPACE_URI = "http://groupeisi.com/matiere";

    private MatiereService matiereService;

    @Autowired
    public MatiereEndpoint(MatiereService matiereService) {
        this.matiereService = matiereService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateMatiereRequest")
    @ResponsePayload
    public CreateMatiereResponse createMatiere(@RequestPayload CreateMatiereRequest request) {
        Matiere matiere = new Matiere();
        matiere.setLibelle(request.getLibelle());
        matiere.setCoefficient(request.getCoefficient());
        Matiere createdMatiere = matiereService.createMatiere(matiere);
        CreateMatiereResponse response = new CreateMatiereResponse();
        response.setId(createdMatiere.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMatiereRequest")
    @ResponsePayload
    public GetMatiereResponse getMatiere(@RequestPayload GetMatiereRequest request) {
        Matiere matiere = matiereService.getMatiere(request.getId());
        GetMatiereResponse response = new GetMatiereResponse();
        response.setId(matiere.getId());
        response.setLibelle(matiere.getLibelle());
        response.setCoefficient(matiere.getCoefficient());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateMatiereRequest")
    @ResponsePayload
    public UpdateMatiereResponse updateMatiere(@RequestPayload UpdateMatiereRequest request) {
        Matiere matiere = new Matiere();
        matiere.setId(request.getId());
        matiere.setLibelle(request.getLibelle());
        matiere.setCoefficient(request.getCoefficient());
        boolean updated = matiereService.updateMatiere(matiere);
        UpdateMatiereResponse response = new UpdateMatiereResponse();
        response.setStatus(updated ? "success" : "failed");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMatiereRequest")
    @ResponsePayload
    public DeleteMatiereResponse deleteMatiere(@RequestPayload DeleteMatiereRequest request) {
        boolean deleted = matiereService.deleteMatiere(request.getId());
        DeleteMatiereResponse response = new DeleteMatiereResponse();
        response.setStatus(deleted ? "success" : "failed");
        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllMatieresRequest")
//    @ResponsePayload
//    public GetAllMatieresResponse getAllMatieres(@RequestPayload GetAllMatieresRequest request) {
//        List<Matiere> matieres = matiereService.getAllMatieres();
//        GetAllMatieresResponse response = new GetAllMatieresResponse();
//        matieres.forEach(matiere -> {
//            com.groupeisi.matiere.Matiere soapMatiere = mapToSoapMatiere(matiere);
//            response.getMatiere().add(soapMatiere);
//        });
//        return response;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllMatieresRequest")
    @ResponsePayload
    public GetAllMatieresResponse getAllMatieres(@RequestPayload GetAllMatieresRequest request) {
        GetAllMatieresResponse response = new GetAllMatieresResponse();
        response.getMatiere().addAll(
                toSoapMatiereList(matiereService.getAllMatieres())
        );
        return response;
    }

    public List<com.groupeisi.matiere.Matiere> toSoapMatiereList(List<Matiere> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toSoapMatiere)
                .collect(Collectors.toList());
    }

    public com.groupeisi.matiere.Matiere toSoapMatiere(Matiere entity) {
        if (entity == null) {
            return null;
        }
        com.groupeisi.matiere.Matiere soapMatiere = new com.groupeisi.matiere.Matiere();
        soapMatiere.setId(entity.getId());
        soapMatiere.setLibelle(entity.getLibelle());
        return soapMatiere;
    }

    private com.groupeisi.matiere.Matiere mapToSoapMatiere(Matiere entityMatiere) {
        com.groupeisi.matiere.Matiere soapMatiere = new com.groupeisi.matiere.Matiere();
        soapMatiere.setId(entityMatiere.getId());
        soapMatiere.setLibelle(entityMatiere.getLibelle());
        soapMatiere.setCoefficient(entityMatiere.getCoefficient());
        return soapMatiere;
    }
}