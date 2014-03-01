package chapter04

// listing 4.4

List myList = [1, 2, 3]

assert myList.size() == 3
assert myList[0]     == 1
assert myList instanceof ArrayList

List emptyList = []
assert emptyList.size() == 0

List longList = (0..1000).toList()
assert longList[555] == 555

List explicitList = new ArrayList()
explicitList.addAll(myList)
assert explicitList.size() == 3
explicitList[0] = 10
assert explicitList[0] == 10

explicitList = new LinkedList(myList)
assert explicitList.size() == 3
explicitList[0] = 10
assert explicitList[0] == 10

// listing 4.5

myList = ['a', 'b', 'c', 'd', 'e', 'f']
assert myList[0..2] == ['a', 'b', 'c']
assert myList[0,2,4] == ['a', 'c', 'e']
myList[0..2] = ['x', 'y', 'z']
assert myList == ['x', 'y', 'z', 'd', 'e' ,'f']

myList[3..5] = []
assert myList == ['x', 'y', 'z']

myList[1..1] = [0, 1, 2]
assert myList == ['x', 0, 1, 2, 'z']

// listing 4.6
myList = []

myList += 'a'
assert myList == ['a']

myList += ['b', 'c']
assert myList == ['a', 'b', 'c']

myList = []
myList << 'a' << 'b'
assert myList == ['a', 'b']

assert myList - ['b'] == ['a']

assert myList * 2 == ['a', 'b', 'a' ,'b']

// listing 4.7
myList = ['a', 'b', 'c']
assert myList.isCase('a')
assert 'b' in myList

def candidate = 'c'

switch (candidate) {
    case myList : assert true; break
    default     : assert false
}

assert ['x', 'a', 'z'].grep(myList) == ['a']

myList = []
if (myList) assert false

def expr = ''
for (i in [1,'*',5]) {
    expr += i
}
assert expr == '1*5'

// listing 4.8 - list manipulation
assert [1, [2, 3]].flatten() == [1, 2, 3]
assert [1, 2, 3].intersect([4, 3, 1]) == [3, 1]
assert [1, 2, 3].disjoint([4, 5, 6]) // intersection is empty

list = [1, 2, 3]
popped = list.pop()
assert popped == 3
assert list == [1, 2]

assert [1, 2].reverse() == [2, 1]
assert [3, 1, 2].sort() == [1, 2, 3]

def list = [[1, 0], [0, 1, 2]]
list = list.sort { a, b -> a[0] <=> b[0] }
assert list == [[0, 1 ,2], [1, 0]]

list = list.sort { it.size() }
assert list == [[1, 0], [0, 1, 2]]

list = ['a', 'b', 'c']
list.remove(2)
assert list == ['a', 'b']
list.remove('b')
assert list == ['a']
assert list - 'a' == []

list = ['a', 'b', 'b', 'c']
list.removeAll(['b', 'c'])
assert list == ['a']

def doubled = [1, 2, 3].collect { it * 2 } // collect returns new list
assert doubled == [2, 4, 6]

def odd = [1, 2, 3].findAll { it % 2 == 1}
assert odd == [1, 3]
odd = [1, 2, 3].findAll { (it & 1) == 1}
assert odd == [1, 3]

def x = [1, 1, 1]
assert [1] == new HashSet(x).toList()
assert [1] == x.unique()

x = [1, null, 1]
assert [1, 1] == x.findAll { it != null }
assert [1, 1] == x.grep { it }

// listing 4.9
list = [1, 2, 3]

assert list.first() == 1
assert list.head() == 1
assert list.tail() == [2, 3]
assert list.last() == 3
assert list.count(2) == 1
assert list.max() == 3
assert list.min() == 1

def even = list.find { (it & 1) == 0 }
assert even == 2

assert list.every { it < 5}
assert list.any { it < 2 }

def store = ''
list.each { store += it }
assert store == '123'

store = ''
list.reverseEach { store += it }
assert store == '321'

store = ''
list.eachWithIndex { item, index -> store += "$index:$item " }
assert store == '0:1 1:2 2:3 '

assert list.join('-') == '1-2-3'

result = list.inject(0) { clinks, guests -> clinks + guests }
assert result == 0 + 1 + 2 + 3
assert list.sum() == 6

factorial = list.inject(1) { factorial, item ->
    factorial * item
}
assert factorial == 1 * 1 * 2 * 3

// listing 4.10

def quickSort(list) {
    if (list.size() < 2) return list
    def pivot = list[list.size().intdiv(2)]
    def left = list.findAll { item -> item < pivot }
    def middle = list.findAll { item -> item == pivot }
    def right = list.findAll { item -> item > pivot }
    return quickSort(left) + middle + quickSort(right)
}

assert quickSort([]) == []
assert quickSort([1]) == [1]
assert quickSort([1, 2]) == [1, 2]
assert quickSort([2, 1]) == [1, 2]
assert quickSort([3, 1, 2]) == [1, 2, 3]
assert quickSort([3, 1, 2, 2]) == [1, 2, 2, 3]
assert quickSort([1.0f, 'a', 10, null]) == [null, 1.0f, 10, 'a']
assert quickSort('bca') == 'abc'.toList()