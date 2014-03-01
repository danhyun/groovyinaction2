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

assert myMap.get('d', 0) == 0
assert myMap.d == 0

myMap['d'] = 1
assert myMap.d == 1
myMap.d = 2
assert myMap.d == 2

myMap = ['a.b' : 1]
assert myMap.'a.b' == 1

// listing 4.13
myMap = [a:1, b:2, c:3]
def other = [b:2, c:3, a:1]

assert myMap == other // call to .equals()

assert myMap.size() == 3
assert myMap.containsKey('a')
assert myMap.containsValue(1)
assert myMap.entrySet() instanceof Collection

assert myMap.any { it.value > 2 }
assert myMap.every { it.key < 'd' }

assert myMap.keySet() == ['a', 'b', 'c'] as Set
assert myMap.values().toList() == [1, 2, 3]

// listing 4.14
myMap = [a:1 , b:2, c:3]

def store = ''
myMap.each {
    store += it.key
    store += it.value
}

assert store == 'a1b2c3'

store = ''
myMap.each { key, value ->
    store += key
    store += value
}

assert store == 'a1b2c3'

store = ''
for (key in myMap.keySet()) {
    store += key
}
assert store == 'abc'

store = ''
for (value in myMap.values()) {
    store += value
}
assert store == '123'

// listing 4.15
myMap = [a:1, b:2, c:3]
myMap.clear()
assert myMap.isEmpty()

myMap = [a:1, b:2, c:3]
myMap.remove('a')
assert myMap.size() == 2

assert [a:1] + [b:2] == [a:1, b:2]

myMap = [a:1, b:2, c:3]
def abMap = myMap.subMap(['a', 'b'])
assert abMap.size() == 2

abMap = myMap.findAll { it.value < 3 }
assert abMap.size() == 2
assert abMap.a == 1

def found = myMap.find { it.value < 2 }
assert found.key == 'a'
assert found.value == 1

def doubled = myMap.collect { it.value *= 2 }
assert doubled instanceof List
assert doubled == [2, 4, 6]
assert doubled.every { (it & 1) == 0}
assert doubled.every { it % 2 == 0}

def addTo = []
myMap.collect(addTo) { it.value *= 2 }
assert addTo instanceof List
assert addTo.every { (it & 1) == 0 }
assert addTo.every { it % 2 == 0 }

// listing 4.16
def textCorpus =
"""
Look for the bare necessities
THe simple bare necessities
Forgot about your worries and your strife
I mean the bare necessities
Old Mother Nature's recipes
That bring the bare necessities of life
"""

def words = textCorpus.tokenize()
def wordFrequency = [:]
words.each { wordFrequency[it] = wordFrequency.get(it, 0) + 1 }
def wordList = wordFrequency.keySet().toList()
wordList.sort { wordFrequency[it] }

def statistic = "\n"
wordList[-1..-5].each { word ->
    statistic += word.padLeft(12) + ': '
    statistic += wordFrequency[word] + "\n"
}

assert statistic == """
 necessities: 4
        bare: 4
         the: 3
        your: 2
        life: 1
"""