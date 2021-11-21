package com.m2dfs.cloud.DemoDynamoDb.Controller;


import com.m2dfs.cloud.DemoDynamoDb.Model.Etudiant;
import com.m2dfs.cloud.DemoDynamoDb.Repository.EtudiantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

import java.util.List;

@RestController
public class EtudiantController {


    private @Autowired
    EtudiantDao etudiantDao;

    @RequestMapping(value = "/etudiant", produces = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Etudiant> getEtudiant(@RequestBody Etudiant Etudiant) {
        try {
            Etudiant response = etudiantDao.createEtudiant(Etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/etudiants/{etudiantId}", produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable String etudiantId) {
        try {
            Etudiant response = etudiantDao.getEtudiant(etudiantId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/etudiants", produces = {"application/json"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Etudiant>> getEtudiants() {
        try {
            List<Etudiant> response = etudiantDao.getEtudiants();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/etudiants/{etudiantId}", produces = {"application/json"},
            method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEtudiant(@PathVariable String etudiantId) {
        try {
            String response = etudiantDao.deleteEtudiant(etudiantId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(),
                    e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

}