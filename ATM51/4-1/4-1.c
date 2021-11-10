#include <reg51.h>
#define uint unsigned int
#define uchar unsigned char
sbit BELL = P1 ^ 1; //P1.1��λ��������
sbit LED = P1 ^ 0;	//P1.0��λ���Ƶ�
uint count;
/**************************������**********************************/
void main() //�������º�ʹ����ж�
{
	EA = 1;	 //�����ж�
	EX0 = 1; //�����ⲿ�ж�0�ж�
	IT0 = 0; //�ⲿ�ж�0����Ϊ��ƽ������ʽ
	while (1)
		; //ѭ��ִ��
}
void delay(uint count) //����Ϊ���پ���ʱ����ms
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
		BELL = 0;	//��������
		delay(500); //��ʱ0.5s
		LED = 1;
		BELL = 1; //�ص�����
		delay(500);
	}
}
void INTT0() interrupt 0
{
	EA = 0;		 //���������ж�����
	show(count); //���õ����ӳ���
	count++;	 //ÿ��һ�μ�����һ����
	delay(50);
	if (count >= 10) //��10���㰴����
		count = 0;
	EA = 1; //�����ж�
}