package chapter06

// listing 6.3 - if statements
if (true)       assert true
else            assert false
if (1) {
    assert true
} else {
    assert false
}

if ('nonempty') assert true
else if (['x']) assert false
else            assert false

if (0)          assert false
else if ([])    assert false
else            assert true

// listing 6.4 - conditional operator
def result = (1 == 1) ? 'ok' : 'failed'
assert result == 'ok'
result = 'some string' ? 10 : ['x']
assert result == 10

// listing 6.5 - general `switch`
def a = 1
def log = ''
switch (a) {
    case 0 : log += '0'
    case 1 : log += '1'
    case 2 : log += '2'; break
    default: log += 'default'
}

assert log == '12'

/**
 * classifiers implement `isCase()` to be usable in switches
switch (candidate) {
    case classifier1 : handle1() ; break
    case classifier2 : handle2() ; break
    default : handleDefault()
}
*/

// listing 6.6 - advanced switch and mixed classifiers
switch (10) {
    case 0              :   assert false; break
    case 0..9           :   assert false; break // in range
    case [8,9,11]       :   assert false; break // in list
    case Float          :   assert false; break // is type
    case {it % 3 == 0}  :   assert false; break
    case ~/../          :   assert true; break
    default             :   assert false; break
}

// table 6.2 impl of `isCase` for `switch`

//      class           |           a.isCase(b)
//      ===================================================================
//      Object          |           a.equals(b)
//      Class           |           a.isInstance(b)
//      Collection      |           a.contains(b)
//      Range           |           a.contains(b)
//      Pattern         |           a.matcher(b.toString()).matches()
//      String          |           (a == null && b == null) || a.equals(b)
//      Closure         |           a.call(b)

// listing 6.7 - use assertions for inline unit tests
def host = /\/\/([a-zA-Z0-9-]+(\.[a-zA-Z0-9-])*?)(:|\/)/

assertHost 'http://a.b.c:8080/bla', host, 'a.b.c'
assertHost 'http://a.b.c/bla', host, 'a.b.c'
assertHost 'http://127.0.0.1:8080/bla', host, '127.0.0.1'
assertHost 'http://t-online.de/bla', host, 't-online.de'
assertHost 'http://T-online.de/bla', host, 'T-online.de'

def assertHost (candidate, regex, expected) {
    candidate.eachMatch(regex) { assert it[1] == expected }
}