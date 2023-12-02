package com.ai.aston_intensive_3.data

import com.ai.aston_intensive_3.model.Contact

class Datasource {

    fun loadContacts(): MutableList<Contact> {
        var contacts = mutableListOf<Contact>()
        fillContacts(contacts)
        return contacts
    }

    private fun fillContacts(contacts: MutableList<Contact>) {
        val names = listOf("Иван", "Петр", "Александр", "Дмитрий", "Юлия","Станислав")
        val surnames = listOf("Романченко", "Дымов","Гроздев","Малыковцев", "Хорошая", "Бондарев")
        val phones = listOf(
            "+7 (999) 123-45-67",
            "+7 (888) 765-43-21",
            "+7 (666) 543-21-09",
            "+7 (555) 321-09-87",
            "+7 (444) 210-98-76",
            "+7 (375) 321-09-87"
        )
        for (i in 0 until 101) {
            val name = names[i % names.size]
            val surname = surnames[i % surnames.size]
            val phone = phones[i % phones.size]

            val contact = Contact(i, name, surname, phone)
            contacts.add(contact)
        }
    }
}