# Fardoza Tohab --- 22/10/2021
# question2.asm-- a program that computea a simple integer sum

  
  .data	0x10010000					
prompt_user:	.asciiz		"Enter a sum: "
newline:	.asciiz		"\n"
arr:	.space		256



.text	0x00400000	
        main:
		
		#initialize counter $t0
		li	$t0, 0
	

      loop:	

                move	$sp, $fp
		la	$s0, arr		# load address to start position
			
		# Print prompt the user for input
		la	$a0, prompt_user
		li	$v0, 4
		syscall
		
		# Read in array
		li	$v0, 8			# Read input from user, $a0 and $a1 are already set.
		la	$a0, arr		# load address of input into $a0
		li	$a1, 256		# Set aside bytes for string
		syscall
		
		
		jal	calculation            #do calculation

		lb	$t0, ($s0)
		
		lw	$t0, ($sp)		
		mtc1	$t0, $f12		# move from compressor 1
		
		
		# Print the result out
		li	$v0, 2
		syscall
		la	$a0, newline		# Print newline
		li	$v0, 4
		
		syscall				
		j	EXIT   #exit program when result is produced


loop_one:

		lb	$t0, ($a0)		#load first character
		
		li      $t8, 1
		add	$a0, $a0, $t8
		
		beq	$t0, 0, Done               # Check if we are at the end of input
		bne	$t0, 10, loop_one          # If character is not a newline move to next character
		
		li	$t1, 0			    # Reinitialize $t1 to 0
		
		sb	$t1, -1($a0)		    # Loop adds 1 times too much.

Done:
		jr 	$ra


# Do calculations
calculation:		

                li $t8, -4
		add $sp, $sp, $t8		# Write to address
		
		sw $ra, ($sp)                   # save to stack
		jal	target
		
calculation_2:
		lb	$t2, 0($s0)		# Read in characters
		
		sne	$t3, $t2, 0	# check if character is equal to zero
		seq	$t4, $t2, 45		# check if character is "-"
		seq	$t5, $t2, 43		# check if character is "+"
		
		
		
		li $t8, -4
		add	$sp, $sp, $t8
		sw	$t5, ($sp)		# Save to stack
				
		or	$t4, $t4, $t5		# char if character is not equal to zero
		and	$t3, $t3, $t4
		
		beq	$t3, 0, go_back   
		li $t8, 1
		add	$s0, $s0, $t8		# Next char
		
		jal	target
		
		lw	$t4, ($sp)		# load second character
		li $t8, 4
		add	$sp, $sp, $t8
		
		lw	$t5, ($sp)		# load if it's addition
		li $t8, 4
		add	$sp, $sp, $t8
		lw	$t6, ($sp)		# load first character

		mtc1	$t6, $f2		# convert first operand
		mtc1	$t4, $f4		# convert second operand
		
		beq	$t5, 1, addition
		
subtraction:
		sub.s	$f2, $f2, $f4		# perform subtraction
		swc1	$f2, ($sp)		# Move output to stack
		j	calculation_2
addition:
		add.s	$f2, $f2, $f4		# perform addition
		swc1	$f2, ($sp)		# Move output to stack	
		j	calculation_2

go_back:
                li $t8, 4
		add	$sp, $sp, $t8
		lw	$t0, 0($sp)		# Load output from stack
		
		li $t8, 4
		add	$sp, $sp, $t8
		lw	$ra, 0($sp)		# Load address to calculation
		sw	$t0, 0($sp)		# Save input to stack
		jr	$ra

target:		

                li $t8, -4
		add	$sp, $sp, $t8		# write address to calculation stack
		
		sw	$ra, 0($sp)
		jal	result
		
		
