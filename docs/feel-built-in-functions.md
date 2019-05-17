---
title: FEEL Language Reference: Built-in Functions
---

# Built-in Functions

The following functions are available and can be used in expressions and unary-tests.

Note that some function/parameter names contain whitespaces. However, they can be invoked directly - without escaping the name.  

## Conversion Functions 

Convert a value into a different type.

### date()

* parameters:
  * `from`: string / date-time
  * or `year`, `month`, `day`: number 
* result: date

```js
date(birthday) 
// date("2018-04-29")

date(date and time("2012-12-25T11:00:00"))
// date("2012-12-25")

date(2012, 12, 25)
// date("2012-12-25")
```

### time()

* parameters:
  * `from`: string / date-time
  * or `hour`, `minute`, `second`: number 
    * (optional) `offset`: day-time-duration
* result: time

```js
time(lunchTime) 
// time("12:00:00")

time(date and time("2012-12-25T11:00:00"))
// time("11:00:00")

time(23, 59, 0)
// time("23:59:00")

time(14, 30, 0, duration("PT1H"))
// time("15:30:00")
```

### date and time()

* parameters:
  * `date`: date / date-time
  * `time`: time
  * or `from`: string 
* result: date-time

```js
date and time(date("2012-12-24"),time("T23:59:00")) 
// date and time("2012-12-24T23:59:00")

date and time(date and time("2012-12-25T11:00:00"),time("T23:59:00"))
// date and time("2012-12-25T23:59:00")

date and time(birthday) 
// date and time("2018-04-29T009:30:00")
```

### duration()

* parameters:
  * `from`: string
* result: day-time-duration or year-month-duration

```js
duration(weekDays)
// duration("P5D")

duration(age)
// duration("P32Y")
```

### years and months duration()

* parameters:
  * `from`: date
  * `to`: date
* result: year-month-duration

```js
years and months duration(date("2011-12-22"), date("2013-08-24"))
// duration("P1Y8M")
```

### number()

* parameters:
  * `from`: string
* result: number

```js
number("1500.5") 
// 1500.5
```

### string()

* parameters:
  * `from`: any
* result: string

```js
string(1.1) 
// "1.1"

string(date("2012-12-25"))
// "2012-12-25"
```

## Boolean Functions 

### not()

* parameters:
  * `negand`: boolean
* result: boolean

```js
not(true)
// false
```

## String Functions 

### substring()

* parameters:
  * `string`: string
  * `start position`: number
  * (optional) `length`: number  
* result: string

```js
substring("foobar",3) 
// "obar"

substring("foobar",3,3) 
// "oba"
```

### string length()

* parameters:
  * `string`: string
* result: number

```js
string length("foo") 
// 3
```

### upper case()

* parameters:
  * `string`: string
* result: string

```js
upper case("aBc4") 
// "ABC4"
```

### lower case()

* parameters:
  * `string`: string
* result: string

```js
lower case("aBc4") 
// "abc4"
```

### substring before()

* parameters:
  * `string`: string
  * `match`: string
* result: string

```js
substring before("foobar", "bar") 
// "foo"
```

### substring after()

* parameters:
  * `string`: string
  * `match`: string
* result: string

```js
substring after("foobar", "ob") 
// "ar"
```

### contains()

* parameters:
  * `string`: string
  * `match`: string
* result: boolean

```js
contains("foobar", "of") 
// false
```

### starts with()

* parameters:
  * `input`: string
  * `match`: string
* result: boolean

```js
starts with("foobar", "fo") 
// true
```

### ends with()

* parameters:
  * `input`: string
  * `match`: string
* result: boolean

```js
ends with("foobar", "r") 
// true
```

### matches()

* parameters:
  * `input`: string
  * `pattern`: string (regular expression)
* result: boolean

```js
matches("foobar", "^fo*bar") 
// true
```

### replace()

* parameters:
  * `input`: string
  * `pattern`: string (regular expression)
  * `replacement`: string (e.g. `$1` returns the first match group) 
  * (optional) `flags`: string ("s", "m", "i", "x")
* result: string

```js
replace("abcd", "(ab)|(a)", "[1=$1][2=$2]")
// "[1=ab][2=]cd"

replace("0123456789", "(\d{3})(\d{3})(\d{4})", "($1) $2-$3")
// "(012) 345-6789"
```

### split()

* parameters:
  * `string`: string
  * `delimiter`: string (regular expression)
* result: list of strings

```js
split("John Doe", "\s" ) 
// ["John", "Doe"]

split("a;b;c;;", ";")
// ["a", "b", "c", "", ""]
```

