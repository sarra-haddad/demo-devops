package com.beedigital.beestore.repository;

import com.beedigital.beestore.entity.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests d'intégration du {@link ProduitRepository} via {@code @DataJpaTest}.
 *
 * <p>Charge uniquement la couche JPA (pas de service, pas de controller)
 * avec une base H2 en mémoire par défaut, et rollback chaque test.</p>
 *
 * <p>Les tests valident :</p>
 * <ul>
 *   <li>les dérivations de méthodes Spring Data (IgnoreCase, Containing, Between, etc.)</li>
 *   <li>la requête JPQL custom</li>
 *   <li>le déclenchement réel de {@code @PrePersist} ({@code onCreate})</li>
 * </ul>
 */
@DataJpaTest
class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Produit chaise;
    private Produit table;
    private Produit lampeInactive;
    private Produit canape;

    @BeforeEach
    void setUp() {
        // Jeu de données commun — persisté via flush + clear pour simuler
        // un vrai passage par @PrePersist
        chaise = persist(buildProduit(
                "Chaise", "Chaise en bois", 49.99, 10, "Mobilier", true));
        table = persist(buildProduit(
                "Table", "Table en chêne", 199.99, 5, "Mobilier", true));
        lampeInactive = persist(buildProduit(
                "Lampe", "Lampe de bureau", 29.99, 0, "Décoration", false));
        canape = persist(buildProduit(
                "Canapé", "Canapé en cuir", 899.00, 2, "Mobilier", true));

        entityManager.flush();
        entityManager.clear();
    }

    // =====================================================================
    // findByActifTrue
    // =====================================================================

    @Test
    void findByActifTrue() {
        List<Produit> result = produitRepository.findByActifTrue();

        assertThat(result)
                .hasSize(3)
                .extracting(Produit::getNom)
                .containsExactlyInAnyOrder("Chaise", "Table", "Canapé");

        assertThat(result).allMatch(Produit::getActif);
        assertThat(result).noneMatch(p -> "Lampe".equals(p.getNom()));
    }

    @Test
    void findByActifTrue_shouldReturnEmpty_whenAllInactive() {
        Produit p = persist(buildProduit("Inactif", "desc", 10.0, 1, "Cat", false));
        entityManager.flush();

        // On désactive aussi tout le reste
        produitRepository.findAll().forEach(prod -> {
            prod.setActif(false);
            produitRepository.save(prod);
        });
        produitRepository.flush();

        assertThat(produitRepository.findByActifTrue()).isEmpty();
    }

    // =====================================================================
    // findByCategorieIgnoreCase
    // =====================================================================

    @Test
    void findByCategorieIgnoreCase() {
        List<Produit> result =
                produitRepository.findByCategorieIgnoreCase("mobilier");

        assertThat(result)
                .hasSize(3)
                .extracting(Produit::getCategorie)
                .containsOnly("Mobilier");
    }

    @Test
    void findByCategorieIgnoreCase_uppercase() {
        List<Produit> result =
                produitRepository.findByCategorieIgnoreCase("MOBILIER");

        assertThat(result).hasSize(3);
    }

    @Test
    void findByCategorieIgnoreCase_mixedCase() {
        List<Produit> result =
                produitRepository.findByCategorieIgnoreCase("MoBiLiEr");

        assertThat(result).hasSize(3);
    }

    @Test
    void findByCategorieIgnoreCase_shouldReturnEmpty_whenNoMatch() {
        List<Produit> result =
                produitRepository.findByCategorieIgnoreCase("Inexistante");

        assertThat(result).isEmpty();
    }

    @Test
    void findByCategorieIgnoreCase_shouldIncludeInactive() {
        // "Décoration" ne contient que la lampe inactive
        List<Produit> result =
                produitRepository.findByCategorieIgnoreCase("décoration");

        assertThat(result)
                .hasSize(1)
                .extracting(Produit::getNom)
                .containsExactly("Lampe");
    }

    // =====================================================================
    // findByNomContainingIgnoreCase
    // =====================================================================

    @Test
    void findByNomContainingIgnoreCase() {
        List<Produit> result =
                produitRepository.findByNomContainingIgnoreCase("cha");

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactly("Chaise");
    }

    @Test
    void findByNomContainingIgnoreCase_shouldMatchAnywhereInName() {
        // "a" est contenu dans Chaise, Table, Lampe, Canapé
        List<Produit> result =
                produitRepository.findByNomContainingIgnoreCase("a");

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactlyInAnyOrder("Chaise", "Table", "Lampe", "Canapé");
    }

    @Test
    void findByNomContainingIgnoreCase_uppercase() {
        List<Produit> result =
                produitRepository.findByNomContainingIgnoreCase("TABLE");

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactly("Table");
    }

    @Test
    void findByNomContainingIgnoreCase_shouldReturnEmpty_whenNoMatch() {
        List<Produit> result =
                produitRepository.findByNomContainingIgnoreCase("xyz");

        assertThat(result).isEmpty();
    }

    // =====================================================================
    // findByPrixBetween
    // =====================================================================

    @Test
    void findByPrixBetween() {
        List<Produit> result =
                produitRepository.findByPrixBetween(30.0, 200.0);

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactlyInAnyOrder("Chaise", "Table");
    }

    @Test
    void findByPrixBetween_inclusiveBounds() {
        // Bornes exactes = incluses par BETWEEN
        List<Produit> result =
                produitRepository.findByPrixBetween(49.99, 199.99);

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactlyInAnyOrder("Chaise", "Table");
    }

    @Test
    void findByPrixBetween_shouldReturnEmpty_whenRangeTooHigh() {
        List<Produit> result =
                produitRepository.findByPrixBetween(1000.0, 2000.0);

        assertThat(result).isEmpty();
    }

    @Test
    void findByPrixBetween_minGreaterThanMax_shouldReturnEmpty() {
        List<Produit> result =
                produitRepository.findByPrixBetween(200.0, 50.0);

        assertThat(result).isEmpty();
    }

    // =====================================================================
    // findByStock
    // =====================================================================

    @Test
    void findByStock() {
        List<Produit> result = produitRepository.findByStock(0);

        assertThat(result)
                .hasSize(1)
                .extracting(Produit::getNom)
                .containsExactly("Lampe");
    }

    @Test
    void findByStock_multipleMatches() {
        persist(buildProduit("Autre rupture", "desc", 5.0, 0, "Cat", true));
        entityManager.flush();

        List<Produit> result = produitRepository.findByStock(0);

        assertThat(result)
                .extracting(Produit::getNom)
                .containsExactlyInAnyOrder("Lampe", "Autre rupture");
    }

    @Test
    void findByStock_shouldReturnEmpty_whenNoMatch() {
        List<Produit> result = produitRepository.findByStock(999);

        assertThat(result).isEmpty();
    }

    // =====================================================================
    // @PrePersist / mapping JPA
    // =====================================================================

    @Test
    void prePersist_shouldPopulateDateCreationAndDefaultActif() {
        Produit nouveau = buildProduit(
                "Nouveau", "desc", 10.0, 3, "Divers", null);
        Produit saved = produitRepository.saveAndFlush(nouveau);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getDateCreation()).isNotNull();
        assertThat(saved.getActif()).isTrue();
    }

    // =====================================================================
    // Helpers
    // =====================================================================

    private Produit persist(Produit p) {
        return entityManager.persistAndFlush(p);
    }

    private Produit buildProduit(String nom, String description, Double prix,
                                 Integer stock, String categorie, Boolean actif) {
        return Produit.builder()
                .nom(nom)
                .description(description)
                .prix(prix)
                .stock(stock)
                .categorie(categorie)
                .actif(actif)
                .build();
    }
}