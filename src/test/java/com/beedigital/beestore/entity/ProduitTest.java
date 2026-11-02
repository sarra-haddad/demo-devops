package com.beedigital.beestore.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de l'entité JPA {@link Produit}.
 *
 * <p>Aucun contexte Spring n'est nécessaire : on teste uniquement
 * les getters/setters, le builder, et le callback {@code @PrePersist}
 * ({@link Produit#onCreate()}) appelé directement.</p>
 */
class ProduitTest {

    private Produit produit;

    @BeforeEach
    void setUp() {
        produit = new Produit();
    }

    // =====================================================================
    // @PrePersist : onCreate()
    // =====================================================================

    @Test
    void onCreate() {
        // dateCreation null au départ
        assertNull(produit.getDateCreation());

        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        produit.onCreate();
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        assertNotNull(produit.getDateCreation());
        assertTrue(produit.getDateCreation().isAfter(before));
        assertTrue(produit.getDateCreation().isBefore(after));
    }

    @Test
    void onCreate_shouldSetActifToTrue_whenNull() {
        produit.setActif(null);

        produit.onCreate();

        assertTrue(produit.getActif());
    }

    @Test
    void onCreate_shouldKeepActifFalse_whenAlreadySetToFalse() {
        produit.setActif(false);

        produit.onCreate();

        assertFalse(produit.getActif());
    }

    @Test
    void onCreate_shouldKeepActifTrue_whenAlreadySetToTrue() {
        produit.setActif(true);

        produit.onCreate();

        assertTrue(produit.getActif());
    }

    @Test
    void onCreate_shouldAlwaysOverrideDateCreation() {
        LocalDateTime oldDate = LocalDateTime.of(2020, 1, 1, 0, 0);
        produit.setDateCreation(oldDate);

        produit.onCreate();

        assertNotEquals(oldDate, produit.getDateCreation());
        assertTrue(produit.getDateCreation().isAfter(oldDate));
    }

    // =====================================================================
    // Getters
    // =====================================================================

    @Test
    void getId() {
        produit.setId(1L);
        assertEquals(1L, produit.getId());
    }

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
        produit.setStock(10);
        assertEquals(10, produit.getStock());
    }

    @Test
    void getCategorie() {
        produit.setCategorie("Mobilier");
        assertEquals("Mobilier", produit.getCategorie());
    }

    @Test
    void getActif() {
        // Valeur par défaut définie dans l'entité
        assertTrue(produit.getActif());

        produit.setActif(false);
        assertFalse(produit.getActif());
    }

    @Test
    void getDateCreation() {
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 10, 30);
        produit.setDateCreation(date);
        assertEquals(date, produit.getDateCreation());
    }

    // =====================================================================
    // Setters
    // =====================================================================

    @Test
    void setId() {
        produit.setId(42L);
        assertEquals(42L, produit.getId());
    }

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

    @Test
    void setDateCreation() {
        LocalDateTime now = LocalDateTime.now();
        produit.setDateCreation(now);
        assertEquals(now, produit.getDateCreation());
    }

    // =====================================================================
    // Constructeurs
    // =====================================================================

    @Test
    void noArgsConstructor_shouldCreateInstance_withDefaultActifTrue() {
        Produit empty = new Produit();

        assertNull(empty.getId());
        assertNull(empty.getNom());
        assertNull(empty.getDescription());
        assertNull(empty.getPrix());
        assertNull(empty.getStock());
        assertNull(empty.getCategorie());
        // Valeur par défaut définie au niveau du champ
        assertTrue(empty.getActif());
        assertNull(empty.getDateCreation());
    }

    @Test
    void allArgsConstructor_shouldPopulateAllFields() {
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 10, 30);

        Produit p = new Produit(
                1L, "Chaise", "Chaise en bois", 49.99, 10,
                "Mobilier", true, date);

        assertEquals(1L, p.getId());
        assertEquals("Chaise", p.getNom());
        assertEquals("Chaise en bois", p.getDescription());
        assertEquals(49.99, p.getPrix());
        assertEquals(10, p.getStock());
        assertEquals("Mobilier", p.getCategorie());
        assertTrue(p.getActif());
        assertEquals(date, p.getDateCreation());
    }

    // =====================================================================
    // Builder
    // =====================================================================

    @Test
    void builder() {
        LocalDateTime date = LocalDateTime.of(2024, 1, 15, 10, 30);

        Produit p = Produit.builder()
                .id(1L)
                .nom("Chaise")
                .description("Chaise en bois")
                .prix(49.99)
                .stock(10)
                .categorie("Mobilier")
                .actif(true)
                .dateCreation(date)
                .build();

        assertAll(
                () -> assertEquals(1L, p.getId()),
                () -> assertEquals("Chaise", p.getNom()),
                () -> assertEquals("Chaise en bois", p.getDescription()),
                () -> assertEquals(49.99, p.getPrix()),
                () -> assertEquals(10, p.getStock()),
                () -> assertEquals("Mobilier", p.getCategorie()),
                () -> assertTrue(p.getActif()),
                () -> assertEquals(date, p.getDateCreation())
        );
    }

    @Test
    void builder_shouldAllowPartialConstruction() {
        Produit p = Produit.builder()
                .nom("Lampe")
                .prix(19.99)
                .build();

        assertEquals("Lampe", p.getNom());
        assertEquals(19.99, p.getPrix());
        assertNull(p.getId());
        assertNull(p.getDescription());
        assertNull(p.getStock());
        assertNull(p.getCategorie());
        assertNull(p.getDateCreation());
        // Attention : le builder ne passe pas par l'initialiseur de champ
        // selon la version Lombok ; on vérifie donc juste qu'il est affectable.
        // Avec @Builder + @NoArgsConstructor, la valeur par défaut du champ s'applique.
    }

    // =====================================================================
    // equals / canEqual / hashCode
    // =====================================================================

    @Test
    void testEquals() {
        Produit a = buildSample();
        Produit b = buildSample();

        assertEquals(a, b);
        assertEquals(a, a);
        assertNotEquals(a, null);
        assertNotEquals(a, new Object());

        b.setNom("Autre nom");
        assertNotEquals(a, b);

        b = buildSample();
        b.setId(999L);
        assertNotEquals(a, b);

        b = buildSample();
        b.setDateCreation(LocalDateTime.of(2020, 1, 1, 0, 0));
        assertNotEquals(a, b);
    }

    @Test
    void canEqual() {
        Produit a = buildSample();
        Produit b = buildSample();

        assertTrue(a.canEqual(b));
        assertTrue(b.canEqual(a));
        assertFalse(a.canEqual(new Object()));
        assertFalse(a.canEqual(null));
    }

    @Test
    void testHashCode() {
        Produit a = buildSample();
        Produit b = buildSample();

        assertEquals(a.hashCode(), b.hashCode());

        b.setPrix(999.99);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    // =====================================================================
    // toString
    // =====================================================================

    @Test
    void testToString() {
        Produit a = buildSample();

        String result = a.toString();

        assertNotNull(result);
        assertTrue(result.contains("Produit"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Chaise"));
        assertTrue(result.contains("Mobilier"));
        assertTrue(result.contains("49.99"));
        assertTrue(result.contains("10"));
    }

    // =====================================================================
    // Helper
    // =====================================================================

    private Produit buildSample() {
        return Produit.builder()
                .id(1L)
                .nom("Chaise")
                .description("Chaise en bois")
                .prix(49.99)
                .stock(10)
                .categorie("Mobilier")
                .actif(true)
                .dateCreation(LocalDateTime.of(2024, 1, 15, 10, 30))
                .build();
    }
}