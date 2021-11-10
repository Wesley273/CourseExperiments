#include <stdio.h>
/*递归法计算斐波那契数列*/
long int Fib(int N)
{
	if (N <= 1)
		return 1;
	else
		return Fib(N - 1) + Fib(N - 2);
}

int main()
{
	int x = 0;
	x = Fib(10);
	printf("%d", x);
	getchar();
	return 0;
}