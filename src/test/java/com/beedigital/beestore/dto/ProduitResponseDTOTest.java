package com.beedigital.beestore.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProduitResponseDTOTest {

    private static final LocalDateTime DATE_CREATION = LocalDateTime.of(2024, 1, 15, 10, 30, 0);

    private ProduitResponseDTO produit;

    @BeforeEach
    void setUp() {
        produit = new ProduitResponseDTO();
    }

    // ---------------------------------------------------------------------
    // Getters
    // ---------------------------------------------------------------------

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
        produit.setActif(true);
        assertTrue(produit.getActif());

        produit.setActif(false);
        assertFalse(produit.getActif());
    }

    @Test
    void getDateCreation() {
        produit.setDateCreation(DATE_CREATION);
        assertEquals(DATE_CREATION, produit.getDateCreation());
    }

    // ---------------------------------------------------------------------
    // Setters
    // ---------------------------------------------------------------------

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

    // ---------------------------------------------------------------------
    // Constructors
    // ---------------------------------------------------------------------

    @Test
    void noArgsConstructor_shouldCreateEmptyInstance() {
        ProduitResponseDTO dto = new ProduitResponseDTO();

        assertNull(dto.getId());
        assertNull(dto.getNom());
        assertNull(dto.getDescription());
        assertNull(dto.getPrix());
        assertNull(dto.getStock());
        assertNull(dto.getCategorie());
        assertNull(dto.getActif());
        assertNull(dto.getDateCreation());
    }

    @Test
    void allArgsConstructor_shouldPopulateAllFields() {
        ProduitResponseDTO dto = new ProduitResponseDTO(
                1L, "Chaise", "Chaise en bois", 49.99, 10,
                "Mobilier", true, DATE_CREATION);

        assertEquals(1L, dto.getId());
        assertEquals("Chaise", dto.getNom());
        assertEquals("Chaise en bois", dto.getDescription());
        assertEquals(49.99, dto.getPrix());
        assertEquals(10, dto.getStock());
        assertEquals("Mobilier", dto.getCategorie());
        assertTrue(dto.getActif());
        assertEquals(DATE_CREATION, dto.getDateCreation());
    }

    // ---------------------------------------------------------------------
    // Builder
    // ---------------------------------------------------------------------

    @Test
    void builder() {
        ProduitResponseDTO dto = ProduitResponseDTO.builder()
                .id(1L)
                .nom("Chaise")
                .description("Chaise en bois")
                .prix(49.99)
                .stock(10)
                .categorie("Mobilier")
                .actif(true)
                .dateCreation(DATE_CREATION)
                .build();

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("Chaise", dto.getNom()),
                () -> assertEquals("Chaise en bois", dto.getDescription()),
                () -> assertEquals(49.99, dto.getPrix()),
                () -> assertEquals(10, dto.getStock()),
                () -> assertEquals("Mobilier", dto.getCategorie()),
                () -> assertTrue(dto.getActif()),
                () -> assertEquals(DATE_CREATION, dto.getDateCreation())
        );
    }

    @Test
    void builder_shouldAllowPartialConstruction() {
        ProduitResponseDTO dto = ProduitResponseDTO.builder()
                .id(7L)
                .nom("Lampe")
                .build();

        assertEquals(7L, dto.getId());
        assertEquals("Lampe", dto.getNom());
        assertNull(dto.getDescription());
        assertNull(dto.getPrix());
        assertNull(dto.getStock());
        assertNull(dto.getCategorie());
        assertNull(dto.getActif());
        assertNull(dto.getDateCreation());
    }

    @Test
    void builder_toString_shouldWork() {
        ProduitResponseDTO dto = ProduitResponseDTO.builder()
                .id(1L)
                .nom("Chaise")
                .build();

        // Le builder Lombok utilise la même classe avec toString hérité
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("Chaise"));
    }

    // ---------------------------------------------------------------------
    // Equals / canEqual / hashCode
    // ---------------------------------------------------------------------

    @Test
    void testEquals() {
        ProduitResponseDTO a = buildSample();
        ProduitResponseDTO b = buildSample();

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
        b.setDateCreation(DATE_CREATION.plusDays(1));
        assertNotEquals(a, b);
    }

    @Test
    void canEqual() {
        ProduitResponseDTO a = buildSample();
        ProduitResponseDTO b = buildSample();

        assertTrue(a.canEqual(b));
        assertTrue(b.canEqual(a));
        assertFalse(a.canEqual(new Object()));
        assertFalse(a.canEqual(null));
    }

    @Test
    void testHashCode() {
        ProduitResponseDTO a = buildSample();
        ProduitResponseDTO b = buildSample();

        assertEquals(a.hashCode(), b.hashCode());

        b.setPrix(999.99);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testToString() {
        ProduitResponseDTO a = buildSample();

        String result = a.toString();

        assertNotNull(result);
        assertTrue(result.contains("ProduitResponseDTO"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Chaise"));
        assertTrue(result.contains("Mobilier"));
        assertTrue(result.contains("49.99"));
        assertTrue(result.contains("10"));
    }

    // ---------------------------------------------------------------------
    // Helper
    // ---------------------------------------------------------------------

    private ProduitResponseDTO buildSample() {
        return ProduitResponseDTO.builder()
                .id(1L)
                .nom("Chaise")
                .description("Chaise en bois")
                .prix(49.99)
                .stock(10)
                .categorie("Mobilier")
                .actif(true)
                .dateCreation(DATE_CREATION)
                .build();
    }
}