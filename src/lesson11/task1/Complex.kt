@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    if (s.matches(Regex("""\d+[+-]\d+i"""))) {
        val index = s.indexOfFirst { it == '+' || it == '-' }
        return Complex(s.substring(0, index).toDouble(), s.substring(index, s.length - 1).toDouble())
    } else throw IllegalArgumentException()
}

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
    operator fun minus(other: Complex): Complex = this + other.unaryMinus()

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        re * other.im + im * other.re
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        val x = other.re * other.re + other.im * other.im
        return Complex(
            (re * other.re + im * other.im) / x,
            (im * other.re - re * other.im) / x
        )
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean =
        other is Complex && (re == other.re && im == other.im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        return if (im >= 0) "$re+${im}i"
        else "$re${im}i"
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }

}
