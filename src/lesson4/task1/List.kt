@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt


// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var vector = 0.0
    for (element in v) {
        vector += sqr(element)
    }
    return sqrt(vector)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isNotEmpty()) {
        return list.sum() / list.size
    }
    return (0.0)
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val minus = list.sum() / list.size
        for (i in list.indices) {
            list[i] -= minus
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    var grade = 1
    for (i in p.indices) {
        result += p[i] * grade
        grade *= x
    }
    return result
}


/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var n1 = n
    var k = 2
    while (n1 > 1) {
        while (n1 % k == 0) {
            result.add(k)
            n1 /= k
        }
        k++
        if (k > sqrt(n1.toDouble()).toInt()) break
    }
    return if (n1 > 1) result + n1
    else result
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var n1 = n
    while (n1 >= base) {
        result.add(n1 % base)
        n1 /= base
    }
    result.add(n1)
    return result.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String =
    convert(n, base).joinToString(separator = "", transform = { if (it <= 9) "$it" else ('a' + it - 10).toString() })

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var grade = 1
    var result = 0
    for (digit in digits.reversed()) {
        result += digit * grade
        grade *= base
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    var grade = 1
    for (element in str.reversed()) {
        val number = if (element <= '9')
            element - '0'
        else
            element - 'a' + 10
        result += number * grade
        grade *= base
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */

fun digit(n: Int, list: List<Char>): String {
    var result = ""
    if (n == 9) {
        result += list[2]
        result += list[0]
    }
    if (n in 6..8) result += list[0].toString().repeat(n - 5)
    if (n in 4..8) result += list[1]
    if (n == 4) result += list[0]
    if (n in 1..3) result += list[0].toString().repeat(n)
    return result
}

fun roman(n: Int): String {
    var result = ""// перевернутый результат
    var n1 = n
    var count = 0 // номер разряда
    while (n1 > 0) {
        count++
        when {
            (count == 1 && n1 % 10 != 0) -> result += digit(n1 % 10, listOf('I', 'V', 'X'))
            (count == 2 && n1 % 10 != 0) -> result += digit(n1 % 10, listOf('X', 'L', 'C'))
            (count == 3 && n1 % 10 != 0) -> result += digit(n1 % 10, listOf('C', 'D', 'M'))
            (count == 4) -> result += "M".repeat(n1 % 10)
        }
        n1 /= 10
    }
    return result.reversed()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = ""
    var n1 = n
    var count = 0 // номер разряда
    while (n1 > 0) {
        count++
        var list: List<String>
        when {
            (n1 % 100 in 10..19 && (count == 1 || count == 4)) -> {
                if (count == 4) result += " чясыт"
                list = listOf(
                    "десять ",
                    "одиннадцать ",
                    "двенадцать ",
                    "тринадцать ",
                    "четырнадцать ",
                    "пятнадцать ",
                    "шестнадцать ",
                    "семнадцать ",
                    "восемнадцать ",
                    "девятнадцать "
                )
                result += list[n1 % 10].reversed()
                count++
                n1 /= 10
            }
            (count == 1 && n1 % 10 != 0) -> {
                list = listOf("один ", "два ", "три ", "четыре ", "пять ", "шесть ", "семь ", "восемь ", "девять ")
                result += list[n1 % 10 - 1].reversed()
            }

            (count == 2 && n1 % 10 != 0) -> {
                list = listOf(
                    "двадцать ",
                    "тридцать ",
                    "сорок ",
                    "пятьдесят ",
                    "шестьдесят ",
                    "семьдесят ",
                    "восемьдесят ",
                    "девяносто "
                )
                result += list[n1 % 10 - 2].reversed()
            }
            (count == 3 && n1 % 10 != 0) -> {
                list = listOf(
                    "сто ",
                    "двести ",
                    "триста ",
                    "четыреста ",
                    "пятьсот ",
                    "шестьсот ",
                    "семьсот ",
                    "восемьсот ",
                    "девятьсот "
                )
                result += list[n1 % 10 - 1].reversed()
            }
            (count == 4) -> {
                list = listOf(
                    "тысяч ",
                    "одна тысяча ",
                    "две тысячи ",
                    "три тысячи ",
                    "четыре тысячи ",
                    "пять тысяч ",
                    "шесть тысяч ",
                    "семь тысяч ",
                    "восемь тысяч ",
                    "девять тысяч "
                )
                result += list[n1 % 10].reversed()
            }
            (count == 5 && n1 % 10 != 0) -> {
                list = listOf(
                    "двадцать ",
                    "тридцать ",
                    "сорок ",
                    "пятьдесят ",
                    "шестьдесят ",
                    "семьдесят ",
                    "восемьдесят ",
                    "девяносто "
                )
                result += list[n1 % 10 - 2].reversed()
            }
            (count == 6 && n1 % 10 != 0) -> {
                list = listOf(
                    "сто ",
                    "двести ",
                    "триста ",
                    "четыреста ",
                    "пятьсот ",
                    "шестьсот ",
                    "семьсот ",
                    "восемьсот ",
                    "девятьсот "
                )
                result += list[n1 % 10 - 1].reversed()
            }
        }
        n1 /= 10

    }
    return result.reversed().trim()
}

