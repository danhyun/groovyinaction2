package chapter05

// returning from closures
// last line is implicit return

[1, 2, 3].collect { it * 2 }
[1, 2, 3].collect { return it * 2 }

assert [1, 2, 3].collect { it * 2 } == [1, 2, 3].collect { return it * 2 }

// can choose to return early
[1, 2, 3].collect {
    if (it % 2 == 0) return it * 2
    return it
}