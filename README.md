# xkcd-936 [WIP]

[![xkcd-936](http://imgs.xkcd.com/comics/password_strength.png)](https://xkcd.com/936/)

Implementing 👆in Clojure

## Installation

1. Clone this Repo
2. cd `xkdd-936`

## Usage

    $ lein run

or

    $ java -jar xkcd-936-0.1.0-standalone.jar [args]

## Options

Use `-h` to list all Options

* `-c` Count - Number of words per suggestion. Default - `4`
* `-d` Delimiter - String delimiter between words. Default - `\space`

## What's working?

Generates XKCD Style passwords.

You can configure word count, add delimiters as of now.

## To-Do

- [ ] Use arguments to give different outputs like min/max word length, number of suggestions etc...
- [ ] acrostic outputs
- [ ] calculate and show entropy of the passwords

## The Unlicense

2017 Christopher
