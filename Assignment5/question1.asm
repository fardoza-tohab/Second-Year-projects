# Fardoza Tohab --- 12/10/2021
# question1.asm-- A program that receives three separate lines of input, in the order 0-1-2, 
# and reorders them according to the following sequence: 2-0-1. 
# Registers used:
#           $t0 - used to hold the first number.
#           $t1 - used to hold the second number.
#           $t2 - used to hold the third number.


 .data
     #All string data
	 
     start: .asciiz "Enter a list of 3 lines:"
     end1: .asciiz "The reordered list is:"
     string1: .space 20
     string2: .space 20
     string3: .space 20
     
     newline: .asciiz "\n"

 .text
     main:


           # Printing start text
             li $v0, 4
             la $a0, start
             syscall


            #Printing newline
             li $v0, 4       
             la $a0, newline      
             syscall
			
			 

           ## Get first line from user, put into $t0.
           li $v0, 8       # load syscall string1 into $v0.
           la $a0, string1
           li $a1, 20
           syscall          # make the syscall.
           move $t0, $v0    # move the string read into $t0.
		   

           ## Get second line from user, put into $t1.
           li $v0, 8        # load syscall string2 into $v0.
           la $a0, string2
	   li $a1, 20
           syscall          # make the syscall.
           move $t1, $v0    # move the string read into $t1. 


           ## Get third line from user, put into $t2.
           li $v0, 8       # load syscall string3 into $v0.
           la $a0, string3
	   li $a1, 20
           syscall          # make the syscall.
           move $t2, $v0    # move the string read into $t2.
		   

            # Printing end text
             li $v0, 4
             la $a0, end1
             syscall

             
	    # Printing newline
             li $v0, 4       
             la $a0, newline      
             syscall
           
	#reordering		  
	   ##Print out the third string	  
	     la $a0, string3     #load and print string3
             li $v0, 4
             syscall
			  
			  
	  ##Print out the first string
              la $a0, string1   #load and print string1
              li $v0, 4
              syscall
              

          ##Print out the second string
              la $a0, string2    #load and print string2
              li $v0, 4
              syscall




          ## exit the program.
          li $v0, 10     # syscall code 10 is for exit.
          syscall        # make the syscall.

# end of question1.asm.







     
