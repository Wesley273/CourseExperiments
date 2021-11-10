#include <reg51.h>
#include <intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code KEY_TABLE[] = {0xee, 0xde, 0xbe, 0x7e, 0xed, 0xdd, 0xbd, 0x7d, 0xeb, 0xdb, 0xbb, 0x7b, 0xe7, 0xd7, 0xb7, 0x77}; //按键键码表，分别为1~9,加减乘除，清除和等号
uchar data disresult[] = {0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
uchar data disremain[] = {0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
uchar code line1[] = "Error!";
uchar code line2[] = "......";
sbit LCD_RS = P1 ^ 7; //定义控制引脚
sbit LCD_RW = P1 ^ 6;
sbit LCD_EN = P1 ^ 5;
sbit BF = P2 ^ 7; //用于忙检测子程序
uchar flag = 0;	  //防止忙检测死循环
uint temp, temp2, key, m, j, order, error, minus, num1, num2, result, opr, remain;
/************延时子程序************/
void delays(uint ms) //参数为多少即为多少毫秒
{
	uint t;
	while (ms--)
		for (t = 0; t < 120; t++)
			;
}
/************LCD 忙检查子程序************/
bit lcd_busy()
{
	bit result;
	LCD_RS = 0;
	LCD_RW = 1;
	LCD_EN = 1;
	delays(5);
	result = BF; //返回数据最高位BF代表是否忙
	LCD_EN = 0;
	flag++;
	return result;
}
/************写命令子程序************/
void lcd_wcmd(uchar cmd)
{
	{
		while (lcd_busy() & (flag <= 10))
			;
	} //防止忙检测进入死循环
	LCD_RS = 0;
	LCD_RW = 0;
	LCD_EN = 0;
	delays(5);
	P2 = cmd;
	delays(5);
	LCD_EN = 1;
	delays(5);
	LCD_EN = 0;
}

/************写数据子程序************/
void lcd_wdat(uchar dat)
{
	{
		while (lcd_busy() & (flag <= 10))
			;
	} //防止忙检测进入死循环
	LCD_RS = 1;
	LCD_RW = 0;
	LCD_EN = 0;
	delays(5);
	P2 = dat;
	delays(5);
	LCD_EN = 1;
	delays(5);
	LCD_EN = 0;
}
/************LCD初始化子程序************/
void lcd_init()
{
	delays(5);		//等待 LCD 电源稳定
	lcd_wcmd(0x38); //功能设定指令中 DL=1,N=1,F=0,8 位数据宽度,16*2 显示，
	//5*7 点阵字符
	delays(5);
	lcd_wcmd(0x0c); //显示开关控制指令中 D=1,C=0,B=1,显示开，关光标,不闪烁
	delays(5);
	lcd_wcmd(0x06); //进入模式设置指令中 I/D=1,S=0,地址自动增加
	delays(5);
	lcd_wcmd(0x01); //清除 LCD 显示内容,清屏指令 DB7-DB0 部分为 01H
	delays(5);
}

/***********主程序***********/
void main()
{
	key = 20;
	order = 1;
	num1 = num2 = m = temp = temp2 = minus = error = remain = 0;
	lcd_init();
	while (1)
	{
		P3 = 0xF0;			//行输出0
		temp = P3;			//读取列输入
		temp = temp & 0xF0; //保留有效位
		if (temp != 0xF0)
		{
			delays(5);			//延时10毫秒
			P3 = 0xF0;			//重新行输出0
			temp = P3;			//读取列输入
			temp = temp & 0xF0; //保留有效位
			if (temp != 0xF0)
			{
				key = temp;			//记录按键所在列
				temp = temp | 0x0F; //变为列输出0
				P3 = temp;
				temp = P3 & 0x0F; //记录按键所在行
				key = temp | key; //合并键码
				for (j = 0; j < 16; j++)
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
			//无按键时key的值默认为20，这样保证无按键时不会进行任何操作
			if (key == 20)
				delays(5);
			else
			{
				if (key == 14)
				{
					//key=14时要进行复位操作
					delays(5);
					lcd_init();
					key = 20;
					order = 1;
					num1 = num2 = temp = m = temp2 = minus = error = remain = 0;
				}
				for (j = 0; j < 6; j++)
				{
					//初始化存结果的数组，方便输出时检测是否已经输出到最后一位
					disresult[j] = disremain[j] = 0xf;
				}
				//分成正在输入第一个数及运算符、正在输入第二个数两种情况
				switch (order)
				{
				//处理正输入第一个数的情况
				case 1:
				{
					if ((key >= 0) & (key <= 9))
					{
						if (m == 0)
						{
							//光标指向第一行第一列，且保证输入第一个数过程中不再光标归位
							lcd_wcmd(0x80);
							m++;
						}
						delays(5);
						//将键值转换为ASCII码写入LCD显示
						lcd_wdat(key + 48);
						//存放所输入的第一个数
						num1 = num1 * 10 + key;
						//操作完成，key值置默认值
						key = 20;
					}
					if ((key >= 10) & (key <= 13))
					{
						//这时输入了运算符，表示第一个数已完成输入
						order++;
						opr = key;
						//分别将加减乘除号转为ASCII码
						switch (opr)
						{
						case 10:
							lcd_wdat(opr + 0x21);
							break;
						case 11:
							lcd_wdat(opr + 0x22);
							break;
						case 12:
							lcd_wdat(opr + 0x1e);
							break;
						case 13:
							lcd_wdat(opr + 0x22);
							break;
						}
					}
				};
				break;
				//处理正输入第二个数的情况
				case 2:
				{
					if ((key >= 0) & (key <= 9))
					{
						//显示输出、存放输入的数、key值置默认
						lcd_wdat(key + 48);
						delays(5);
						num2 = num2 * 10 + key;
						key = 20;
					}
					if (key == 15)
					{
						//这时按下了等号
						order = 1;
						key = 20;
						switch (opr)
						{
						//加
						case 10:
							result = num1 + num2;
							break;
						//减
						case 11:
							if (num1 >= num2)
								result = num1 - num2;
							else
							{
								//这里考虑了结果为负的情况
								minus = 1;
								result = num2 - num1;
							}
							break;
						//乘
						case 12:
							result = num1 * num2;
							break;
						//除
						case 13:
							//除数为0报错
							if (num2 == 0)
								error = 1;
							else
							{
								//计算出商和余数
								result = num1 / num2;
								remain = num1 % num2;
							}
							break;
						default:
							break;
						}
						//下面对计算结果进行显示输出
						if (error == 1)
						{
							//除0时直接报错，不进行其他任何操作
							lcd_wcmd(0xc0);
							j = 0;
							while (line1[j] != '\0')
							{
								lcd_wdat(line1[j]);
								delays(5);
								j++;
							}
						}
						else
						{
							//将光标指向第二行第一列
							lcd_wcmd(0xc0);
							delays(5);
							//显示输出一个等号
							lcd_wdat(0x3d); 
							//若结果为负先输出一个负号
							if (minus == 1)
								lcd_wdat(0x2d); 
							for (j = 0; j < 6; j++)
							{
								//将结果转十进制存入数组disresult[]
								disresult[5 - j] = result % 10;
								result = result / 10;
								if (result == 0)
									break;
							}
							for (j = 0; j < 6; j++)
							{
								//将结果转ASCII码并进行显示
								if (disresult[j] != 0xf)
									lcd_wdat(disresult[j] + 48);
								delays(5);
							} 
							if (remain != 0)
							{
								j = 0;
								while (line2[j] != '\0')
								{
									//输出"......"
									lcd_wdat(line2[j]);
									delays(5);
									j++;
								}
								for (j = 0; j < 6; j++)
								{
									//将余数转为十进制存入数组disremain[]
									disremain[5 - j] = remain % 10;
									remain = remain / 10;
									if (remain == 0)
										break;
								}
								for (j = 0; j < 6; j++)
								{
									//将余数转为ASCII码输出
									if (disremain[j] != 0xf)
										lcd_wdat(disremain[j] + 48);
									delays(5);
								} 
							}
						}
					}
				};
				break;
				}
			}
		}
	}
}
