package chapter04

// listing 4.11
def myMap = [a:1, b:2, c:3]

assert myMap instanceof LinkedHashMap
assert myMap.size() == 3
assert myMap['a'] == 1

def emptyMap = [:]
assert emptyMap.size() == 0

def explicitMap = new TreeMap()
explicitMap.putAll(myMap)
assert explicitMap['a'] == 1

def composed = [x:'y', *:myMap]
assert composed == [x:'y', a:1, b:2, c:3]

def x = 'a'
assert ['x':1] == [x:1]
assert ['a':1] == [(x):1]

// listing 4.12

myMap = [a:1, b:2, c:3]

assert myMap['a'] == 1
assert myMap.a == 1
assert myMap.get('a') == 1
assert myMap.get('a', 0) == 1

assert myMap['d'] == null
assert myMap.d == null
assert myMap.get('d') == null

assert myMap.get('d', 0) == null
assert myMap.d == 0

myMap['d'] = 1
assert myMap.d == 1
myMap.d = 2
assert myMap.d == 2

myMap = ['a.b' : 1]
assert myMap.'a.b' == 1