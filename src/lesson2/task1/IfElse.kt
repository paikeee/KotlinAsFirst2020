@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
fun mod(m: Int): Int = m % 10
if ((5 <= mod(age)) || (mod(age) == 0)) return ("$age лет")
else if ((age in 11..14) || (age in 111..114)) return ("$age лет")
    else if (mod(age) == 1) return ("$age год")
    else return ("$age года")
}


/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val half = (s1 + s2 + s3) / 2
    if (s1 > half) return (half / v1)
    else if (s2 > half - s1) return ((half - s1) / v2 + t1)
    else return ((half - s1 - s2) / v3 + t1 + t2)
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if (((kingX == rookX1) && (kingX == rookX2))
        || ((kingX == rookX1) && (kingY == rookY2))
        || ((kingY == rookY1) && (kingX == rookX2))
        || ((kingY == rookY1) && (kingY == rookY2))) return (3)
    else if ((kingX == rookX1) || (kingY == rookY1)) return (1)
    else if ((kingX == rookX2) || (kingY == rookY2)) return (2)
    else return (0)
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val y = kingY - bishopY
    val x = kingX - bishopX
    if (x != y) {
        if (((x * (-1) == y) && (rookX == kingX)) || ((x * (-1) == y) && (rookY == kingY))) return (3)
        else if (x * (-1) == y) return (2)
        else if ((rookX == kingX) || (rookY == kingY)) return (1)
        else return (0)
    } else if (((x == y) && (rookX == kingX)) || ((x == y) && (rookY == kingY))) return (3)
    else if (x == y) return (2)
    else if ((rookX == kingX) || (rookY == kingY)) return (1)
    else return (0)
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    fun sqr(s: Double) = s * s
    val cosA = (sqr(a) - sqr(b) - sqr(c)) / (-2 * b * c)
    val cosB = (sqr(b) - sqr(a) - sqr(c)) / (-2 * a * c)
    val cosC = (sqr(c) - sqr(a) - sqr(b)) / (-2 * b * a)
    if ((cosA >= 1) || (cosB >= 1) || (cosC >= 1) || (cosA <= -1) || (cosB <= -1) || (cosC <= -1)) return (-1)
    else if ((cosA > 0) && (cosB > 0) && (cosC > 0)) return (0)
    else if ((cosA == 0.0) || (cosB == 0.0) || (cosC == 0.0)) return (1)
    else return (2)
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((a <= c) && (c <= b) && (d >= b)) return (b - c)
    else if ((c <= a) && (b <= d)) return (b - a)
    else if ((c <= a) && (b >= d) && (d >= a)) return (d - a)
    else if ((a <= c) && (d <= b)) return (d - c)
    else return (-1)
}
