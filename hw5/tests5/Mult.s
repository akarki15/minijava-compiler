	.file	"Mult.java"
	.section	.rodata
L15:	.string	""
	.align 8
L16:	.string	" "
	.align 8
L29:	.string	"\n"
	.align 8

	.align 8

	.section	.data
	.local v_some_static_var_0
	.comm v_some_static_var_0,8,8

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
L37:
	movq $15,%rdi 
	movq %rdi,%rdi 
	call m_makeTable_0 
	movq %rax,%rdi 
	call m_printTable_0 
L0:
	jmp L36
L36:
	leave
	ret


	.text
	.align 8
m_makeTable_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$32, %rsp
L39:
	movq %r15,0(%rsp)
	movq %r14,8(%rsp)
	movq %r13,16(%rsp)
	movq %rdi,%r13 
	movq %r13,%rdi 
	call b_createArray_0 
	movq %rax,%r14 
	movq $0,%r15 
L9:
	cmpq %r13, %r15 
	jl L12
L13:
	movq $0,%rdi 
L14:
	movq $0,%rsi 
	cmpq %rsi, %rdi 
	jne L10
L11:
	movq %r14,%rax 
L1:
	movq 16(%rsp),%r13
	movq 8(%rsp),%r14
	movq 0(%rsp),%r15
	jmp L38
L12:
	movq $1,%rdi 
	jmp L14
L10:
	movq %r13,%rdi 
	call b_createArray_0 
	movq %r14,%rdi 
	movq $8,%rsi 
	imulq %rsi,%r15 
	addq %rsi,%rdi 
	movq %rax,%rsi 
	movq $0,%rdi 
L3:
	cmpq %r13, %rdi 
	jl L6
L7:
	movq $0,%rsi 
L8:
	movq $0,%rdx 
	cmpq %rdx, %rsi 
	jne L4
L5:
	movq $1,%rdi 
	addq %rdi,%r15 
	movq %rdi,%r15 
	jmp L9
L6:
	movq $1,%rsi 
	jmp L8
L4:
	imulq %rdi,%r15 
	movq %rdi,%rsi 
	movq %r14,%rdx 
	movq $8,%rcx 
	imulq %rcx,%r15 
	addq %rcx,%rdx 
	movq %rsi,%rcx 
	movq $1,%rsi 
	addq %rsi,%rdi 
	movq %rsi,%rdi 
	jmp L3
L38:
	leave
	ret


	.text
	.align 8
m_printTable_0:
	pushq	%rbp
	movq	%rsp,%rbp
	subq	$48, %rsp
L41:
	movq %r15,0(%rsp)
	movq %r14,8(%rsp)
	movq %r13,16(%rsp)
	movq %r12,24(%rsp)
	movq %rbx,32(%rsp)
	movq %rdi,%rbx 
	movq $0,%r12 
L30:
	movq %r12,%rdi 
	movq %rbx,%rsi 
	movq $-1,%rdx 
	movq $8,%rcx 
	imulq %rcx,%rdx 
	addq %rcx,%rsi 
	cmpq %rcx, %rdi 
	jl L33
L34:
	movq $0,%rdi 
L35:
	movq $0,%rsi 
	cmpq %rsi, %rdi 
	jne L31
L32:
L2:
	movq 32(%rsp),%rbx
	movq 24(%rsp),%r12
	movq 16(%rsp),%r13
	movq 8(%rsp),%r14
	movq 0(%rsp),%r15
	jmp L40
L33:
	movq $1,%rdi 
	jmp L35
L31:
	movq $0,%r13 
L23:
	movq %r13,%rdi 
	movq %rbx,%rsi 
	movq $-1,%rdx 
	movq $8,%rcx 
	imulq %rcx,%rdx 
	addq %rcx,%rsi 
	cmpq %rcx, %rdi 
	jl L26
L27:
	movq $0,%rdi 
L28:
	movq $0,%rsi 
	cmpq %rsi, %rdi 
	jne L24
L25:
	leaq L29(%rip),%rdi
	movq %rdi,%rdi 
	call b_printString_0 
	movq $1,%rdi 
	addq %rdi,%r12 
	movq %rdi,%r12 
	jmp L30
L26:
	movq $1,%rdi 
	jmp L28
L24:
	movq %rbx,%rdi 
	movq $8,%rsi 
	imulq %rsi,%r12 
	addq %rsi,%rdi 
	movq %rsi,%rdi 
	call b_intToString_0 
	leaq L15(%rip),%rdi
	movq %rax,%rdi 
	movq %rdi,%rsi 
	call b_stringConcatenate_0 
	movq %rax,%r14 
	movq %r14,%rdi 
	call b_string_length_0 
	movq %rax,%r15 
L17:
	movq $5,%rdi 
	cmpq %rdi, %r15 
	jl L20
L21:
	movq $0,%rdi 
L22:
	movq $0,%rsi 
	cmpq %rsi, %rdi 
	jne L18
L19:
	movq %r14,%rdi 
	call b_printString_0 
	movq $1,%rdi 
	addq %rdi,%r13 
	movq %rdi,%r13 
	jmp L23
L20:
	movq $1,%rdi 
	jmp L22
L18:
	leaq L16(%rip),%rdi
	movq %rdi,%rdi 
	call b_printString_0 
	movq $1,%rdi 
	addq %rdi,%r15 
	movq %rdi,%r15 
	jmp L17
L40:
	leave
	ret


