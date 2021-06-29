# Marginal Tax Calculator

Calculate taxes due based on filing status and gross income

## Table of contents

* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Inspiration](#inspiration)

## General info
Discovered error caused by using IntelliJ IDEA compiler with jdk set to 14.
Changed language settings to 16 and no issues.

## Technologies

* Java <a href="https://jdk.java.net/java-se-ri/14">14</a> or <a href="https://jdk.java.net/15/">15</a>
* Maven Dependencies:
    * <a href="https://github.com/google/gson">Google Gson</a> version 2.8.6
    * <a href="https://junit.org/junit5/">JUnit 5</a> version 5.3.1

UPDATE: Known error with Google Gson
* As of Java 15, Gson throws an Exception (AssertionError) when trying to write to a record.
Replace record's with POJOs

## Setup

* Add .jar file to your projects library

## Inspiration

This project is based on classwork from a data structures course





