package chapter06

// listing 6.10 - Simple break and continue
def a = 1
while (true) {
    a++
    break
}
assert a == 2

for (i in 0..10) {
    if (i == 0) continue
    a++
    if ( i > 0) break
}
assert a == 3

// listing 6.11 - Throw, try, catch, and finally
def myMethod() {
    throw new IllegalArgumentException()
}

def log = []
try {
    myMethod()
} catch (Exception e) {
    log << e.toString()
} finally {
    log << 'finally'
}
assert log.size() == 2