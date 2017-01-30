# xkcd-936 [WIP]

[![xkcd-936](http://imgs.xkcd.com/comics/password_strength.png)](https://xkcd.com/936/)

Implementing 👆in Clojure

## Installation

1. Clone this Repo
2. cd `xkcd-936`

## Usage

    $ lein run [args]

or
    $ lein uberjar
    $ java -jar xkcd-936-0.1.0-standalone.jar [args]

## Options

* `-c` `--count` Count - Number of words per suggestion. Default - `4`
* `-d` `--delimiter` Delimiter - String delimiter between words. Default - `\space`
* `-s` `--suggestions` Number of Suggestions. Default - `1`
* `-l` `--lte` Minimum number of Characters in a word. Default - `4`
* `-g` `--gte` Maximum number of Characters in a word. Default - `7`
* `-h` `--help`

## What's working?

Generates XKCD Style passwords.

You can configure word count, add delimiters, specify minumum/maximum characters per word and the number of suggestions.

## To-Do

- [x] Use arguments to give different outputs like min/max word length, number of suggestions etc...
- [ ] acrostic outputs
- [ ] calculate and show entropy of the passwords

## The Unlicense

2017 Christopher
