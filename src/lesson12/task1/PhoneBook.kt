@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

import org.junit.Test

fun main() {
    val x = mutableMapOf("Васильев Дмитрий" to "+79217654321", "Иванов Петр" to "+79211234567")
    val y = mutableMapOf("Иванов Петр" to "+79211234567", "Васильев Дмитрий" to "+79217654321")
    println(x.toSortedMap() == y.toSortedMap())
}

/**
 * Класс "Телефонная книга".
 *
 * Общая сложность задания -- средняя, общая ценность в баллах -- 14.
 * Объект класса хранит список людей и номеров их телефонов,
 * при чём у каждого человека может быть более одного номера телефона.
 * Человек задаётся строкой вида "Фамилия Имя".
 * Телефон задаётся строкой из цифр, +, *, #, -.
 * Поддерживаемые методы: добавление / удаление человека,
 * добавление / удаление телефона для заданного человека,
 * поиск номера(ов) телефона по заданному имени человека,
 * поиск человека по заданному номеру телефона.
 *
 * Класс должен иметь конструктор по умолчанию (без параметров).
 */
class PhoneBook {
    private data class Human(val name: String) {
        init {
            if (!name.matches(Regex("""[A-zА-я]+ [A-zА-я]+""")))
                throw IllegalArgumentException("Invalid name format")
        }

        var listPhone = mutableSetOf<String>()
    }

    private var listHuman = mutableMapOf<String, Human>()

    private fun String.phoneTest() {
        if (!this.matches(Regex("""\+[\d\-\*#]+"""))) throw IllegalArgumentException("Invalid phone format")
    }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean {
        return if (name in listHuman) false
        else {
            listHuman[name] = Human(name)
            true
        }
    }

    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        return if (name in listHuman) {
            listHuman.remove(name)
            true
        } else false
    }

    /**
     * Добавить номер телефона.
     * Возвращает true, если номер был успешно добавлен,
     * и false, если человек с таким именем отсутствовал в телефонной книге,
     * либо у него уже был такой номер телефона,
     * либо такой номер телефона зарегистрирован за другим человеком.
     */
    fun addPhone(name: String, phone: String): Boolean {
        phone.phoneTest()
        if (name !in listHuman) return false
        for (i in listHuman.values) {
            if (phone in i.listPhone) return false
        }
        listHuman[name]!!.listPhone.add(phone)
        return true
    }

    /**
     * Убрать номер телефона.
     * Возвращает true, если номер был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * либо у него не было такого номера телефона.
     */
    fun removePhone(name: String, phone: String): Boolean {
        phone.phoneTest()
        if (name !in listHuman) return false
        else {
            if (phone !in listHuman[name]!!.listPhone) return false
            else listHuman[name]!!.listPhone.remove(phone)
        }
        return true
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> = listHuman[name]?.listPhone ?: setOf()

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        phone.phoneTest()
        for ((name, i) in listHuman) {
            if (phone in i.listPhone) return name
        }
        return null
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is PhoneBook) {
            if (listHuman.keys == other.listHuman.keys) {
                for ((key, name) in listHuman) {
                    if (name.listPhone != other.listHuman[key]!!.listPhone) return false
                }

            } else return false
        } else return false
        return true
    }

    override fun hashCode(): Int {
        return listHuman.hashCode()
    }


    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */

}