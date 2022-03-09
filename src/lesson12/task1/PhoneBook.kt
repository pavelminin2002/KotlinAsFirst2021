@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1

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
            if (!name.matches(Regex("""[A-zА-яЁё]+ [A-zА-яЁё]+""")))
                throw IllegalArgumentException("Invalid name format")
        }

        var listPhone = mutableSetOf<String>()
    }

    private var mapHuman = mutableMapOf<String, Human>()

    private fun String.phoneTest() {
        if (!this.matches(Regex("""\+[\d\-*#]+"""))) throw IllegalArgumentException("Invalid phone format")
    }

    /**
     * Добавить человека.
     * Возвращает true, если человек был успешно добавлен,
     * и false, если человек с таким именем уже был в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun addHuman(name: String): Boolean =
        if (name in mapHuman) false
        else {
            mapHuman[name] = Human(name)
            true
        }


    /**
     * Убрать человека.
     * Возвращает true, если человек был успешно удалён,
     * и false, если человек с таким именем отсутствовал в телефонной книге
     * (во втором случае телефонная книга не должна меняться).
     */
    fun removeHuman(name: String): Boolean {
        return if (name in mapHuman) {
            mapHuman.remove(name)
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
        if (name !in mapHuman) return false
        for (human in mapHuman.values) {
            if (phone in human.listPhone) return false
        }
        mapHuman[name]!!.listPhone.add(phone)
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
        if (name !in mapHuman) return false
        else {
            if (phone !in mapHuman[name]!!.listPhone) return false
            else mapHuman[name]!!.listPhone.remove(phone)
        }
        return true
    }

    /**
     * Вернуть все номера телефона заданного человека.
     * Если этого человека нет в книге, вернуть пустой список
     */
    fun phones(name: String): Set<String> = mapHuman[name]?.listPhone ?: setOf()

    /**
     * Вернуть имя человека по заданному номеру телефона.
     * Если такого номера нет в книге, вернуть null.
     */
    fun humanByPhone(phone: String): String? {
        phone.phoneTest()
        for ((name, human) in mapHuman) {
            if (phone in human.listPhone) return name
        }
        return null
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is PhoneBook) return false
        if (mapHuman.keys != other.mapHuman.keys) return false
        return mapHuman.entries.all { it.value.listPhone == other.mapHuman[it.key]!!.listPhone }
    }

    override fun hashCode(): Int = mapHuman.hashCode()


    /**
     * Две телефонные книги равны, если в них хранится одинаковый набор людей,
     * и каждому человеку соответствует одинаковый набор телефонов.
     * Порядок людей / порядок телефонов в книге не должен иметь значения.
     */

}