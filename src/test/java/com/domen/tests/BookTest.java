package com.domen.tests;

import com.domen.model.Book;
import com.domen.model.Contributor;
import com.domen.model.Edition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book guideBook;
    private Edition edition;
    private Contributor andrew;
    private Contributor vladimir;

    @BeforeEach
    void setUp() {
        guideBook = new Book("Путеводитель по Галактике для автостопщиков", "Космос велик");
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
        boolean conflict = edition.submitEdit(andrew, "Повторная правка от Andrew");

        assertFalse(conflict);
        assertEquals(1, edition.getEditHistory().size());
    }

    @Test
    void testReputationImpact() {
        boolean firstEdit = edition.submitEdit(vladimir, "Информация о планете");
        boolean secondEdit = edition.submitEdit(andrew, "Правка");

        assertTrue(firstEdit);
        assertTrue(secondEdit);

        assertTrue(andrew.getReputation() > vladimir.getReputation());
    }
}

