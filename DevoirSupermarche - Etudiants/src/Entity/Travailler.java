/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


    public class Travailler
    {
        private Employe unEmploye;
        private String dateTravaillee;
        private int tempsPasse;

        public Travailler(Employe unEmploye, String uneDate, int unTemps)
        {
            this.unEmploye = unEmploye;
            dateTravaillee = uneDate;
            tempsPasse = unTemps;
        }

        public Employe getUnEmploye() {
            return unEmploye;
        }

        public int getTempsPasse() {
            return tempsPasse;
        }

        public String getDateTravaillee() {
            return dateTravaillee;
        }
    }

