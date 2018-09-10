# Compiler project - Languages and Compilers

Final project for the Languages and Compilers course @ University of Milan (A.Y. 2017/2018).

Made by [Gabriele Quagliarella](https://www.github.com/cdzeno) and [Emmanuel Esposito](https://www.github.com/emmanuelespo).

## Setup
The compiler is written in Java and is based on [JFlex](http://jflex.de/) and [CUP](http://www2.cs.tum.edu/projects/cup/), respectively scanner and parser generators.

The JFlex installation is required and can be easily done by issuing the following command (on Debian-based distros):
```bash
sudo apt install jflex
```

JAR libraries for CUP and the [stack machine](http://pighizzini.di.unimi.it/linguaggi/?a=gc2018) assembler are already present inside the directory lib/ of the project.

## Build
Building the compiler is straightforward and can be accomplished using Gradle by issuing the following command:
```bash
./gradlew build
```

## Running
To compile a source file, you can run the script `compile.sh` with the filename as the first argument and the object filename as second argument.
The object file can be executed with the stack machine interpreter by running the script `exec.sh`, with its name as an argument (it also accepts debugging options).

## Example
For example, if you want to compile the source code `examples/test_8.mylang` type:
```bash
./compile.sh examples/test_8.mylang test_8
```
If there aren't any syntax errors, the compiler produces the object file `test_8` that can be run issuing:
```bash
./exec.sh test_8
```
