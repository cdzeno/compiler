//moltiplicazioni
n = input "inserisci il massimo numero da considerare " 
i = 1
loop n - i + 1
	j = 1
	loop n - j + 1
		output i
		output " * " j 
		output " = " i * j 
		newLine
		j = j + 1
	endLoop
	i = i + 1 
endLoop
