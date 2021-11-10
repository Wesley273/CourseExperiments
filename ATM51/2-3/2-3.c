#include<reg51.h>
#include<stdio.h>
char idata a[32] _at_ 0x30;
int i _at_ 0x70;
int j _at_ 0x72;
int t _at_ 0x74;
void main()
{
	char b[32] = { 1,3,9,2,17,4,11,6,5,20,100,64,21,14,79,35,92,7,91,23,65,16,13,18,18,73,65,101,27,19,62,69 };
	for (i = 0; i < 32; i++)
	{
		a[i] = b[i];
	}
	for (i = 0; i < 31; i++)
	{
		for (j = 0; j < 31 - i; j++)
		{
			if (a[j] > a[j + 1])
			{
				t = a[j];
				a[j] = a[j + 1];
				a[j + 1] = t;
			}
		}
	}
	while (1);
}