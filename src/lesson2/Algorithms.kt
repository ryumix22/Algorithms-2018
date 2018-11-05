@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.io.File

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
    val list = File(inputName).readLines()
    var maxDifference = 0
    var minPrice = Integer.MAX_VALUE
    var buy = 0
    var sell = 0
    var a = 0

    for (i in 0 until list.size) {
        when {
            list[i].toInt() < minPrice -> {
                minPrice = list[i].toInt()
                a = i
            }
            list[i].toInt() - minPrice > maxDifference -> {
                maxDifference = list[i].toInt() - minPrice
                buy = a
                sell = i
            }
        }
    }
    return Pair(buy + 1, sell + 1)
}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */
private fun solver(number: Int, step: Int): Int {
    return if (number == 1) 0
    else (solver(number - 1, step) + step) % number
}

fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    if (menNumber == 0) throw IllegalArgumentException("not 0")
    return when (choiceInterval) {
        1 -> menNumber
        2 -> 1
        else -> solver(menNumber, choiceInterval) + 1
    }
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
fun longestCommonSubstring(first: String, second: String): String {
    if (first.isEmpty() || second.isEmpty()) {
        return ""
    }
    if (first == second) {
        return first
    }
    val matrix = arrayOfNulls<IntArray>(first.length)
    var maxLength = 0
    var maxI = 0
    for (i in matrix.indices) {
        matrix[i] = IntArray(second.length)
        for (j in 0 until matrix[i]!!.size) {
            if (first[i] == second[j]) {
                if (i != 0 && j != 0) {
                    matrix[i]!![j] = matrix[i - 1]!![j - 1] + 1
                } else {
                    matrix[i]!![j] = 1
                }
                if (matrix[i]!![j] > maxLength) {
                    maxLength = matrix[i]!![j]
                    maxI = i
                }
            }
        }
    }
    return first.substring(maxI - maxLength + 1, maxI + 1)
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
fun calcPrimesNumber(limit: Int): Int {
    var result = 0
    when (limit) {
        0 -> return 0
        1 -> return 0
        else -> {
            val list = IntArray(limit + 1)
            for (i in 0..limit) list[i] = 1
            var i = 2
            while (i * i <= limit) {
                if (list[i] == 1) {
                    var j = i * i
                    while (j <= limit) {
                        list[j] = 0
                        j += i
                    }
                }
                i++
            }
            for (i in 2..limit) if (list[i] == 1) result++
            return result
        }
    }
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    TODO()
}