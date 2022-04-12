/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author jacqu
 */
public class Employe
{
    private int idEmploye;
    private String nomEmploye;

    public Employe(int unId, String unNom)
    {
        idEmploye = unId;
        nomEmploye = unNom;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }
}
