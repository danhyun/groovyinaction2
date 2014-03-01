package chapter03

import java.math.MathContext
import java.text.DecimalFormat

// regex stuff
// find =~
// match ==~
// pattern ~string

assert "abc" == /abc/
assert "\\d" == /\d/

def reference = "hello"

assert reference == /$reference/

def twister = 'she sells sea shells at the sea shore of seychelles'
assert twister =~ /s.a/

def finder = (twister =~ /s.a/)
assert finder instanceof java.util.regex.Matcher

assert twister ==~ /(\w+ \w+)*/

def WORD = /\w+/
matches = (twister ==~ /($WORD $WORD)*/)
assert matches instanceof java.lang.Boolean
assert !(twister ==~ /s.e/)

def wordsByX = twister.replaceAll(WORD, 'x')
assert wordsByX == 'x x x x x x x x x x'

def words = twister.split(/ /)
assert words.size() == 10
assert words[0] == 'she'

// listing 3.8

def myFairStringy = 'The rain in Spain stays mainly in the plain!'

// words that end with 'ain': \b\w*ain\b
def wordEnding = /\w*ain/
def rhyme = /\b$wordEnding\b/

def found = ''
myFairStringy.eachMatch(rhyme) { match ->
    found <<= "$match "
}

assert found.toString() == 'rain Spain plain '

def cloze = myFairStringy.replaceAll(rhyme) { it-'ain'+'___'}
assert cloze == 'The r___ in Sp___ stays mainly in the pl___!'

def matcher = 'a b c' =~ /\S/
assert matcher[0] == 'a'
assert matcher[1..2] == ['b', 'c']
assert matcher.size() == 3

def KEY_VALUE = /(\S+):(\S+)/
def mapInString = 'a:1 b:2 c:3'

def matcher2 = (mapInString =~ /$KEY_VALUE/)
assert matcher2.hasGroup()
assert matcher2[0] == ['a:1', 'a', '1'] // 1st match
assert matcher2[1][2] == '2'

def matcher3 = (mapInString =~ /$KEY_VALUE/)
matcher3.each { full, key, value ->
    assert full.size() == 3
    assert key.size() == 1 // a,b,c
    assert value.size() == 1 // 1,2,3
}

// regex performance
// patterns constructed using tilde (~) are backed by FSM and is highly performant
// takes longer to construct but faster to match

def tongueTwister = 'she sells sea shells at the sea shore of seychelles'
def regex = /b(\w)\w*\1\b/
def many = 100 * 1000

start = System.nanoTime()
many.times {
    tongueTwister =~ regex
}
timeImplicit = System.nanoTime() - start

start = System.nanoTime()
pattern = ~regex
many.times {
    pattern.matcher(tongueTwister)
}
timePredef = System.nanoTime() - start

println "Find: $timeImplicit\tPattern: $timePredef"
println "Pattern is ${((timeImplicit / timePredef) as Double).round(3)} times faster"
assert timeImplicit > timePredef * 2

// listing 3.10

def fourLetters = ~/\w{4}/

assert fourLetters.isCase('work')

assert 'love' in fourLetters

switch ('beer') {
    case fourLetters: assert true; break
    default         : assert false
}

beasts = ['bear', 'wolf', 'tiger', 'regex']
assert beasts.grep(fourLetters) == ['bear', 'wolf']