package chapter03

import static java.util.Calendar.*

def me = 'Tarzan'
def you = 'Jane'
def line = "me $me - you $you"
assert line == 'me Tarzan - you Jane'

def date = new Date(0)
def dateMap = [y:date[YEAR]-1900, m:date[MONTH], d:date[DAY_OF_MONTH]]
def out = "Year $dateMap.y Month $dateMap.m Day $dateMap.d"
assert out == 'Year 69 Month 11 Day 31'

def tz = TimeZone.getTimeZone('GMT')
def format = 'd MMM YYYY HH:mm:SS z'
out = "Date is ${date.format(format, tz)} !"
assert out == 'Date is 1 Jan 1970 00:00:00 GMT !'

def sql = """
SELECT FROM MyTable
    WHERE Year = $dateMap.y
"""
assert sql == """
SELECT FROM MyTable
    WHERE Year = 69
"""

out = "my 0.02\$"
assert out == 'my 0.02$'

// listing 3.5

String greeting = 'Hello Groovy!'
assert greeting.startsWith('Hello')

assert greeting.getAt(0) == 'H'
assert greeting[0] == 'H'

assert greeting.indexOf('Groovy') >= 0
assert greeting.contains('Groovy')

assert greeting[6..11] == 'Groovy'
assert 'Hi' + greeting - 'Hello' == 'Hi Groovy!'

assert greeting.count('o') == 3

assert 'x'.padLeft(3) == '  x'
assert 'x'.padRight(3) == 'x  '
assert 'x'.center(3) == ' x '
assert 'x' * 3 == 'xxx'

def greeting2 = 'Hello'
greeting2 <<= ' Groovy'

assert greeting2 instanceof java.lang.StringBuffer

greeting2 << '!'

assert greeting2.toString() == 'Hello Groovy!'
greeting2[1..4] = 'i'

assert greeting2.toString() == 'Hi Groovy!'