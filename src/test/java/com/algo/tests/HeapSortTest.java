package com.algo.tests;

import com.algo.HeapSort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeapSortTest {

    @ParameterizedTest
    @MethodSource("provideArrays")
    public void checkHeapSort(int[] array) {
        HeapSort.heapSort(array);
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, array);
    }

    @Test
    public void checkNullHeapSort() {
        assertThrows(NullPointerException.class, () -> HeapSort.heapSort(null));
    }

    private static List<int[]> provideArrays() {
        return List.of(
                new int[]{2, 10, 224, 3, 25, 58},
                new int[]{56, 45, 228, 12, 5},
                new int[]{44},
                new int[]{},
                new int[]{5, 6, 7, 8},
                new int[]{24, 674, 234, 678, 3, 254, 987, 5, 76, 83, 65, 24, 758, 342, 75, 90, 525, 75, 743, 327, 85, 587}

        );
    }

}
