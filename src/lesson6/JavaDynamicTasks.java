package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.min;


@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }


    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // Трудоемкость: T = O(n^2)
    // Ресурсоемкость: R = O(n)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() == 0 || list.size() == 1) {
            return list;
        }
        int[] lis = new int[list.size()];
        Arrays.fill(lis, 1);
        int lisIndex = 0, count = 0;
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) > list.get(j) && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
                if (lis[lisIndex] < lis[i]) {
                    lisIndex = i;
                    count = lis[i];
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        int i = lisIndex, possibleI;
        result.add(list.get(i));
        i--;
        while (i >= 0 && count >= 0) {
            possibleI = -1;
            if (lis[i] == count - 1 && list.get(i) < list.get(lisIndex)) {
                for (int j = i - 1; j >= count - 2; j--) {
                    if (lis[j] == count - 1 && list.get(j) < list.get(lisIndex)) {
                        possibleI = j;
                    }
                }
                if (possibleI != -1) {
                    i = possibleI;
                }
                result.add(list.get(i));
                lisIndex = i;
                count--;
            }
            i--;
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    // Трудоемкость: T = O(n * m)
    // Ресурсоемкость: R = O(n * m)
    public static int shortestPathOnField(String inputName) throws NotImplementedError,
            IOException {
        List<String> stackOfNumbers = new ArrayList<>();
        String currentLine;
        int height = 0, width;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName))) {
            while ((currentLine = bufferedReader.readLine()) != null) {
                stackOfNumbers.add(currentLine);
                height++;
            }
        }
        width = stackOfNumbers.get(0).split(" ").length;
        int i, j;
        int[][] matrixOfMinSums = new int[height][width];
        int[][] inputMatrix = new int[height][width];
        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                inputMatrix[i][j] = stackOfNumbers.get(i).charAt(2 * (j)) - 48;
                matrixOfMinSums[i][j] = inputMatrix[i][j];
            }
        }
        for (i = 1; i < height; i++) {
            matrixOfMinSums[i][0] = matrixOfMinSums[i - 1][0] + inputMatrix[i][0];
        }
        for (i = 1; i < width; i++) {
            matrixOfMinSums[0][i] = matrixOfMinSums[0][i - 1] + inputMatrix[0][i];
        }
        for (i = 1; i < height; i++) {
            for (j = 1; j < width; j++) {
                matrixOfMinSums[i][j] = min(matrixOfMinSums[i - 1][j - 1],
                        min(matrixOfMinSums[i - 1][j], matrixOfMinSums[i][j - 1])) + inputMatrix[i][j];
            }
        }
        return matrixOfMinSums[height - 1][width - 1];
    }

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5
}
