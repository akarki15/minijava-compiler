	.file	"Fib.java"
	.section	.rodata
L2:	.string	"\n"
	.align 8

	.align 8

	.section	.data

	.section	.text
	.align 8
	.globl main
	.type	main,@function
main:
	jmp	m_main_0
	.text
	.align 8
m_main_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$0, %rsp
L10:
	movq $30,%rdi 
	movq %rdi,%rdi 
	call m_fib_0 
	movq %rax,%rdi 
	call b_intToString_0 
	leaq L2(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%rdi 
	call b_printString_0 
L0:
	jmp L9
L9:
	leave
	ret


	.text
	.align 8
m_fib_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$16, %rsp
L12:
	movq %r15,0(%rsp)
	movq %r14,8(%rsp)
	movq %rdi,%r14 
	movq $1,%rdi 
	cmpq %rdi, %r14 
	jle L6
L7:
	movq $0,%rdi 
L8:
	movq $1,%rsi 
	cmpq %rsi, %rdi 
	je L3
L4:
	movq $1,%rdi 
	subq %rdi,%r14 
	movq %rdi,%rdi 
	call m_fib_0 
	movq %rax,%r15 
	movq $2,%rdi 
	subq %rdi,%r14 
	movq %rdi,%rdi 
	call m_fib_0 
	addq %rax,%r15 
L1:
	movq 8(%rsp),%r14
	movq 0(%rsp),%r15
	jmp L11
L6:
	movq $1,%rdi 
	jmp L8
L3:
	movq $1,%rax 
	jmp L1
L13:
L5:
	jmp L1
L11:
	leave
	ret


