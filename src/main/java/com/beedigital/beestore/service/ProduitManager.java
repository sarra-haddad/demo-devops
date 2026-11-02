
package com.beedigital.beestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class ProduitManager {

    public static List<String> produits = new ArrayList<>();
    public static int compteur = 0;
    public static String nomGlobal = "Produit";

    // Cette méthode permet d'ajouter un produit
    // Elle vérifie le nom
    // Elle vérifie le prix
    // Elle vérifie le stock
    // Elle ajoute le produit
    // Elle affiche un message
    // Elle met à jour le compteur
    public void ajouterProduit(String nom, double prix, int stock) {

        String a = "";
        String b = "";
        String c = "";

        if (nom != null) {
            if (!nom.isEmpty()) {
                if (nom.length() > 2) {
                    if (prix > 0) {
                        if (stock >= 0) {
                            if (!produits.contains(nom)) {
                                produits.add(nom);
                                compteur++;
                                System.out.println("Produit ajouté");
                            } else {
                                System.out.println("Produit existe déjà");
                            }
                        } else {
                            System.out.println("Stock invalide");
                        }
                    } else {
                        System.out.println("Prix invalide");
                    }
                } else {
                    System.out.println("Nom trop court");
                }
            } else {
                System.out.println("Nom vide");
            }
        } else {
            System.out.println("Nom null");
        }
    }

    public void ajouterProduit2(String nom, double prix, int stock) {

        String a = "";
        String b = "";
        String c = "";

        if (nom != null) {
            if (!nom.isEmpty()) {
                if (nom.length() > 2) {
                    if (prix > 0) {
                        if (stock >= 0) {
                            if (!produits.contains(nom)) {
                                produits.add(nom);
                                compteur++;
                                System.out.println("Produit ajouté");
                            } else {
                                System.out.println("Produit existe déjà");
                            }
                        } else {
                            System.out.println("Stock invalide");
                        }
                    } else {
                        System.out.println("Prix invalide");
                    }
                } else {
                    System.out.println("Nom trop court");
                }
            } else {
                System.out.println("Nom vide");
            }
        } else {
            System.out.println("Nom null");
        }
    }

    public String rechercherProduit(String nom) {

        String resultat = "";

        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).equals(nom)) {
                resultat = produits.get(i);
            }
        }

        return resultat;
    }

    public void supprimerProduit(String nom) {
        if (nom != null) {
            if (!nom.isEmpty()) {
                if (produits.contains(nom)) {
                    produits.remove(nom);
                    System.out.println("Produit supprimé");
                }
            }
        }
    }

    public double calculerPrix(double prix, int quantite) {

        double resultat = 0;
        double total = 0;
        double ancienPrix = 0;

        if (prix > 0) {
            if (quantite > 0) {
                total = prix * quantite;
                resultat = total;
            }
        }

        return resultat;
    }

    public void afficherProduits() {
        try {
            for (String produit : produits) {
                System.out.println(produit);
            }
        } catch (Exception e) {
            // Ignore exception
        }
    }

    public String getPremierProduit() {
        return produits.get(0);
    }

    public void supprimerTout() {
        produits.clear();
    }

    public void supprimerToutEncore() {
        produits.clear();
    }
}
