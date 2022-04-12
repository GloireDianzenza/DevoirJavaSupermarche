/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Employe;
import Entity.Rayon;
import Entity.Secteur;
import Entity.Travailler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier
{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection cnx;

    public FonctionsMetier()
    {
        cnx = ConnexionBDD.getCnx();
    }
    
    public ArrayList<Secteur> GetAllSecteurs()
    {
        ArrayList<Secteur> lesSecteurs = new ArrayList<>();
        try
        {
            ps = cnx.prepareStatement("SELECT numS,nomS FROM secteur");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Secteur s = new Secteur(rs.getInt(1), rs.getString(2));
                lesSecteurs.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesSecteurs;
    }
    
    public ArrayList<Employe> GetAllEmployes()
    {
        ArrayList<Employe> lesEmployes = new ArrayList<>();
        try
        {
            ps = cnx.prepareStatement("SELECT numE,prenomE FROM employe");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Employe e = new Employe(rs.getInt(1), rs.getString(2));
                lesEmployes.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesEmployes;
    }
    
    public ArrayList<Rayon> GetAllRayonsByIdsecteur(int numSecteur)
    {
        ArrayList<Rayon> lesRayons = new ArrayList<>();
        try
        {
            ps = cnx.prepareStatement("SELECT numR,nomR FROM rayon WHERE numSecteur = "+numSecteur);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Rayon r = new Rayon(rs.getInt(1), rs.getString(2));
                lesRayons.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesRayons;
    }
    
    public ArrayList<Travailler> GetAllTravailler(int numRayon)
    {
        ArrayList<Travailler> lesTravaux = new ArrayList<>();
        try
        {
            ps = cnx.prepareStatement("SELECT codeE,employe.prenomE,date,temps FROM travailler JOIN employe ON travailler.codeE = employe.numE WHERE codeR = '"+numRayon+"'");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Travailler t = new Travailler(new Employe(rs.getInt(1),rs.getString(2)), rs.getString(3), rs.getInt(4));
                lesTravaux.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTravaux;
    }
    
    public int GetIdEmployeByNom(String nomEmploye)
    {
        int id = 0;
        try
        {
            ps = cnx.prepareStatement("SELECT numE FROM employe WHERE prenomE = '"+nomEmploye+"'");
            rs = ps.executeQuery();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int TotalHeuresRayon(int numRayon)
    {
        int total = 0;
        ArrayList<Travailler> trav = GetAllTravailler(numRayon);
        for(Travailler t:trav)
        {
            total += t.getTempsPasse();
        }
        return total;
    }
    
    public void ModifierTemps(int codeEmploye, int CodeRayon, String uneDate,int nouveauTemps)
    {
        try
        {
            ps = cnx.prepareStatement("UPDATE travailler SET temps = '"+nouveauTemps+"' WHERE codeE = '"+codeEmploye+"' AND codeR = '"+CodeRayon+"' AND date = '"+uneDate+"'");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void InsererTemps(int codeEmploye, int CodeRayon,int nouveauTemps)
    {
        try
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate localDate = LocalDate.now();
            String newDate = dtf.format(localDate);
            ps = cnx.prepareStatement("INSERT INTO travailler VALUES ('"+codeEmploye+"','"+CodeRayon+"','"+newDate+"','"+nouveauTemps+"')");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
