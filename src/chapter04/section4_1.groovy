package chapter04

// listing 4.1

assert (0..10).contains(0)
assert (0..10).contains(5)
assert (0..10).contains(10)

assert (0..10).contains(-1) == false
assert (0..10).contains(11) == false

assert (0..<10).contains(9)
assert (0..<10).contains(10) == false

def a = 0..10
assert a instanceof Range
assert a.contains(5)

a = new IntRange(0, 10)
assert a.contains(5)

assert (0.0..1.0).contains(1.0)
assert (0.0..1.0).containsWithinBounds(0.5)

def today = new Date()
def yesterday = today - 1
assert (yesterday..today).size() == 2

assert ('a'..'c').contains('b')

def log = ''
for (element in 5..9) {
    log += element
}

assert log == '56789'

log = ''
for (element in 9..5) {
    log += element
}
assert log == '98765'

log = ''
(9..<5).each { e -> log += e }
assert log == '9876'

// listing 4.2
def result = ''
(5..9).each { result += it }
assert result == '56789'

assert 5 in 0..10
assert (0..10).isCase(5)

def age = 36
switch(age) {
    case 16..20 : insuranceRate = 0.05; break
    case 21..50 : insuranceRate = 0.06; break
    case 51..65 : insuranceRate = 0.07; break
    default     : throw new IllegalArgumentException()
}
assert insuranceRate == 0.06

def ages = [20, 36, 42, 56]
def midage = 21..50
assert ages.grep(midage) == [36, 42]

// to use ranges implement `next` (++), `previous` (--_
// and implement `Comparable.compareTo` (<=>)

class Weekday implements Comparable {

    static final DAYS = ['Sun', 'Mon', 'Tues', 'Wed', 'Thurs', 'Fri', 'Sat']

    private int index = 0

    Weekday(String day) {
        index = DAYS.indexOf(day)
    }

    Weekday next() {
        return new Weekday(DAYS[(index + 1) % DAYS.size()])
    }

    Weekday previous() {
        return new Weekday(DAYS[index - 1])
    }

    int compareTo(Object o) {
        return this.index <=> o.index
    }

    String toString() {
        return DAYS[index]
    }
}

def mon = new Weekday('Mon')
def fri = new Weekday('Fri')

def worklog = ''
for (day in mon..fri) {
    worklog += "$day "
}
assert worklog == 'Mon Tues Wed Thurs Fri '