#include <reg51.h>
#define uint unsigned int
#define uchar unsigned char
sbit BELL = P1 ^ 1; //P1.1口位控制铃声
sbit LED = P1 ^ 0;	//P1.0口位控制灯
uint count;
/**************************主程序**********************************/
void main() //按键按下后就触发中断
{
	EA = 1;	 //开放中断
	EX0 = 1; //允许外部中断0中断
	IT0 = 0; //外部中断0设置为电平触发方式
	while (1)
		; //循环执行
}
void delay(uint count) //参数为多少就延时多少ms
{
	uint x, y;
	for (x = count; x > 0; x--)
		for (y = 120; y > 0; y--)
			;
}
void show(uint count)
{
	uint i;
	for (i = 0; i <= count; i++)
	{
		LED = 0;
		BELL = 0;	//开灯响铃
		delay(500); //延时0.5s
		LED = 1;
		BELL = 1; //关灯灭铃
		delay(500);
	}
}
void INTT0() interrupt 0
{
	EA = 0;		 //屏蔽所有中断请求
	show(count); //调用灯铃子程序
	count++;	 //每按一次键，记一次数
	delay(50);
	if (count >= 10) //到10清零按键数
		count = 0;
	EA = 1; //开放中断
}