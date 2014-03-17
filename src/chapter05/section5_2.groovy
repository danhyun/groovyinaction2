package chapter05

// listing 5.1
log = ''
(1..10).each { counter -> log += counter }
assert log == '12345678910'

log = ''
(1..10).each { log += it }
assert log == '12345678910' // note 'it' is implicitly defined