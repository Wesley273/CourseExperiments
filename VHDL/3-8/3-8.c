#include<reg51.h>
#include<intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code line1[] = "f=";
uchar code line2[] = "Hz";
uchar data disf[] = { 0xf,0xf,0xf,0xf,0xf,0xf };
//定义LCD控制引脚
sbit LCD_RS = P2^0;
sbit LCD_RW = P2^1;
sbit LCD_EN = P2^2;
//定义ADC控制引脚
sbit CLK = P2^3;
sbit START = P2^4;
sbit OE = P2^5;
sbit OUT_PULSE = P2^6;//5us脉冲
sbit EOC = P2^7;
sbit BF = P1^7;//用于检测忙子程序
uchar flag = 0;//用于防止检测忙死循环
uchar j, time1s;
uchar i = 1;
/************延时子程序************/
void delays(uint ms)//参数为多少即为多少毫秒
{
	uint t;
	while (ms--)for (t = 0; t < 120; t++);
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
	{while (lcd_busy() & (flag <= 10)); }//防止忙检测进入死循环
	LCD_RS = 0;
	LCD_RW = 0;
	LCD_EN = 0;
	delays(5);
	P1 = cmd;
	delays(5);
	LCD_EN = 1;
	delays(5);
	LCD_EN = 0;
}

/************写数据子程序************/
void lcd_wdat(uchar dat)
{
	{while (lcd_busy() & (flag <= 10)); }//防止忙检测进入死循环
	LCD_RS = 1;
	LCD_RW = 0;
	LCD_EN = 0;
	delays(5);
	P1 = dat;
	delays(5);
	LCD_EN = 1;
	delays(5);
	LCD_EN = 0;
}
/************地址设置子函数************/
void address(uchar x)
{
	lcd_wcmd(x + 0x80); //显示位置的确定方法规定为"80H+地址码x"	
}
/************LCD初始化子程序************/
void lcd_init()
{
	delays(5); //等待 LCD 电源稳定
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
/***********定时器T0中断服务程序************/
void t0(void) interrupt 1
{
	ET0 = 0;
	TH0 = 0x3C;
	TL0 = 0xB1;//使T0计数器实现50ms定时
	i = i + 1;
	if (i == 20) {
		time1s = 1;
		i = 0;
	}//实现1s定时
	ET0 = 1;
}
/***********定时器T1中断服务程序************/
void t1(void) interrupt 3
{
	ET1 = 0;
	CLK = ~CLK;
	ET1 = 1;
}
/***********AD数据采集子程序************/
uint getad()
{
	uint temp;
	START = 0;
	while (!EOC)
		P0 = 0xff;//要从P0读数据必先写入0xff
	OE = 1;
	temp = P0;
	OE = 0;
	START = 1;
	return(temp);
}
/************频率显示子程序************/
void lcd_dis(uchar x)
{
	address(2);
	for (j = 0; j < 6; j++)
	{
		disf[5 - j] = x % 10;
		x = x / 10;
		if (x == 0)break;
	}
	for (j = 0; j < 6; j++)
	{
		if (disf[j] != 0xf)lcd_wdat(disf[j] + 48); delays(5);
	}//以上两个循环将结果转十进制并显示
	j = 0;
	while (line2[j] != '\0')
	{
		lcd_wdat(line2[j]);
		j++;
	};
}
/***********主程序***********/
void main()
{
	uchar temp1, temp2, f, zero;
	temp1 = temp2 = j = f = OUT_PULSE = OE = time1s = 0;
	i = START = 1;
	lcd_init();
	while (line1[j] != '\0')
	{
		lcd_wdat(line1[j]);
		delays(5);
		j++;
	}
	TMOD = 0x21;//T0方式1，T1方式2，均为非门控定时器
	EA = 1;//开总中断
	TH0 = 0x3c;
	TL0 = 0xb0;//使T0定时器实现50ms定时
	ET0 = 1;
	TR0 = 1;//启动定时器T0
	TH1 = 0xfe;
	TL1 = 0xfe;//T1实现100kHz定时，输出50kHz方波
	ET1 = 1;
	TR1 = 1;//启动定时器T1
	while (1)
	{
		temp1 = temp2;
		temp2 = getad();//转换完毕的数字信号传给temp2，上一次的数据留在temp1中
		if (((temp1 < 128) && (temp2 > 128)) || ((temp1 > 128) && (temp2 < 128)) || (temp2 == 128))
		{
			zero++;
			OUT_PULSE = 1;
			_nop_();
			_nop_();
			_nop_();
			_nop_();
			_nop_();
			OUT_PULSE = 0;//一个nop指令为1μs，五个为5μs
		}
		if (time1s == 1)
		{
			ET0 = ET1 = 0;
			time1s = 0;
			f = zero / 2;
			lcd_dis(f);
			zero = 0;
			ET0 = ET1 = 1;
		}
	}
}