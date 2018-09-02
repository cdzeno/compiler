//massimo comun divisore
x = input "Primo numero? "
y = input "Secondo numero? "
loop y
    resto = x % y 
    x = y
    y = resto
endLoop
output "mcd = " x
newLine