## List Functions 

| function | parameter | description | example |
|----------|-----------|-------------|---------|
| list contains(list, element) | list, _any_ | check if list contain the _element_ | list contains([1,2,3], 2) = true |
| count(list)| list | size of list | count([1,2,3]) = 3 |
| min(list) | list of numbers | return the minimum | min([1,2,3]) = 1, min(1,2,3) = 1 |
| max(list) | list of numbers | return the maximum | max([1,2,3]) = 3, max(1,2,3) = 3 |
| sum(list) | list of numbers | return the sum | sum([1,2,3]) = 6, sum(1,2,3) = 6 |
| product(list) | list of numbers | return the product | product(2, 3, 4) = 24 |
| mean(list) | list of numbers | return arithmetic mean (average) | mean([1,2,3]) = 2, mean(1,2,3) = 2 |
| median(list) | list of numbers | returns the median element of the list of numbers | median(8, 2, 5, 3, 4) = 4, median([6, 1, 2, 3]) = 2.5 |
| stddev(list) | list of numbers | returns the standard deviation | stddev(2, 4, 7, 5) = 2.0816659994661326 |
| mode(list) | list of numbers | return mode of the list of numbers | mode(6, 3, 9, 6, 6) = [6], mode([6, 1, 9, 6, 1]) = [1, 6] |
| and(list) / all(list) | list of boolean | boolean conjunction | and([true,false]) = false, and(false,null,true) = false |
| or(list) / any(list) | list of boolean | boolean disjunction | or([false,true]) = true, or(false,null,true) = true |
| sublist(list, start position) | list, number | list starting at _position_ | sublist([1,2,3], 2) = [2,3] |
| sublist(list, start position, length) | list, number, number | list with _length_ starting at _postion_ | sublist([1,2,3], 1, 2) = [1,2] |
| append(list, items) | list, _any_ | return new list with item | append([1], 2, 3) = [1,2,3] |
| concatenate(lists) | list of lists | return new list of both lists | concatenate([1,2],[3]) = [1,2,3] |
| insert before(list, position, newItem) | list, number, _any_ | return new list with _newItem_ inserted at position | insert before([1,3],1,2) = [1,2,3] |
| remove(list, position) | list, number | list without item | remove([1,2,3], 2) = [1,3] |
| reverse(list) | list | reverse the list | reverse([1,2,3]) = [3,2,1] |
| index of(list, match) | list, _any_ | return ascending list of positions containing _match_ | index of([1,2,3,2],2) = [2,4] |
| union(lists) | list of lists | concatenate with duplicate removal | union([1,2],[2,3]) = [1,2,3] |
| distinct values(list) | list | duplicate removal list | distinct values([1,2,3,2,1]) = [1,2,3] |
| flatten(list) | list | flatten nested lists | `flatten([[1,2],[[3]], 4]) = [1,2,3,4]` |
| sort(list, precedes) | list, function => boolean | return the list sorted by given function | sort(list: [3,1,4,5,2], precedes: function(x,y) x < y) = [1,2,3,4,5] |

## Numeric Functions 

| function | parameter | description | example |
|----------|-----------|-------------|---------|
| decimal(n, scale) | number, number | return n with given scale | decimal(1/3, 2) = .33, decimal(1.5, 0) = 2 |
| floor(n) | number | return greatest integer <= n | floor(1.5) = 1, floor(-1.5) = -2 | 
| ceiling(n) | number | return smallest integer >= n | ceiling(1.5) = 2, ceiling(-1.5) = -1 |
| abs(number) | number | return the absolute value | abs(10) = 10 , abs(-10) = 10 |
| modulo(dividend, divisor) | number, number | return the remainder of the division of dividend by divisor | modulo( 12, 5 ) = 2 |
| sqrt(number) | number | return the square root | sqrt(16) = 4 |
| log(number) | number | return the natural logarithm (base e) of the number | log(10) = 2.302585092994046 |
| exp(number) | number | return the Euler’s number e raised to the power of number | exp( 5 ) = 148.4131591025766 |
| odd(number) | number | check if the number is odd | odd(5) = true |
| even(number) | number | check if the number is even | even(5) = false |

## Context Functions 

| function | parameter | description | example |
|----------|-----------|-------------|---------|
| get value(context, key) | context, string | return the value of the context entry with the given key | get value({foo: 123}, "foo") = 123 |
| get entries(context) | context | return the entries of the context as list of key-value-pairs | get entries({foo: 123}) = [ { key: "foo", value: 123 } ] | 