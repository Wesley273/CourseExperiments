#include<reg51.h>
#include<intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code T_TABLE[] = { 200,160,120,80,40 };//周期值表
uchar code OUT_TABLE[] = { 0x0f,0x17,0x1b,0x1d,0x1e };//档位输出表，分别为1~5档
uchar code KEY_TABLE[] = { 0x22,0x12,0x21,0x11 };//按键键码表，分别为正向，反向，加速，减速
  /************延时子程序************/
void delays(uint ms)//参数为多少即为多少毫秒
{
	uchar t;
	while (ms--)for (t = 0; t < 120; t++);
}
  /***********主程序***********/
void main()
{
	uchar DIR = 1;
	uchar speed = 0;
	uchar temp, key, j;
	P0 = OUT_TABLE[speed];
	P1 = 0x0fe;
	key = 0;
	while (1)
	{

		P3 = 0x30;//行输出0
		temp = P3;//读取列输入
		temp = temp & 0x30;//保留有效位
		if (temp != 0x30)
		{
			delays(10);//延时10毫秒
			P3 = 0x30;//重新行输出0
			temp = P3;//读取列输入
			temp = temp & 0x30;//保留有效位
			if (temp != 0x30)
			{
				key = temp;//记录按键所在列
				temp = temp | 0x03;//变为列输出0
				P3 = temp;
				temp = P3 & 0x03;//记录按键所在行
				key = temp | key;//合并键码
				for (j = 0; j < 4; j++)
				{
					if (key == KEY_TABLE[j])
					{
						key = j;
						break;
					}
				}
			}
		}
		else
		{
			if (key == 0)DIR = 1;//正转
			if (key == 1)DIR = 0;//反转
			if (key == 2)
			{
				if (speed == 4)
					speed = speed; else
					speed++;
				key = ~DIR;
			}
			if (key == 3)
			{
				if (speed == 0)
					speed = speed; else
					speed--;
				key = ~DIR;//按位取反
			}
			if (DIR == 1)//正转时
			{
				for (j = 0; j < T_TABLE[speed]; j++)
				{
					delays(5);
				}
				P1 = _crol_(P1, 1);//左移
			}
			if (DIR == 0)//反转时
			{
				for (j = 0; j < T_TABLE[speed]; j++)
				{
					delays(5);
				}
				P1 = _cror_(P1, 1);//右移
			}
			P0 = OUT_TABLE[speed];
		}
	}
}
