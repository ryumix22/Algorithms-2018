@file:Suppress("UNUSED_PARAMETER")

package lesson6

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
/*
  Вспомогательные материалы: https://habr.com/post/142825/
  Ресурсоемкость - O(len(x)*len(y))
  Трудоемкость - O(len(x)*len(y))
 */
fun longestCommonSubSequence(first: String, second: String): String {
    var result = ""
    val firstString = first.length
    val secondString = second.length
    val matrix = Array(firstString + 1) { IntArray(secondString + 1) }
    for (i in 1 until firstString + 1) {
        for (j in 1 until secondString + 1) {
            if (first[i - 1] == second[j - 1]) {
                matrix[i][j] = 1 + matrix[i - 1][j - 1]
            } else
                matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1])
        }
    }
    var i = firstString
    var j = secondString
    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                result = first[i - 1] + result
                i--
                j--
            }
            matrix[i][j] == matrix[i - 1][j] -> i--
            else -> j--
        }
    }
    return result
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
/*
  Вспомогательные материалы: http://e-maxx.ru/algo/longest_increasing_subseq_log
  Ресурсоемкость - O(n^2)
  Трудоемкость - O(n^2)
  */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val result = ArrayList<Int>()
    if (list.isEmpty()) return result
    val ancestor = IntArray(list.size)
    val maxLength = IntArray(list.size)
    for (i in 0 until list.size) {
        maxLength[i] = 1
        ancestor[i] = -1
        for (j in 0 until i) {
            if (list[j] < list[i] && maxLength[j] + 1 > maxLength[i]) {
                maxLength[i] = maxLength[j] + 1
                ancestor[i] = j
            }
        }
    }
    var length = 0
    var position = 0
    for (i in maxLength.indices) {
        if (maxLength[i] > length) {
            position = i
            length = maxLength[i]
        }
    }
    while (position != -1) {
        result.add(0, list[position])
        position = ancestor[position]
    }
    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5