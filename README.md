# gaussian-elimination
Command line program made in Java that uses Gaussian elimination to solve systems of linear equations. Originally a project from my numerical methods class

## Features
Given a .lin file with a system of linear equations, will output a .sol file with the solutions of that system


## How to use
Takes a text file with extension .lin formatted as follows:

number of variables/equations

coefficients of equation 1

coefficients of equation 2

.

.

.

coefficients of equation n

all constants on the right hand side of the equation


Program runs naive gaussian elimination by default, but can run scaled partial pivoting with command line argument -spp
