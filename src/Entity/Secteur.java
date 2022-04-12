/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.ArrayList;


    public class Secteur 
    {
        private int idSecteur;
        private String nomSecteur;
        private ArrayList<Rayon> lesRayons;

        public Secteur(int unId, String unNom)
        {
            idSecteur = unId;
            nomSecteur = unNom;
            lesRayons = new ArrayList<Rayon>();
        }

        public int getIdSecteur() {
            return idSecteur;
        }

        public String getNomSecteur() {
            return nomSecteur;
        }

        public ArrayList<Rayon> getLesRayons() {
            return lesRayons;
        }
    }


