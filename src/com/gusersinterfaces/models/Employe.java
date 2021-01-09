package com.gusersinterfaces.models;

public abstract class Employe {
    protected int id;
    protected String nom;
	protected String prenom;
	protected String age;
	protected String date;
    protected String type;
    protected Double salary;

    public Employe() {
    }
    public abstract double calculerSalaire();	

    public Employe(int id, String nom, String prenom, String age, String date, String type, Double salary) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.date = date;
        this.type = type;
        this.salary = salary;
    }


}
