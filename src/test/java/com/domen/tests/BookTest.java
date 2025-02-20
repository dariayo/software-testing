package com.domen.tests;

import com.domen.model.Book;
import com.domen.model.Edition;
import com.domen.model.Contributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book guideBook;
    private Edition edition;
    private Contributor andrew;
    private Contributor vladimir;

    @BeforeEach
    void setUp() {
        guideBook = new Book.BookBuilder()
                .setTitle("Путеводитель по Галактике для автостопщиков")
                .setIntroduction("Космос велик")
                .build();

        edition = new Edition(1);
        andrew = new Contributor("Andrew", 80);
        vladimir = new Contributor("Vladimir", 60);

        edition.addContributor(andrew);
        edition.addContributor(vladimir);
        guideBook.addEdition(edition);
    }

    @Test
    void testSubmitEditSuccessfully() {
        boolean editResult = edition.submitEdit(andrew, "Добавлена новая информация");
        assertTrue(editResult);
        assertEquals(1, edition.getEditHistory().size());
    }

    @Test
    void testConflictDetection() {
        edition.submitEdit(andrew, "Обновление данных о планете");

        assertThrows(IllegalArgumentException.class, () -> edition.submitEdit(andrew, "Повторная правка от Andrew"));
        assertEquals(1, edition.getEditHistory().size());
    }

    @Test
    void testReputationImpact() {
        boolean firstEdit = edition.submitEdit(vladimir, "Информация о планете");
        boolean secondEdit = edition.submitEdit(andrew, "Правка");

        assertTrue(firstEdit);
        assertTrue(secondEdit);

        assertTrue(andrew.reputation() > vladimir.reputation());
    }

    @Test
    void testBookInitialization() {
        assertEquals("Путеводитель по Галактике для автостопщиков", guideBook.getTitle());
        assertEquals("Космос велик", guideBook.getIntroduction());
        assertEquals(1, guideBook.getEditions().size());
    }

    @ParameterizedTest
    @CsvSource({
            "6",
            "7",
            "8"
    })
    void testAddEmptyEdition(int version) {
        Edition emptyEdition = new Edition(version);

        guideBook.addEdition(emptyEdition);

        assertTrue(guideBook.getEditions().stream().anyMatch(e -> e.getVersion() == version),
                "Редакция с версией " + version + " должна быть добавлена");

        assertTrue(emptyEdition.getContributors().isEmpty(),
                "Редакция с версией " + version + " должна быть пустой");

        assertTrue(emptyEdition.getEditHistory().isEmpty(),
                "Редакция с версией " + version + " не должна иметь истории изменений");
    }
}
