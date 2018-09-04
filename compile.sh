#!/bin/sh
make compiler
java -cp "src/:bin/:lib/java-cup-11b-runtime.jar:lib/ltMacchina.jar" lt.compiler.Compiler $1
