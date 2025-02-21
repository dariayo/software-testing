package com.extension;

import com.domen.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockInitExtension.class)
public class AutoMockInitTest {
    @Mock
    private Edition mockEdition;

    @Test
    void testAddEdition() {
        Book book = new Book.BookBuilder().setTitle("Test Book").setIntroduction("Test Intro").build();

        book.addEdition(mockEdition);

        assertTrue(book.getEditions().contains(mockEdition));
    }

    @Test
    void testEditionConflict() {
        Book book = new Book.BookBuilder().setTitle("Test Book").setIntroduction("Test Intro").build();
        Edition edition1 = new Edition(1);
        book.addEdition(edition1);
        Edition edition2 = new Edition(1);

        assertThrows(IllegalArgumentException.class, () -> book.addEdition(edition2));
    }
}
