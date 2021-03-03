@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

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
    fun add(x: Double, y: Double): Boolean = pairs.putIfAbsent(x, y) == null

    /**
     * Удалить пару с заданным значением x.
     * Вернуть true, если пара была удалена.
     */
    fun remove(x: Double): Boolean = pairs.remove(x) != null

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
        val left = pairs.floorEntry(x)
        val right = pairs.ceilingEntry(x)
        return when {
            left == null -> right?.toPair()
            right == null -> left.toPair()
            (x - left.toPair().first <= right.toPair().first - x) -> left.toPair()
            else -> right.toPair()
        }
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
        val left = pairs.floorEntry(x)
        val right = pairs.ceilingEntry(x)
        return when {
            left != null && right != null ->
                left.value + (x - left.key) * (right.value - left.value) / (right.key - left.key)
            left == null -> {
                val right1 = pairs.higherEntry(right.key)
                right.value + (x - right.key) * (right1.value - right.value) / (right1.key - right.key)
            }
            else -> {
                val left1 = pairs.lowerEntry(left.key)
                left1.value + (x - left1.key) * (left.value - left1.value) / (left.key - left1.key)
            }
        }
    }

    /**
     * Таблицы равны, если в них одинаковое количество пар,
     * и любая пара из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean =
        this === other || other is TableFunction && this.pairs == other.pairs

    override fun hashCode(): Int = pairs.hashCode()

}