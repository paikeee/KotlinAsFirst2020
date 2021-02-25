@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import kotlin.math.abs
import java.util.*

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

    private val pairs = TreeMap<Double, Double>()

    /**
     * Количество пар в таблице
     */
    val size: Int get() = pairs.size

    /**
     * Добавить новую пару.
     * Вернуть true, если пары с заданным x ещё нет,
     * или false, если она уже есть (в этом случае перезаписать значение y)
     */
    fun add(x: Double, y: Double): Boolean =
        if (pairs[x] == null) {
            pairs[x] = y
            true
        } else false

    /**
     * Удалить пару с заданным значением x.
     * Вернуть true, если пара была удалена.
     */
    fun remove(x: Double): Boolean =
        if (pairs[x] != null) {
            pairs.remove(x)
            true
        } else false

    /**
     * Вернуть коллекцию из всех пар в таблице
     */
    fun getPairs(): Collection<Pair<Double, Double>> = pairs.toList()

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
        return Pair(min, pairs[min]!!)
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
        when {
            pairs.isEmpty() -> throw IllegalStateException()
            pairs.size == 1 -> return pairs.values.first()
            pairs[x] != null -> return pairs[x]!!
        }
        val left = pairs.floorKey(x)
        val right = pairs.ceilingKey(x)
        return when {
            left != null && right != null ->
                pairs[left]!! + (x - left) * (pairs[right]!! - pairs[left]!!) / (right - left)
            left == null -> {
                val right1 = pairs.higherKey(right)
                pairs[right]!! + (x - right) * (pairs[right1]!! - pairs[right]!!) / (right1 - right)
            }
            else -> {
                val left1 = pairs.lowerKey(left)
                pairs[left1]!! + (x - left1) * (pairs[left]!! - pairs[left1]!!) / (left - left1)
            }
        }
    }

    /**
     * Таблицы равны, если в них одинаковое количество пар,
     * и любая пара из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean =
        this === other || other is TableFunction && other.pairs.size == pairs.size
                && pairs.toList().all { it in other.pairs.toList() }

    override fun hashCode(): Int = pairs.hashCode()

}