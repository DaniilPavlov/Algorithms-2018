package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */

    // Трудоемкость: T = O(m * n)
    // Ресурсоемкость: R = O(n)
    static public String longestCommonSubstring(final String first, final String second) throws NotImplementedError {
        int maxLength = 0;
        int iMax = 0;
        int[][] matrix = new int[first.length()][second.length()];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) != second.charAt(j))
                    continue;
                if (i == 0 || j == 0)
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                if (matrix[i][j] > maxLength) {
                    maxLength = matrix[i][j];
                    iMax = i;
                }
            }
        }
        if (maxLength == 0) return "";
        int i = iMax - maxLength + 1;
        return first.substring(i, iMax + 1);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */

    // Трудоемкость: T = O(n * m)
    // Ресурсоемкость: R = O(n)
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws NotImplementedError,
            IOException {
        List<String> stackOfLetters = new ArrayList<>();
        String currentLine;
        int height = 0, width;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputName))) {
            while ((currentLine = bufferedReader.readLine()) != null) {
                stackOfLetters.add(currentLine);
                height++;
            }
        }
        width = stackOfLetters.get(0).split(" ").length;
        boolean[][] wasIHere = new boolean[height + 2][width + 2];
        char[][] data = dataInput(stackOfLetters, height + 2, width + 2);
        Set<String> answer = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < height + 2; i++)
                for (int j = 0; j < width + 2; j++)
                    wasIHere[i][j] = false;
            boolean wordFound = false;
            for (int i = 1; i <= height; i++) {
                if (wordFound)
                    break;
                for (int j = 1; j <= width; j++) {
                    if (word.charAt(0) == data[i][j])
                        if (search(word, 1, i, j, data, wasIHere.clone())) {
                            answer.add(word);
                            wordFound = true;
                            break;
                        }
                }
            }
        }
        return answer;
    }

    private static char[][] dataInput(List<String> stackOfLetters, int height, int width) {
        char[][] data = new char[height][width];
        for (int i = 0; i < height; i++) {
            data[i][0] = '0';
            data[i][width - 1] = '0';
        }
        for (int j = 0; j < width; j++) {
            data[0][j] = '0';
            data[height - 1][j] = '0';
        }
        for (int i = 1; i <= height - 2; i++) {
            for (int j = 1; j <= width - 2; j++) {
                data[i][j] = stackOfLetters.get(i - 1).charAt(2 * (j - 1));
            }
        }
        return data;
    }

    private static boolean search(String word, int pos, int ii, int jj, final char[][] data, boolean[][] wasIHere) {
        wasIHere[ii][jj] = true;
        if (pos == word.length())
            return true;
        boolean downWay = false, rightWay = false, upWay = false, leftWay = false;
        if (data[ii + 1][jj] == word.charAt(pos) && !wasIHere[ii + 1][jj])
            downWay = search(word, pos + 1, ii + 1, jj, data, wasIHere.clone());
        if (data[ii][jj + 1] == word.charAt(pos) && !wasIHere[ii][jj + 1])
            rightWay = search(word, pos + 1, ii, jj + 1, data, wasIHere.clone());
        if (data[ii - 1][jj] == word.charAt(pos) && !wasIHere[ii - 1][jj])
            upWay = search(word, pos + 1, ii - 1, jj, data, wasIHere.clone());
        if (data[ii][jj - 1] == word.charAt(pos) && !wasIHere[ii][jj - 1])
            leftWay = search(word, pos + 1, ii, jj - 1, data, wasIHere.clone());
        return downWay || rightWay || upWay || leftWay;
    }
}
