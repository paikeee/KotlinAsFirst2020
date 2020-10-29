@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt
import kotlin.math.pow

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
    for (i in p.indices) {
        result += p[i] * (x.toDouble().pow(i)).toInt()
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
    for (i in (list.size - 1) downTo 0) {
        var a = i
        while (a != 0) {
            list[i] += list[a - 1]
            a--
        }
    }
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
    while (n1 % 2 == 0) {
        n1 /= 2
        result.add(2)
    }
    var k = 1
    while (n1 > 1) {
        if (k == 0) break
        k = 0
        for (i in 3..sqrt(n1.toDouble()).toInt() step 2) {
            if (n1 % i == 0) {
                n1 /= i
                result.add(i)
                k++
            }
            if (k == 1) break
        }
    }
    if (n1 > 1) result.add(n1)
    return result.sorted()
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var result = ""
    var n1 = n
    while (n1 % 2 == 0) {
        n1 /= 2
        result = result + 2 + "*"
    }
    var k = 1
    while (n1 > 1) {
        if (k == 0) break
        k = 0
        for (i in 3..sqrt(n1.toDouble()).toInt() step 2) {
            if (n1 % i == 0) {
                n1 /= i
                result = "$result$i*"
                k++
            }
            if (k == 1) break
        }
    }
    return if (n1 > 1) result + n1
    else result.substring(0, result.length - 1)
}

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
    var k: Int
    for (i in 0..((result.size - 1) / 2)) {
        k = result[i]
        result[i] = result[result.size - 1 - i]
        result[result.size - 1 - i] = k
    }
    return result
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
fun convertToString(n: Int, base: Int): String {
    val symbols = mutableListOf<Char>()
    for (i in 'a'..'z') {
        symbols.add(i)
    }
    var result = ""
    var n1 = n
    while (n1 >= base) {
        if (n1 % base > 9) result += symbols[n1 % base - 10]
        else result += n1 % base
        n1 /= base
    }
    if (n1 > 9) result += symbols[n1 % base - 10]
    else result += n1
    var result1 = ""
    for (i in result.length - 1 downTo 0) {
        result1 += result[i]
    }
    return result1
}


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var k = digits.size - 1
    var result = 0
    for (i in digits.indices) {
        result += digits[i] * ((base.toDouble()).pow(k)).toInt()
        k--
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
    val symbols = mutableListOf<Char>()
    for (i in 'a'..'z') {
        symbols.add(i)
    }
    val numbers = mutableListOf<Char>()
    for (i in '0'..'9') {
        numbers.add(i)
    }
    var k = str.length - 1 // степень числа
    var result = 0
    var number = 0 // разряд числа в системе base
    for (i in str.indices) {
        if (str[i] <= '9')
            for (j in '0'..'9') {
                if (str[i] == j) number = numbers.indexOf(j)
            } else
            for (j in 'a'..'z') {
                if (str[i] == j) number = symbols.indexOf(j) + 10
            }
        result += number * ((base.toDouble()).pow(k)).toInt()
        k--
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

fun firstdigit(n: Int): List<Char> {
    val result = mutableListOf<Char>()
    var number = n
    if (number == 9) {
        result.add('X')
        result.add('I')
    }
    if (number in 6..8) {
        number++
        while (number >= 7) {
            result.add('I')
            number--
        }
    }
    if (number in 4..6) result.add('V')
    if (number == 4) result.add('I')
    if (number in 1..3) {
        while (number >= 1) {
            result.add('I')
            number--
        }
    }
    return result
}

fun thirddigit(n: Int): List<Char> {
    val result = mutableListOf<Char>()
    var number = n
    if (number == 9) {
        result.add('M')
        result.add('C')
    }
    if (number in 6..8) {
        number++
        while (number >= 7) {
            result.add('C')
            number--
        }
    }
    if (number in 4..6) result.add('D')
    if (number == 4) result.add('C')
    if (number in 1..3) {
        while (number >= 1) {
            result.add('C')
            number--
        }
    }
    return result
}

fun seconddigit(n: Int): List<Char> {
    val result = mutableListOf<Char>()
    var number = n
    if (number == 9) {
        result.add('C')
        result.add('X')
    }
    if (number in 6..8) {
        number++
        while (number >= 7) {
            result.add('X')
            number--
        }
    }
    if (number in 4..6) result.add('L')
    if (number == 4) result.add('X')
    if (number in 1..3) {
        while (number >= 1) {
            result.add('X')
            number--
        }
    }
    return result
}

fun roman(n: Int): String {
    val result = mutableListOf<Char>() // перевернутый результат
    var n1 = n
    var count = 0 // номер разряда
    var result1 = "" // конечный результат
    while (n1 > 0) {
        count++
        when {
            (count == 1 && n1 % 10 != 0) -> result += firstdigit(n1 % 10)
            (count == 2 && n1 % 10 != 0) -> result += seconddigit(n1 % 10)
            (count == 3 && n1 % 10 != 0) -> result += thirddigit(n1 % 10)
            (count == 4) -> for (i in 1..n1 % 10) result.add('M')
        }
        n1 /= 10
    }
    for (i in (result.size - 1) downTo 0) {
        result1 += result[i].toString()
    }
    return result1
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun digitfirst(number: Int): List<String> {
    val result = arrayListOf<String>()
    if (number == 9) result.add("девять")
    if (number == 8) result.add("восемь")
    if (number == 7) result.add("семь")
    if (number == 6) result.add("шесть")
    if (number == 5) result.add("пять")
    if (number == 4) result.add("четыре")
    if (number == 3) result.add("три")
    if (number == 2) result.add("два")
    if (number == 1) result.add("один")
    return result
}

fun tenTonineteen(number: Int): List<String> {
    val result = arrayListOf<String>()
    if (number == 19) result.add("девятнадцать")
    if (number == 18) result.add("восемнадцать")
    if (number == 17) result.add("семнадцать")
    if (number == 16) result.add("шестнадцать")
    if (number == 15) result.add("пятнадцать")
    if (number == 14) result.add("четырнадцать")
    if (number == 13) result.add("тринадцать")
    if (number == 12) result.add("двенадцать")
    if (number == 11) result.add("одиннадцать")
    if (number == 10) result.add("десять")
    return result
}
fun digitsecond(number: Int): List<String> {
    val result = arrayListOf<String>()
    if (number == 9) result.add("девяносто")
    if (number == 8) result.add("восемьдесят")
    if (number == 7) result.add("семьдесят")
    if (number == 6) result.add("шестьдесят")
    if (number == 5) result.add("пятьдесят")
    if (number == 4) result.add("сорок")
    if (number == 3) result.add("тридцать")
    if (number == 2) result.add("двадцать")
    return result
}
fun digitthird(number: Int): List<String> {
    val result = arrayListOf<String>()
    if (number == 9) result.add("девятьсот")
    if (number == 8) result.add("восемьсот")
    if (number == 7) result.add("семьсот")
    if (number == 6) result.add("шестьсот")
    if (number == 5) result.add("пятьсот")
    if (number == 4) result.add("четыреста")
    if (number == 3) result.add("триста")
    if (number == 2) result.add("двести")
    if (number == 1) result.add("сто")
    return result
}
fun digitforth(number: Int): List<String> {
    val result = arrayListOf<String>()
    if (number == 9) result.add("девять тысяч")
    if (number == 8) result.add("восемь тысяч")
    if (number == 7) result.add("семь тысяч")
    if (number == 6) result.add("шесть тысяч")
    if (number == 5) result.add("пять тысяч")
    if (number == 4) result.add("четыре тысячи")
    if (number == 3) result.add("три тысячи")
    if (number == 2) result.add("две тысячи")
    if (number == 1) result.add("одна тысяча")
    if (number == 0) result.add("тысяч")
    return result
}

fun russian(n: Int): String {
    val result = arrayListOf<String>() // перевернутый результат
    var result1 = "" // конечный результат
    var n1 = n
    var count = 0 // номер разряда
    while (n1 > 0) {
        count++
        when {
            (n1 % 100 in 10..19 && (count == 1 || count == 4)) -> {
                if (count == 4) result.add("тысяч")
                result += tenTonineteen(n1 % 100)
                count++
                n1 /= 10
            }
            (count == 1 && n1 % 10 != 0) -> result += digitfirst(n1 % 10)
            (count == 2 && n1 % 10 != 0) -> result += digitsecond(n1 % 10)
            (count == 3 && n1 % 10 != 0) -> result += digitthird(n1 % 10)
            (count == 4) -> result += digitforth(n1 % 10)
            (count == 5 && n1 % 10 != 0) -> result += digitsecond(n1 % 10)
            (count == 6 && n1 % 10 != 0) -> result += digitthird(n1 % 10)
        }
        n1 /= 10

    }
    for (i in (result.size - 1) downTo 0) {
        result1 += result[i]
        if (i != 0) result1 += " "
    }
    return result1
}
