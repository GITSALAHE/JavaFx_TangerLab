package com.gusersinterfaces.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.guserinterfaces.config.DB;
public class EmployeController implements ProductionRisquesInterface, ManutentionRisqueInterface{
    DB db;
    Scanner scan;

    private double vrChiffre = 20000;
	private double rpChiffre = 30000;
	private int uNProduitMensuel = 20;
    private int nBheur = 200;
    
    public EmployeController() {
        db = new DB();
        scan = new Scanner(System.in);
    }

    public double calculerSalaire(String type ) {
        if(type == "vendeur"){
            return vrChiffre *0.2 + 1500;
        }
        else if(type == "representeur"){
            return rpChiffre *0.2 + 2500;
        }
        else if(type == "producteur"){
            return uNProduitMensuel*5 + ProductionRisquesInterface.salaireFixe;
        }
        else if(type == "Manutentionaire"){
            return nBheur*50 + ManutentionRisqueInterface.salaireFixe;
        }
        return 0;
    }

}
