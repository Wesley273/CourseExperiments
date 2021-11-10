#include<reg51.h>
#include<intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code line1[] = "f=";
uchar code line2[] = "Hz";
uchar data disf[] = { 0xf,0xf,0xf,0xf,0xf,0xf };
//����LCD��������
sbit LCD_RS = P2^0;
sbit LCD_RW = P2^1;
sbit LCD_EN = P2^2;
//����ADC��������
sbit CLK = P2^3;
sbit START = P2^4;
sbit OE = P2^5;
sbit OUT_PULSE = P2^6;//5us����
sbit EOC = P2^7;
sbit BF = P1^7;//���ڼ��æ�ӳ���
uchar flag = 0;//���ڷ�ֹ���æ��ѭ��
uchar j, time1s;
uchar i = 1;
/************��ʱ�ӳ���************/
void delays(uint ms)//����Ϊ���ټ�Ϊ���ٺ���
{
	uint t;
	while (ms--)for (t = 0; t < 120; t++);
}
/************LCD æ����ӳ���************/
bit lcd_busy()
{
	bit result;
	LCD_RS = 0;
	LCD_RW = 1;
	LCD_EN = 1;
	delays(5);
	result = BF; //�����������λBF�����Ƿ�æ
	LCD_EN = 0;
	flag++;
	return result;
}
/************д�����ӳ���************/
void lcd_wcmd(uchar cmd)
{
	{while (lcd_busy() & (flag <= 10)); }//��ֹæ��������ѭ��
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

/************д�����ӳ���************/
void lcd_wdat(uchar dat)
{
	{while (lcd_busy() & (flag <= 10)); }//��ֹæ��������ѭ��
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
/************��ַ�����Ӻ���************/
void address(uchar x)
{
	lcd_wcmd(x + 0x80); //��ʾλ�õ�ȷ�������涨Ϊ"80H+��ַ��x"	
}
/************LCD��ʼ���ӳ���************/
void lcd_init()
{
	delays(5); //�ȴ� LCD ��Դ�ȶ�
	lcd_wcmd(0x38); //�����趨ָ���� DL=1,N=1,F=0,8 λ���ݿ��,16*2 ��ʾ��
	//5*7 �����ַ�
	delays(5);
	lcd_wcmd(0x0c); //��ʾ���ؿ���ָ���� D=1,C=0,B=1,��ʾ�����ع��,����˸
	delays(5);
	lcd_wcmd(0x06); //����ģʽ����ָ���� I/D=1,S=0,��ַ�Զ�����
	delays(5);
	lcd_wcmd(0x01); //��� LCD ��ʾ����,����ָ�� DB7-DB0 ����Ϊ 01H
	delays(5);
}
/***********��ʱ��T0�жϷ������************/
void t0(void) interrupt 1
{
	ET0 = 0;
	TH0 = 0x3C;
	TL0 = 0xB1;//ʹT0������ʵ��50ms��ʱ
	i = i + 1;
	if (i == 20) {
		time1s = 1;
		i = 0;
	}//ʵ��1s��ʱ
	ET0 = 1;
}
/***********��ʱ��T1�жϷ������************/
void t1(void) interrupt 3
{
	ET1 = 0;
	CLK = ~CLK;
	ET1 = 1;
}
/***********AD���ݲɼ��ӳ���************/
uint getad()
{
	uint temp;
	START = 0;
	while (!EOC)
		P0 = 0xff;//Ҫ��P0�����ݱ���д��0xff
	OE = 1;
	temp = P0;
	OE = 0;
	START = 1;
	return(temp);
}
/************Ƶ����ʾ�ӳ���************/
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
	}//��������ѭ�������תʮ���Ʋ���ʾ
	j = 0;
	while (line2[j] != '\0')
	{
		lcd_wdat(line2[j]);
		j++;
	};
}
/***********������***********/
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
	TMOD = 0x21;//T0��ʽ1��T1��ʽ2����Ϊ���ſض�ʱ��
	EA = 1;//�����ж�
	TH0 = 0x3c;
	TL0 = 0xb0;//ʹT0��ʱ��ʵ��50ms��ʱ
	ET0 = 1;
	TR0 = 1;//������ʱ��T0
	TH1 = 0xfe;
	TL1 = 0xfe;//T1ʵ��100kHz��ʱ�����50kHz����
	ET1 = 1;
	TR1 = 1;//������ʱ��T1
	while (1)
	{
		temp1 = temp2;
		temp2 = getad();//ת����ϵ������źŴ���temp2����һ�ε���������temp1��
		if (((temp1 < 128) && (temp2 > 128)) || ((temp1 > 128) && (temp2 < 128)) || (temp2 == 128))
		{
			zero++;
			OUT_PULSE = 1;
			_nop_();
			_nop_();
			_nop_();
			_nop_();
			_nop_();
			OUT_PULSE = 0;//һ��nopָ��Ϊ1��s�����Ϊ5��s
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