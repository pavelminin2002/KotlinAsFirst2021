@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.max

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var k = 0
    var number = n
    do {
        k++
        number /= 10
    } while (number != 0)
    return k
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var x1 = 1
    var x2 = 1
    var k = 2
    var sumValuess: Int
    while (n > k) {
        sumValuess = x1 + x2
        x1 = x2
        x2 = sumValuess
        k++
    }
    return x2
}
//{
//    var x1 = 1
//    var x2 = 1
//    var k = 0
//    var summa = 2
//    while (n - 2 > k) {
//        summa = x1 + x2
//        x1 = x2
//        x2 = summa
//        k++
//    }
//    return x2
//}
/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */

fun minDivisor(n: Int): Int {
    var m = 0
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            m = i
            break
        }
    }
    if (m == 0) {
        m = n
    }
    return m
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = if (minDivisor(n) == n) 1 else n / minDivisor(n)
//{
//    var m = 0
//    for (i in 2..sqrt(n.toDouble()).toInt()) {
//        if (n % i == 0) {
//            m = n / i
//            break
//        }
//    }
//    if (m == 0) {
//        m = 1
//    }
//    return m
//}
/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var n = 0
    var xx = x
    while (xx != 1) {
        n++
        if (xx % 2 == 0) xx /= 2 else xx = xx * 3 + 1
    }
    return n
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var x1 = m
    var x2 = n
    var p = 0
    while (x1 != 0 && x2 != 0) {
        if (x1 > x2) x1 %= x2 else x2 %= x1
    }
    p = max(x1, x2)
    return (m * n) / p
}

//{
//    var k = 0
//    var l = true
//    while (l) {
//        k++
//        if (k % m == 0 && k % n == 0) l = false
//    }
//    return k
//}
/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val smallestValue = min(m, n)
    var k = true
    for (i in 2..smallestValue) {
        if (m % i == 0 && n % i == 0) {
            k = false
            break
        }
    }
    return k
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var nn = n
    var newN = n % 10
    while (nn > 0 && nn > 9) {
        nn /= 10
        newN = newN * 10 + nn % 10
    }
    return newN
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var nn = n
    val k = nn % 10
    if (nn < 10) return false
    while (nn > 0) {
        if (k != nn % 10) return true
        nn /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var number = 1
    var q = 0
    var s = 0
    var i = 0
    while (true) {
        i++
        q = digitNumber(i * i)
        s += q
        if (s >= n) {
            number = i * i
            break
        }
    }
    return i1(s, n, number)

}
//if (s == n) return number % 10
//    else {
//        while (s != n) {
//            number /= 10
//            s -= 1
//        }
//        return number % 10
//    }
/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var number = 1
    var q = 0
    var s = 0
    var i = 0
    while (true) {
        i++
        q = digitNumber(fib(i))
        s += q
        if (s >= n) {
            number = fib(i)
            break
        }
    }
    return i1(s, n, number)
}

private fun i1(s: Int, n: Int, number: Int): Int {
    var s1 = s
    var number1 = number
    while (s1 != n) {
        number1 /= 10
        s1 -= 1
    }
    return number1 % 10
}