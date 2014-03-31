package chapter05

def x = 0
10.times {
    x++
}
assert x == 10

// listing 5.9 Investigating closure scope
class Mother {
    int prop = 1
    int method() { return 2 }
    Closure birth(param) {
        def local = 3
        def closure = { caller ->
            [this, prop, method(), local, param, caller, owner]
        }
        return closure
    }
}

Mother julia = new Mother()

closure = julia.birth(4)

context = closure.call(this)
println context[0].class.name

assert context[1..4] == [1, 2, 3, 4]
assert context[5] instanceof Script
assert context[6] instanceof Mother

firstClosure = julia.birth(4)
secondClosure = julia.birth(4)
assert !firstClosure.is(secondClosure) // closure braces create new references

/**
 * http://www.paulgraham.com/icad.html
    We want to write a function that generates accumulators—a function that
    takes a number  n , and returns a function that takes another number  i and
    returns  n incremented by  i
 **/
// listing 5.10 Accumulator problem in groovy
def foo(n) {
    return { n += it }
}

def accumulator = foo(1)
assert accumulator(2) == 3
assert accumulator(1) == 4