# Fardoza Tohab --- 21/10/2021
# question3.asm-- A program to complete rotations (clockwise and anti-clockwise) of a 3 x 3 character matrix.

.data
           start: 	.asciiz   "Enter a 3x3 matrix: "
           newline:	.asciiz   "\n"
           string1:	.space 256
           string2:	.space 256	
           string3:	.space 256
           result1:	.space 256
           result2:	.space 256
           result3:	.space 256
          


.text
      main:
      
        #loading address of results...
	la $s1, result1  #load address of result 1
	la $s2, result2  #load address of result 2
	la $s3, result3  #load address of result 3
        
	## Print string prompter to prompt user for input
	li $v0, 4               # 4 is the print_string syscall
	la $a0, start	        # load the address of the prompter message into $a0.
	syscall			# do the syscall 
	
       #Printing newline
         li $v0, 4         # 4 is the print syscall
         la $a0, newline   #load address of newline into a0   
         syscall           #do the syscall

	## Getting the user input line by line
	# Get first line from user
	la $a0, string1		# load the address of string1 into $a0
	li $a1, 256             # allocate byte space for string
	li $v0, 8		# load "read_string" code into $v0
	syscall			# make the syscall
	move $t1, $a0		

        #Get second line from user
	la $a0, string2		# load the address of string2 into $a0
	li $a1, 256             # allocate byte space for string
	li $v0, 8		# load "read_string" code into $v0
	syscall			# make the syscall
	move $t2, $a0		

        #Get third line from user
	la $a0, string3 	# load the address of string3 into $a0
	li $a1, 256             # allocate byte space for string
	li $v0, 8		# load "read_string" code into $v0
	syscall			# make the syscall
	move $t3, $a0		

	## Getting the amount of rotations that has to be done
	li $v0, 5 		# load syscall read_int into $v0.
	syscall 		# make the syscall.
	move $t0, $v0 		# move the number read into $t0.
	
	
  
  ##Since it is a 3 by 3 matrix, we'll be initializing 3 counters to use in loops
        li $t9, 0
   #initialize first counter
	add $t4, $t4, $t9
   #initialize second counter		
	add $t5, $t5, $t9		
   #initialize third counter
	add $t6, $t6, $t9		
   #initializing matrix size as three
	li $t7, 3		


register_1:
	beq $t4, $t7, register_2  
	lb $s0, 0($t1)		# Get first character
	sb $s0, 0($s1)		# store the location of first character
	
	#initialize variables to 1
	li $t8, 1
	add $s1, $s1, $t8	
	add $t1, $t1, $t8	
	add $t4, $t4, $t8
	j register_1

register_2:
	beq $t5, $t7, register_3  
	lb $s0, 0($t2)		# Get the first character
	sb $s0, 0($s2)		# store location of first character
	
	#initialize variables to one
	li $t8, 1
	add $s2, $s2, $t8
	add $t2, $t2, $t8	
	add $t5, $t5, $t8
	j register_2

register_3:
	beq $t6, $t7, transferring
	lb $s0, 0($t3)		# Get the first character 
	sb $s0, 0($s3)		# store location of first character
	
	#initialize all variables to one
	li $t8, 1
	add $s3, $s3, $t8
	add $t3, $t3, $t8	
	add $t6, $t6, $t8
	j register_3
	

do_rotation:

## Here rotation is done
	li $t4, 0	
	bgez $t0, clockwise	# check if rotation is positive or negative and do appropriate rotation
	b anti_clockwise	
	
	

initialize_again:
	## Resetting to the base value
	li $t8, -3
	add $s1, $s1, $t8
	add $s2, $s2, $t8
	add $s3, $s3, $t8

	jr $ra
	
transferring:

	## Resetting to the base value
	
	li $t8, -3
	add $t1, $t1, $t8
	add $t2, $t2, $t8
	add $t3, $t3, $t8
	j do_rotation


