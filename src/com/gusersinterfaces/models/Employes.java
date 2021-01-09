package com.gusersinterfaces.models;

public class Employes extends Employe{

    public Employes(int id, String nom, String prenom, String age, String date, String type, Double salary) {
       super(id, nom, prenom, age, date, type, salary);
    }

    public Employes() {
    }


    
    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
    


    public String getNom() {
        return this.nom;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
  
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", age='" + getAge() + "'" +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            ", salary='" + getSalary() + "'" +
            "}";
    }

    @Override
    public double calculerSalaire() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