target_loop:
		lb	$t2, 0($s0)		# Read first character
		
		sne	$t3, $t2, 0		# check if character is not equal to zero
		
		
		seq	$t4, $t2, 47
			
		seq	$t5, $t2, 42		
		
		li $t8, -4
		add	$sp, $sp, $t8
		sw	$t5, 0($sp)		
				
		or	$t4, $t4, $t5		
		and	$t3, $t3, $t4
		beq	$t3, 0, target_return
		
		li $t8, 1
		add	$s0, $s0, $t8		# Go to next character
		
		jal	result
		
		lw	$t4, 0($sp)		# Go to second character
		
		li $t8, 4
		add	$sp, $sp, $t8
		lw	$t5, 0($sp)		
		
		li $t8, 4
		add	$sp, $sp, $t8
		lw	$t6, 0($sp)		# Load first operand

		mtc1	$t6, $f2		# First operand 
		mtc1	$t4, $f4		# Second operand 

		

target_return:	

                li $t8, 4
                add	$sp, $sp, $t8
		lw	$t0, 0($sp)		# result is loaded
		
		li $t8, 4
		add	$sp, $sp, $t8
		lw	$ra, 0($sp)		# load calculation to return address
		sw	$t0, 0($sp)		# save the result
		jr	$ra


result:

                li  $t8, -4
		add	$sp, $sp, $t8
		sw	$ra, 0($sp)		# Save address
		
		lb	$t0, 0($s0)		# Read character
				
		bne	$t0, 40, stop_calculation  # check if there are illegal characters
		
		li $t8, 1
		add	$s0, $s0, $t8		# move to next character
		
		jal	calculation		# Start new calculation recursively
		lb	$t0, 0($s0)		# Read in character
		
	
		sne	$t1, $t0, 41
			
		seq	$t2, $t0, 0
		or	$t1, $t1, $t2		# If char is illegal or 0

                li $t8, 1
		add	$s0, $s0, $t8		# move to next character
		j	return_result



stop_calculation:		
		jal	top
		
return_result:	

                lw	$t0, 0($sp)
                
                li $t8, 4
		add	$sp, $sp, $t8
		
		lw	$ra, 0($sp)
		sw	$t0, 0($sp)
		jr	$ra


top:		
		lb	$t1, 0($s0)		# Read first character
		
		
		sle	$t2, $t1, 57		# Check if character is 0 or 9
	
		sge	$t3, $t1, 48
		and	$t3, $t2, $t3

		
		
		li	$t0, 0			# Save length of number to $t0
		
		
top_loop:	

                lb	$t1, 0($s0)		# Read first character
                
		sle	$t2, $t1, 57
		sge	$t3, $t1, 48
		and	$t3, $t2, $t3		# check if character is 0 or 9
		
		
		li $t8, 1
		add	$t0, $t0, $t8		# Increment length
		
		add	$s0, $s0, $t8		# Next character
		beq	$t3, 1, top_loop	# Read character digit
		
		li $t8, -1
		add	$t0, $t0, $t8	
		
		move	$t1, $t0		# move length of the number to $t1
		li	$t5, 1
		li	$t6, 0			# Total number value
		li	$t7, 10
		
		li  $t8, -2
		add $s0, $s0, $t8		# go back to last digit
		
		li	$v0, 0
		
		

		
		
		
convert_loop:	

                lb	$t4, 0($s0)		# Get first character
                
                li $t8, -48
		add $t4, $t4, $t8		# Convert ascii value to zero
		
		li $t8, -1
		add $t1, $t1, $t8		
		
		mul	$t4, $t4, $t5		# multiply ascii value by 10
		add	$t6, $t6, $t4		# add numbers and total up
		
		mul	$t5, $t5, $t7		# multiply by 10
		
		li $t8, -1
		add	$s0, $s0, $t8	# Go to previous digit
		
		bne	$t1, 0, convert_loop 
		
		add	$s0, $s0, $t0		# Move to last digit
		li $t8, 1
		add	$s0, $s0, $t8	# Go to next character
						
						
return_top:	

                mtc1	$t6, $f0		# Move number total to coprocessor
		cvt.s.w	$f0, $f0
		
		
		li $t8, -4
		add	$sp, $sp, $t8
		swc1	$f0, 0($sp)		# Save number to stack
		jr	$ra			# Go back to number



EXIT:		li	$v0, 10             #code 10 is for exit
		syscall

    
    
    
    
    
    
    
      
  