clockwise:	
		
	beq $t4, $t0, print_results	# if the counter is equal to the number of rotations then print results
	jal initialize_again		# else initialize again

	# rotation of first string

	## First char
	move $a1, $t3		# load address of string1
	lb $t5, 0($a1)		# get character at third position
	
	sb $t5, 0($s1)	
	li $t8, 1	
	add $s1, $s1, $t8	  	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 0($a1)		# get character at third position
	sb $t5, 0($s1)          # store it
	
	li $t8, 1		
	add $s1, $s1, $t8	

	## third char
	move $a1, $t1		# load address of string3	
	lb $t5, 0($a1)		# get the character at third position
	sb $t5, 0($s1)		# store it
	
	li $t8, 1
	add $s1, $s1, $t8 


	## Rotation of second string

	## first char
	move $a1, $t3		# load address of string1
	lb $t5, 1($a1)		# get first character
	sb $t5, 0($s2)		# store it
	
	li $t8, 1
	add $s2, $s2, $t8	 	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 1($a1)		# get second char
	sb $t5, 0($s2)		# store it
	
	li $t8, 1
	add $s2, $s2, $t8

	## third char
	move $a1, $t1		# load address of string3
	lb $t5, 1($a1)		# get the second char
	sb $t5, 0($s2)		# store it
	
	li  $t8, 1
	add $s2, $s2, $t8	 


	##rotation of third string

	## first char
	move $a1, $t3		# load address of string1
	lb $t5, 2($a1)		# get the first char
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8	  	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 2($a1)		# get the first character
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8

	## third char
	move $a1, $t1		# load address of string3
	lb $t5, 2($a1)		# get the first character
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8	 

	jal initialize_again
	li $t6, 0		# counter is reinitialized
	jal get_registers
	
	li $t8, 1
	add $t4, $t4, $t8	# decrement 
	j clockwise	# jump to top of loop
	

	
	

anti_clockwise:		
	
	beq $t4, $t0, print_results	# if the counter is equal to the number of rotations then print results
	jal initialize_again		# else initialize again

	# rotation of first string

	## First char
	move $a1, $t1		# load address of string1
	lb $t5, 2($a1)		# get character at third position
	
	sb $t5, 0($s1)	
	li $t8, 1	
	add $s1, $s1, $t8	  	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 2($a1)		# get character at third position
	sb $t5, 0($s1)          # store it
	
	li $t8, 1		
	add $s1, $s1, $t8	

	## third char
	move $a1, $t3		# load address of string3	
	lb $t5, 2($a1)		# get the character at third position
	sb $t5, 0($s1)		# store it
	
	li $t8, 1
	add $s1, $s1, $t8 


	## Rotation of second string

	## first char
	move $a1, $t1		# load address of string1
	lb $t5, 1($a1)		# get first character
	sb $t5, 0($s2)		# store it
	
	li $t8, 1
	add $s2, $s2, $t8	 	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 1($a1)		# get second char
	sb $t5, 0($s2)		# store it
	
	li $t8, 1
	add $s2, $s2, $t8

	## third char
	move $a1, $t3		# load address of string3
	lb $t5, 1($a1)		# get the second char
	sb $t5, 0($s2)		# store it
	
	li  $t8, 1
	add $s2, $s2, $t8	 


	##rotation of third string

	## first char
	move $a1, $t1		# load address of string1
	lb $t5, 0($a1)		# get the first char
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8	  	

	## second char
	move $a1, $t2		# load address of string2
	lb $t5, 0($a1)		# get the first character
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8

	## third char
	move $a1, $t3		# load address of string3
	lb $t5, 0($a1)		# get the first character
	sb $t5, 0($s3)		# store it
	
	li $t8, 1
	add $s3, $s3, $t8	 

	jal initialize_again
	li $t6, 0		# counter is reinitialized
	jal get_registers
	
	li $t8, -1
	add $t4, $t4, $t8	# decrement 
	j anti_clockwise	# jump to top of loop
	

reset:
	## reset to base
	li $t8, -3
	add $t1, $t1, $t8
	add $t2, $t2, $t8
	add $t3, $t3, $t8
	jr $ra		
		
	
	

get_registers:
	#reload all registers with matrix content
	beq $t6, $t7, reset	# if counter is equal 3 then reset

	
	lb $t5, 0($s1)		# get first character
	sb $t5, 0($t1)		# and store it
	
	li $t8, 1
	
	add $t1, $t1, $t8 	
	add $s1, $s1, $t8	


	lb $t5, 0($s2)		# get the first character
	sb $t5, 0($t2)		# and store it
	
	li $t8, 1
	add $t2, $t2, $t8		
	add $s2, $s2, $t8	

	
	lb $t5, 0($s3)		# get the first character
	sb $t5, 0($t3)		# and store it
	
	li $t8, 1
	add $t3, $t3, $t8	 	
	add $s3, $s3, $t8	
	
	add $t6, $t6, $t8
	
	
	j get_registers
	

print_results:
	jal initialize_again

	la $a0, ($t1)
	li $v0, 4		# print first line after rotation
	syscall			

	la $a0, ($t2)
	li $v0, 4		# print second line after rotation
	syscall			

	la $a0, ($t3)
	li $v0, 4		# print third line after rotation
	syscall			
	j Exit_program
	


Exit_program:
	li $v0, 10		# syscall 10 is for exit.
	syscall			# do the syscall
