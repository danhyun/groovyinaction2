package chapter05

def printer = { line -> println line }
// closure literal { } == new Closure() {}
def Closure getPrinter() {
    return { lin -> println line }
}

// you can reuse methods by "closurizing" it via & dereferencer

// listing 5.2 simple method closures in action
class SizeFilter {
    Integer limit

    boolean sizeUpTp(String value) {
        return value.size() <= limit
    }
}

SizeFilter filter6 = new SizeFilter(limit: 6)
SizeFilter filter5 = new SizeFilter(limit: 5)

Closure sizeUpTo6 = filter6.&sizeUpTp

def words = ['long string', 'medium', 'short', 'tiny']

assert 'medium' == words.find ( sizeUpTo6 )
assert 'medium' == words.find ( sizeUpTo6 )
assert 'short' == words.find ( filter5.&sizeUpTp )

// listing 5.3 multimethod closures -
//      the same method name called with different parameters is used
//      to call different implementations

class MultiMethodSample {

    int mysteryMethod (String value) {
        return value.length()
    }

    int mysteryMethod (List list) {
        return list.size()
    }

    int mysteryMethod (int x, int y) {
        return x + y
    }
}

MultiMethodSample instance = new MultiMethodSample()
Closure multi = instance.&mysteryMethod

// woah!
assert 10 == multi ('string arg')
assert 3  == multi (['list', 'of', 'args'])
assert 14 == multi (6, 8)



// listing 5.4 full closure declaration examples

Map map = [a: 1, b: 2]
map.each { key, value -> map[key] = value * 2 }
assert map == [a: 2, b: 4]

Closure doubler = { key, value -> map[key] = value * 2 }
map.each doubler
assert map == [a: 4, b: 8]

def doubleMethod (entry) {
    entry.value = entry.value * 2
}

doubler = this.&doubleMethod
map.each doubler
assert map == [a: 8, b: 16]