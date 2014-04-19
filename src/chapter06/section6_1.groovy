package chapter06

// groovy truth

// ${type}              |   ${evaluate to true}
// boolean              |   true
// Matcher              |   has match
// Collection           |   nonempty
// Map                  |   nonempty
// String, GString      |   nonempty
// Number, Character    |   nonzero
// Everything else      |   non-null

// listing 6.1 Example boolean evaluations

assert true
assert !false

assert ('a' =~ /./)
assert !('a' =~ /b/)

assert [1]
assert ![]

Iterator iter = [1].iterator()
assert iter
iter.next()
assert !iter

assert ['a': 1]
assert ![:]

assert 'a'
assert !''

assert 1
assert 1.1
assert 1.2f
assert 1.3g
assert 2L
assert 3G
assert !0

assert !null
assert new Object()

class AlwaysFalse {
    boolean asBoolean() { false }
}

assert ! new AlwaysFalse()

// listing 6.2

def x = 1

if (x == 2) {
    assert false
}

/***************
// compilation exception
if (x = 2) {
    println x
}
***************/

if ((x = 3)) {
    println x
}

assert x == 3

def store = []
while (x = x - 1) {
    store << x
}

assert store == [2, 1]

while (x = 2) {
    println x
    break
}