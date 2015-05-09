	.file	"Hanoi.java"
	.section	.rodata
L2:	.string	"\n"
	.align 8
L1:	.string	"\nTotal moves: "
	.align 8
L5:	.string	"Move disk 1 from "
	.align 8
L4:	.string	"n is "
	.align 8
L7:	.string	"Move disk "
	.align 8
L6:	.string	" to "
	.align 8
L8:	.string	" from "
	.align 8

	.align 8

	.section	.data
	.local v_count_0
	.comm v_count_0,8,8

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
L13:
	movq $0,%rsi
	leaq v_count_0(%rip),%rdi
	movq %rsi,(%rdi)
	movq $9,%rdi
	movq $1,%rsi
	movq $2,%rdx
	call m_hanoi_0
	movq v_count_0(%rip),%rdi
	call b_intToString_0
	movq %rax,%rsi
	leaq L1(%rip),%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L2(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%rdi
	call b_printString_0
L0:
	jmp L12
L12:
	leave
	ret


	.text
	.align 8
m_hanoi_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$48, %rsp
L15:
	movq %r15,0(%rsp)
	movq %r14,8(%rsp)
	movq %r13,16(%rsp)
	movq %r12,24(%rsp)
	movq %rbx,32(%rsp)
	movq %rdx,%rbx
	movq %rsi,%r12
	movq %rdi,%r13
	movq %r13,%rdi
	call b_intToString_0
	movq %rax,%rsi
	leaq L4(%rip),%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L2(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%rdi
	call b_printString_0
	movq v_count_0(%rip),%rdi
	addq $1,%rdi
	leaq v_count_0(%rip),%rsi
	movq %rdi,(%rsi)
	cmpq $1,%r13
	je L9
L10:
	movq $6,%r14
	subq %r12,%r14
	subq %rbx,%r14
	movq %r13,%rdi
	subq $1,%rdi
	movq %r12,%rsi
	movq %r14,%rdx
	call m_hanoi_0
	movq %r13,%rdi
	call b_intToString_0
	movq %rax,%rsi
	leaq L7(%rip),%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L8(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%r15
	movq %r12,%rdi
	call b_intToString_0
	movq %rax,%rsi
	movq %r15,%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L6(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%r12
	movq %rbx,%rdi
	call b_intToString_0
	movq %rax,%rsi
	movq %r12,%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L2(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%rdi
	call b_printString_0
	movq %r13,%rdi
	subq $1,%rdi
	movq %r14,%rsi
	movq %rbx,%rdx
	call m_hanoi_0
L11:
L3:
	movq 32(%rsp),%rbx
	movq 24(%rsp),%r12
	movq 16(%rsp),%r13
	movq 8(%rsp),%r14
	movq 0(%rsp),%r15
	jmp L14
L9:
	movq %r12,%rdi
	call b_intToString_0
	movq %rax,%rsi
	leaq L5(%rip),%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L6(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%r12
	movq %rbx,%rdi
	call b_intToString_0
	movq %rax,%rsi
	movq %r12,%rdi
	call b_stringConcatenate_0
	movq %rax,%rdi
	leaq L2(%rip),%rsi
	call b_stringConcatenate_0
	movq %rax,%rdi
	call b_printString_0
	jmp L11
L14:
	leave
	ret


