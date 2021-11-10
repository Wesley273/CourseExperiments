#include<reg51.h>
#include<intrins.h>
#define uchar unsigned char
#define uint unsigned int
uchar code T_TABLE[] = { 200,160,120,80,40 };//����ֵ��
uchar code OUT_TABLE[] = { 0x0f,0x17,0x1b,0x1d,0x1e };//��λ������ֱ�Ϊ1~5��
uchar code KEY_TABLE[] = { 0x22,0x12,0x21,0x11 };//����������ֱ�Ϊ���򣬷��򣬼��٣�����
  /************��ʱ�ӳ���************/
void delays(uint ms)//����Ϊ���ټ�Ϊ���ٺ���
{
	uchar t;
	while (ms--)for (t = 0; t < 120; t++);
}
  /***********������***********/
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

		P3 = 0x30;//�����0
		temp = P3;//��ȡ������
		temp = temp & 0x30;//������Чλ
		if (temp != 0x30)
		{
			delays(10);//��ʱ10����
			P3 = 0x30;//���������0
			temp = P3;//��ȡ������
			temp = temp & 0x30;//������Чλ
			if (temp != 0x30)
			{
				key = temp;//��¼����������
				temp = temp | 0x03;//��Ϊ�����0
				P3 = temp;
				temp = P3 & 0x03;//��¼����������
				key = temp | key;//�ϲ�����
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
			if (key == 0)DIR = 1;//��ת
			if (key == 1)DIR = 0;//��ת
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
				key = ~DIR;//��λȡ��
			}
			if (DIR == 1)//��תʱ
			{
				for (j = 0; j < T_TABLE[speed]; j++)
				{
					delays(5);
				}
				P1 = _crol_(P1, 1);//����
			}
			if (DIR == 0)//��תʱ
			{
				for (j = 0; j < T_TABLE[speed]; j++)
				{
					delays(5);
				}
				P1 = _cror_(P1, 1);//����
			}
			P0 = OUT_TABLE[speed];
		}
	}
}
