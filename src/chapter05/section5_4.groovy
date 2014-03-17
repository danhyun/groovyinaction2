package chapter05

// listing 5.5 calling closures
def adder = { x, y -> return x + y }
assert adder(4, 3) == 7
assert adder.call(2, 6) == 8

// listing 5.6 calling closures
def benchmark (int repeat, Closure worker) {
    def start = System.nanoTime()
    repeat.times { worker it }
    def stop = System.nanoTime()

    return stop - start
}

def slow = benchmark(10000) { (int) it / 2 }
def fast = benchmark(10000) { it.intdiv(2) }

println "Fast is ${slow/fast} times faster than slow"

assert fast * 4 < slow
println "Fast: $fast"
println "Slow: $slow"

// default values
adder = { x, y = 5 -> return x + y }
assert adder(4, 3) == 7
assert adder.call(7) == 12

// section 5.4.2 reacting to param count
def caller (Closure closure) {
    closure.getParameterTypes().size()
}

assert caller { one -> } == 1
assert caller { one, two -> } == 2

// listing 5.7 currying example (currying is fixing some params)
// curried params bind left to right in args listing
adder = { x, y -> return x + y }
def addOne = adder.curry(1)
assert addOne(5) == 6

// listing 5.8 more elaborate currying
def configurator = { format, filter, line ->
    filter(line) ? format(line) : null
}

def appender = { config, append, line ->
    def out = config(line)
    if (out) append(out)
}

def dateFormatter = { line -> "${new Date()}: $line" }
def debugFilter = { line -> line.contains('debug') }
def consoleAppender = { line -> println line }

def myConf = configurator.curry(dateFormatter, debugFilter)
def myLog = appender.curry(myConf, consoleAppender)

myLog('here is some debug message')
myLog('this will not be printed')

// classification via isCase method
assert [1, 2, 3].grep { it < 3 } == [1, 2]

switch(10) {
    case { it % 2 == 1 } : assert false
}

// closures need to support clone method