package com.m2dfs.cloud.DemoDynamoDb.Repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.google.gson.Gson;
import com.m2dfs.cloud.DemoDynamoDb.Model.Etudiant;
import com.m2dfs.cloud.DemoDynamoDb.Model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.List;

@Repository
public class EtudiantDao {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    public EtudiantDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Etudiant getEtudiant(String EtudiantId) {
        return dynamoDBMapper.load(Etudiant.class, EtudiantId);
    }
    public List<Etudiant>getEtudiants() {

        List<Etudiant> scanResult = dynamoDBMapper.scan(Etudiant.class, new DynamoDBScanExpression());

        return scanResult;
    }
    public Etudiant createEtudiant(Etudiant etudiant) {
        dynamoDBMapper.save(etudiant);
        return etudiant;
    }

    public String deleteEtudiant(String EtudiantId) {
        Gson g = new Gson();
        Etudiant Etudiant = dynamoDBMapper.load(Etudiant.class, EtudiantId);

        if (Etudiant == null) {
            return g.toJson("Avertissement - l'etudiant  : " + EtudiantId + " n'existe pas.");
        } else {
            String name = Etudiant.getName();
            dynamoDBMapper.delete(Etudiant);
            Etudiant deletedItem = dynamoDBMapper.load(Etudiant.class, Etudiant.getEtudiantId());
            if (deletedItem == null) {
                return g.toJson("Terminé - l'Etudiant : \" + nom + \" à été supprimé.");
            } else {
                return g.toJson("Erreur - l'étudiant "+name+" n'a pas été supprimé");
            }
        }

    }
}