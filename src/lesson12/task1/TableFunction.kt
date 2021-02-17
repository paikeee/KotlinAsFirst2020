@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import kotlin.math.abs

/**
 * Класс "табличная функция".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 16.
 * Объект класса хранит таблицу значений функции (y) от одного аргумента (x).
 * В таблицу можно добавлять и удалять пары (x, y),
 * найти в ней ближайшую пару (x, y) по заданному x,
 * найти (интерполяцией или экстраполяцией) значение y по заданному x.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class TableFunction {

    private val pairs = mutableListOf<Pair<Double, Double>>()

    /**
     * Количество пар в таблице
     */
    val size: Int get() = pairs.size

    /**
     * Добавить новую пару.
     * Вернуть true, если пары с заданным x ещё нет,
     * или false, если она уже есть (в этом случае перезаписать значение y)
     */
    fun add(x: Double, y: Double): Boolean {
        val pair = pairs.find { it.first == x }
        if (pair == null) {
            pairs.add(Pair(x, y))
            return true
        } else pairs.remove(pair)
        pairs.add(Pair(x, y))
        return false
    }

    /**
     * Удалить пару с заданным значением x.
     * Вернуть true, если пара была удалена.
     */
    fun remove(x: Double): Boolean {
        val pair = pairs.find { it.first == x }
        if (pair != null)
            pairs.remove(pair)
        else return false
        return true
    }

    /**
     * Вернуть коллекцию из всех пар в таблице
     */
    fun getPairs(): Collection<Pair<Double, Double>> = pairs

    /**
     * Вернуть пару, ближайшую к заданному x.
     * Если существует две ближайшие пары, вернуть пару с меньшим значением x.
     * Если таблица пуста, бросить IllegalStateException.
     */
    fun findPair(x: Double): Pair<Double, Double>? {
        if (pairs.isEmpty()) throw IllegalStateException()
        var min = Double.MAX_VALUE
        for ((first) in pairs)
            when {
                abs(x - first) < abs(min - x) -> min = first
                abs(x - first) == abs(min - x) && first < min -> min = first
            }
        return pairs.find { it.first == min }
    }

    /**
     * Вернуть значение y по заданному x.
     * Если в таблице есть пара с заданным x, взять значение y из неё.
     * Если в таблице есть всего одна пара, взять значение y из неё.
     * Если таблица пуста, бросить IllegalStateException.
     * Если существуют две пары, такие, что x1 < x < x2, использовать интерполяцию.
     * Если их нет, но существуют две пары, такие, что x1 < x2 < x или x > x2 > x1, использовать экстраполяцию.
     */
    fun getValue(x: Double): Double {
        val xy = pairs.find { it.first == x }
        when {
            pairs.size == 0 -> throw IllegalStateException()
            pairs.size == 1 -> return pairs.first().second
            (xy != null) -> return xy.second
        }
        var fLeft = Double.MAX_VALUE
        var sLeft = Double.MAX_VALUE
        var fRight = Double.MIN_VALUE
        var sRight = Double.MIN_VALUE
        for ((first) in pairs)
            when {
                first <= fLeft && first < x -> {
                    sLeft = fLeft
                    fLeft = first
                }
                first >= fRight && first > x -> {
                    sRight = fRight
                    fRight = first
                }
            }
        return when {
            (fLeft != Double.MAX_VALUE && fRight != Double.MIN_VALUE) -> {
                val yLeft = (pairs.find { it.first == fLeft })!!.second
                val yRight = (pairs.find { it.first == fRight })!!.second
                yLeft + (x - fLeft) * (yRight - yLeft) / (fRight - fLeft)
            }
            (sLeft != Double.MAX_VALUE) -> {
                val y1 = (pairs.find { it.first == fLeft })!!.second
                val y2 = (pairs.find { it.first == sLeft })!!.second
                y2 + (x - sLeft) * (y1 - y2) / (fLeft - sLeft)
            }
            else -> {
                val y1 = (pairs.find { it.first == fRight })!!.second
                val y2 = (pairs.find { it.first == sRight })!!.second
                y1 + (x - fRight) * (y2 - y1) / (sRight - fRight)
            }
        }
    }

    /**
     * Таблицы равны, если в них одинаковое количество пар,
     * и любая пара из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean =
        other is TableFunction && other.pairs.size == pairs.size && pairs.all { it in other.pairs }

    override fun hashCode(): Int = pairs.hashCode()
}