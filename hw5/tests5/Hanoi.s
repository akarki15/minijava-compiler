	.file	"Hanoi.java"
	.section	.rodata
L7:	.string	" from "
	.align 8
L3:	.string	"\n"
	.align 8
L4:	.string	"Move disk 1 from "
	.align 8
L5:	.string	" to "
	.align 8
L6:	.string	"Move disk "
	.align 8
L2:	.string	"\nTotal moves: "
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
L15:
	movq $0,%rdi 
	leaq v_count_0(%rip),%rsi
	movq %rdi,%rsi 
	movq $9,%rdi 
	movq $1,%rsi 
	movq $2,%rdx 
	movq %rdi,%rdi 
	movq %rsi,%rsi 
	movq %rdx,%rdx 
	call m_hanoi_0 
	leaq v_count_0(%rip),%rdi
	movq %rdi,%rdi 
	call b_intToString_0 
	leaq L2(%rip),%rdi
	movq %rdi,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L3(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%rdi 
	call b_printString_0 
L0:
	jmp L14
L14:
	leave
	ret


	.text
	.align 8
m_hanoi_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$48, %rsp
L17:
	movq %r15,0(%rsp)
	movq %r14,8(%rsp)
	movq %r13,16(%rsp)
	movq %r12,24(%rsp)
	movq %rbx,32(%rsp)
	movq %rdx,%rbx 
	movq %rsi,%r12 
	movq %rdi,%r13 
	leaq v_count_0(%rip),%rdi
	movq $1,%rsi 
	addq %rsi,%rdi 
	leaq v_count_0(%rip),%rdi
	movq %rsi,%rdi 
	movq $1,%rdi 
	cmpq %rdi, %r13 
	je L11
L12:
	movq $0,%rdi 
L13:
	movq $1,%rsi 
	cmpq %rsi, %rdi 
	je L8
L9:
	movq $6,%rdi 
	subq %r12,%rdi 
	movq %r12,%rdi 
	subq %rbx,%rdi 
	movq %rbx,%r14 
	movq $1,%rdi 
	subq %rdi,%r13 
	movq %rdi,%rdi 
	movq %r12,%rsi 
	movq %r14,%rdx 
	call m_hanoi_0 
	movq %r13,%rdi 
	call b_intToString_0 
	leaq L6(%rip),%rdi
	movq %rdi,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L7(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%r15 
	movq %r12,%rdi 
	call b_intToString_0 
	movq %r15,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L5(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%r12 
	movq %rbx,%rdi 
	call b_intToString_0 
	movq %r12,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L3(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%rdi 
	call b_printString_0 
	movq $1,%rdi 
	subq %rdi,%r13 
	movq %rdi,%rdi 
	movq %r14,%rsi 
	movq %rbx,%rdx 
	call m_hanoi_0 
L10:
L1:
	movq 32(%rsp),%rbx
	movq 24(%rsp),%r12
	movq 16(%rsp),%r13
	movq 8(%rsp),%r14
	movq 0(%rsp),%r15
	jmp L16
L11:
	movq $1,%rdi 
	jmp L13
L8:
	movq %r12,%rdi 
	call b_intToString_0 
	leaq L4(%rip),%rdi
	movq %rdi,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L5(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%r12 
	movq %rbx,%rdi 
	call b_intToString_0 
	movq %r12,%rdi 
	movq %rax,%rsi 
	call b_stringConcatenate_0 
	leaq L3(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%rdi 
	call b_printString_0 
	jmp L10
L16:
	leave
	ret


