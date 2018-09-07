#!/bin/sh
for t in $(ls examples); do
	echo Compiling examples/$t/$t.mylang
	./compile.sh examples/$t/$t.mylang examples/$t/$t.o && \
	./exec.sh -L examples/$t/$t.o > examples/$t/$t.out
	[ -f examples/$t/$t.o ] || rm -f examples/$t/$t.out
done
