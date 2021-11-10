#include <reg51.h>
#include <intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code KEY_TABLE[] = {0xee, 0xde, 0xbe, 0x7e, 0xed, 0xdd, 0xbd, 0x7d, 0xeb, 0xdb, 0xbb, 0x7b, 0xe7, 0xd7, 0xb7, 0x77}; //����������ֱ�Ϊ1~9,�Ӽ��˳�������͵Ⱥ�
uchar data disresult[] = {0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
uchar data disremain[] = {0xf, 0xf, 0xf, 0xf, 0xf, 0xf};
uchar code line1[] = "Error!";
uchar code line2[] = "......";
sbit LCD_RS = P1 ^ 7; //�����������
sbit LCD_RW = P1 ^ 6;
sbit LCD_EN = P1 ^ 5;
sbit BF = P2 ^ 7; //����æ����ӳ���
uchar flag = 0;	  //��ֹæ�����ѭ��
uint temp, temp2, key, m, j, order, error, minus, num1, num2, result, opr, remain;
/************��ʱ�ӳ���************/
void delays(uint ms) //����Ϊ���ټ�Ϊ���ٺ���
{
	uint t;
	while (ms--)
		for (t = 0; t < 120; t++)
			;
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
	{
		while (lcd_busy() & (flag <= 10))
			;
	} //��ֹæ��������ѭ��
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

/************д�����ӳ���************/
void lcd_wdat(uchar dat)
{
	{
		while (lcd_busy() & (flag <= 10))
			;
	} //��ֹæ��������ѭ��
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
/************LCD��ʼ���ӳ���************/
void lcd_init()
{
	delays(5);		//�ȴ� LCD ��Դ�ȶ�
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

/***********������***********/
void main()
{
	key = 20;
	order = 1;
	num1 = num2 = m = temp = temp2 = minus = error = remain = 0;
	lcd_init();
	while (1)
	{
		P3 = 0xF0;			//�����0
		temp = P3;			//��ȡ������
		temp = temp & 0xF0; //������Чλ
		if (temp != 0xF0)
		{
			delays(5);			//��ʱ10����
			P3 = 0xF0;			//���������0
			temp = P3;			//��ȡ������
			temp = temp & 0xF0; //������Чλ
			if (temp != 0xF0)
			{
				key = temp;			//��¼����������
				temp = temp | 0x0F; //��Ϊ�����0
				P3 = temp;
				temp = P3 & 0x0F; //��¼����������
				key = temp | key; //�ϲ�����
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
			//�ް���ʱkey��ֵĬ��Ϊ20��������֤�ް���ʱ��������κβ���
			if (key == 20)
				delays(5);
			else
			{
				if (key == 14)
				{
					//key=14ʱҪ���и�λ����
					delays(5);
					lcd_init();
					key = 20;
					order = 1;
					num1 = num2 = temp = m = temp2 = minus = error = remain = 0;
				}
				for (j = 0; j < 6; j++)
				{
					//��ʼ�����������飬�������ʱ����Ƿ��Ѿ���������һλ
					disresult[j] = disremain[j] = 0xf;
				}
				//�ֳ����������һ���������������������ڶ������������
				switch (order)
				{
				//�����������һ���������
				case 1:
				{
					if ((key >= 0) & (key <= 9))
					{
						if (m == 0)
						{
							//���ָ���һ�е�һ�У��ұ�֤�����һ���������в��ٹ���λ
							lcd_wcmd(0x80);
							m++;
						}
						delays(5);
						//����ֵת��ΪASCII��д��LCD��ʾ
						lcd_wdat(key + 48);
						//���������ĵ�һ����
						num1 = num1 * 10 + key;
						//������ɣ�keyֵ��Ĭ��ֵ
						key = 20;
					}
					if ((key >= 10) & (key <= 13))
					{
						//��ʱ���������������ʾ��һ�������������
						order++;
						opr = key;
						//�ֱ𽫼Ӽ��˳���תΪASCII��
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
				//����������ڶ����������
				case 2:
				{
					if ((key >= 0) & (key <= 9))
					{
						//��ʾ�����������������keyֵ��Ĭ��
						lcd_wdat(key + 48);
						delays(5);
						num2 = num2 * 10 + key;
						key = 20;
					}
					if (key == 15)
					{
						//��ʱ�����˵Ⱥ�
						order = 1;
						key = 20;
						switch (opr)
						{
						//��
						case 10:
							result = num1 + num2;
							break;
						//��
						case 11:
							if (num1 >= num2)
								result = num1 - num2;
							else
							{
								//���￼���˽��Ϊ�������
								minus = 1;
								result = num2 - num1;
							}
							break;
						//��
						case 12:
							result = num1 * num2;
							break;
						//��
						case 13:
							//����Ϊ0����
							if (num2 == 0)
								error = 1;
							else
							{
								//������̺�����
								result = num1 / num2;
								remain = num1 % num2;
							}
							break;
						default:
							break;
						}
						//����Լ�����������ʾ���
						if (error == 1)
						{
							//��0ʱֱ�ӱ��������������κβ���
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
							//�����ָ��ڶ��е�һ��
							lcd_wcmd(0xc0);
							delays(5);
							//��ʾ���һ���Ⱥ�
							lcd_wdat(0x3d); 
							//�����Ϊ�������һ������
							if (minus == 1)
								lcd_wdat(0x2d); 
							for (j = 0; j < 6; j++)
							{
								//�����תʮ���ƴ�������disresult[]
								disresult[5 - j] = result % 10;
								result = result / 10;
								if (result == 0)
									break;
							}
							for (j = 0; j < 6; j++)
							{
								//�����תASCII�벢������ʾ
								if (disresult[j] != 0xf)
									lcd_wdat(disresult[j] + 48);
								delays(5);
							} 
							if (remain != 0)
							{
								j = 0;
								while (line2[j] != '\0')
								{
									//���"......"
									lcd_wdat(line2[j]);
									delays(5);
									j++;
								}
								for (j = 0; j < 6; j++)
								{
									//������תΪʮ���ƴ�������disremain[]
									disremain[5 - j] = remain % 10;
									remain = remain / 10;
									if (remain == 0)
										break;
								}
								for (j = 0; j < 6; j++)
								{
									//������תΪASCII�����
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
