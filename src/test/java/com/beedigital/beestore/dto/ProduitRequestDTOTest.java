package com.beedigital.beestore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduitRequestDTOTest {

    private ProduitRequestDTO produit;

    @BeforeEach
    void setUp() {
        produit = new ProduitRequestDTO();
    }

    // ---------------------------------------------------------------------
    // Getters
    // ---------------------------------------------------------------------

    @Test
    void getNom() {
        produit.setNom("Chaise");
        assertEquals("Chaise", produit.getNom());
    }

    @Test
    void getDescription() {
        produit.setDescription("Chaise en bois massif");
        assertEquals("Chaise en bois massif", produit.getDescription());
    }

    @Test
    void getPrix() {
        produit.setPrix(49.99);
        assertEquals(49.99, produit.getPrix());
    }

    @Test
    void getStock() {
        produit.setStock(15);
        assertEquals(15, produit.getStock());
    }

    @Test
    void getCategorie() {
        produit.setCategorie("Mobilier");
        assertEquals("Mobilier", produit.getCategorie());
    }

    @Test
    void getActif() {
        // Valeur par défaut définie dans le DTO
        assertTrue(produit.getActif());

        produit.setActif(false);
        assertFalse(produit.getActif());
    }

    // ---------------------------------------------------------------------
    // Setters
    // ---------------------------------------------------------------------

    @Test
    void setNom() {
        produit.setNom("Table");
        assertEquals("Table", produit.getNom());
    }

    @Test
    void setDescription() {
        produit.setDescription("Table en chêne");
        assertEquals("Table en chêne", produit.getDescription());
    }

    @Test
    void setPrix() {
        produit.setPrix(120.50);
        assertEquals(120.50, produit.getPrix());
    }

    @Test
    void setStock() {
        produit.setStock(0);
        assertEquals(0, produit.getStock());
    }

    @Test
    void setCategorie() {
        produit.setCategorie("Décoration");
        assertEquals("Décoration", produit.getCategorie());
    }

    @Test
    void setActif() {
        produit.setActif(false);
        assertFalse(produit.getActif());

        produit.setActif(true);
        assertTrue(produit.getActif());
    }

    // ---------------------------------------------------------------------
    // Equals / canEqual / hashCode
    // ---------------------------------------------------------------------

    @Test
    void testEquals() {
        ProduitRequestDTO a = buildSample();
        ProduitRequestDTO b = buildSample();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        b.setNom("Autre nom");
        assertNotEquals(a, b);

        assertNotEquals(a, null);
        assertNotEquals(a, new Object());
        assertEquals(a, a);
    }

    @Test
    void canEqual() {
        ProduitRequestDTO a = buildSample();
        ProduitRequestDTO b = buildSample();

        assertTrue(a.canEqual(b));
        assertTrue(b.canEqual(a));
        assertFalse(a.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        ProduitRequestDTO a = buildSample();
        ProduitRequestDTO b = buildSample();

        assertEquals(a.hashCode(), b.hashCode());

        b.setPrix(999.99);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testToString() {
        ProduitRequestDTO a = buildSample();

        String result = a.toString();

        assertNotNull(result);
        assertTrue(result.contains("Chaise"));
        assertTrue(result.contains("Mobilier"));
        assertTrue(result.contains("49.99"));
        assertTrue(result.contains("10"));
    }

    // ---------------------------------------------------------------------
    // Helper
    // ---------------------------------------------------------------------

    private ProduitRequestDTO buildSample() {
        ProduitRequestDTO dto = new ProduitRequestDTO();
        dto.setNom("Chaise");
        dto.setDescription("Chaise en bois");
        dto.setPrix(49.99);
        dto.setStock(10);
        dto.setCategorie("Mobilier");
        dto.setActif(true);
        return dto;
    }
}