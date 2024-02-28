Lab1

My code is structured in three methods name : compulsory, homework, bonus , main and one helper methode
defined to help me copute the number of cycles.

#Compulsory
In the compulsory method i generate a random number and do the afferent computations
to it . After that I get the sum of it's digits and put it in the result
variable . As long as result variable has more than one digit
I compute it's sum of digits .

#Homework
I pass the arguments received from the command line to the method homework
then i store the current time in a long variable and verify if there are
enough command line arguments . I transform them from strings to integers
and I iterate through the interval [a,b] and verify if they are k-reductible
and store the in a string and print the string to the screen.
At the end i store the current time again in another variable and substract
the initial stored time from it .

#Bonus
I receive a number by command line arguments from main passed to bonus method
and i build the matrix , by knowing one node is connected to all the other
nodes and the rest of the nodes are connected two by two , because
it is a cycles
After that i compute the correct number of cycles given by the formula and 
then i use another method called findCycles and iterate through all the nodes
and find the cycles from them and then add them to the total number of cycles
