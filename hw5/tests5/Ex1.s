	.file	"Ex1.java"
	.section	.rodata
L1:	.string	"The result is \n\n"
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
L3:
	movq $3,%rdi 
	movq $4,%rsi 
	addq %rsi,%rdi 
	movq $5,%rdi 
	movq $6,%rdx 
	addq %rdx,%rdi 
	imulq %rdx,%rsi 
	movq %rdx,%rdi 
	call b_intToString_0 
	leaq L1(%rip),%rdi
	movq %rdi,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%rdi 
	call b_printString_0 
L0:
	jmp L2
L2:
	leave
	ret


