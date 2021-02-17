@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr
import java.lang.IllegalArgumentException


val finder = Regex("""([+-]?[\d]*[.]?[\d]*)""")
val format = Regex("""-?\d*.?\d*[+-]\d*.?\d*i""")

private fun checker(s: String, i: Int): Double =
    if (format.matches(s))
        (finder.findAll(s).elementAt(i).value.toDouble())
    else throw IllegalArgumentException()

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */

class Complex(val re: Double, val im: Double) {


    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */

    constructor(s: String) : this(checker(s, 0), checker(s, 1))

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        other.re * im + re * other.im
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
        (other.re * im - re * other.im) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && re == other.re && im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String =
        when {
            re == 0.0 && im == 0.0 -> "0.0"
            re != 0.0 && im == 0.0 -> "$re"
            re == 0.0 && im != 0.0 -> "${im}i"
            im > 0 -> "${re}+${im}i"
            else -> "$re${im}i"
        }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}

