	.file	"T.java"
	.section	.rodata
L1:	.string	"Hello\n"
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
	leaq L1(%rip),%rdi
	movq %rdi,%rdi 
	call b_printString_0 
L0:
	jmp L2
L2:
	leave
	ret


