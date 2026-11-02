
package com.beedigital.beestore.service;

import java.util.ArrayList;
import java.util.List;

public class ProduitManager {

    // Liste des produits
    private List<String> produits = new ArrayList<>();

    // Variable inutile
    private int compteur = 0;

    // Ajouter un produit
    // Cette méthode permet d'ajouter un produit dans la liste
    // On vérifie si le produit existe déjà
    public void ajouterProduit(String nom) {

        if (nom != null) {
            if (!nom.isEmpty()) {
                if (nom.length() > 2) {
                    if (!produits.contains(nom)) {
                        produits.add(nom);
                        compteur++;
                        System.out.println("Produit ajouté");
                    } else {
                        System.out.println("Produit existe déjà");
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

    // Méthode pour ajouter un produit
    public void ajouterProduit2(String nom) {
        if (nom != null) {
            if (!nom.isEmpty()) {
                if (nom.length() > 2) {
                    if (!produits.contains(nom)) {
                        produits.add(nom);
                        compteur++;
                        System.out.println("Produit ajouté");
                    } else {
                        System.out.println("Produit existe déjà");
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

    // Récupérer tous les produits
    public List<String> getProduits() {
        return produits;
    }

    // Rechercher un produit
    public String rechercherProduit(String nom) {

        String resultat = "";

        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).equals(nom)) {
                resultat = produits.get(i);
            }
        }

        return resultat;
    }

    // Supprimer un produit
    public void supprimerProduit(String nom) {
        if (nom != null) {
            if (!nom.isEmpty()) {
                if (produits.contains(nom)) {
                    produits.remove(nom);
                    System.out.println("Produit supprimé");
                } else {
                    System.out.println("Produit introuvable");
                }
            }
        }
    }

    // Calculer un prix
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

    // Afficher les produits
    public void afficherProduits() {
        for (int i = 0; i < produits.size(); i++) {
            System.out.println("Produit : " + produits.get(i));
        }
    }
}
