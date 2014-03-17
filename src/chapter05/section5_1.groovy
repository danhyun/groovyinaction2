package chapter05

class Person {
    String name
}

class Letter {

    Person person

    void send() {
        println "Sending letter to ${person.name}"
    }
}

Closure envelope = { person -> new Letter(person: person).send() }

def addressBook = [new Person(name: "Alice"), new Person(name: "Bob"), new Person(name: "Clara")]

addressBook.each(envelope)
addressBook.each { new Letter(person: it).send() }
