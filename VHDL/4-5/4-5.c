#include <reg51.h>
#include <stdio.h>
#include <absacc.h> // 可使用其中定义的宏来访问绝对地址
#define uchar unsigned char
#define uint unsigned int
#define PA XBYTE[0x7FFC]  //PA指代外部数据存储器7FFCh的地址
#define COM XBYTE[0x7FFF] //由P0 P2口联合输出地址
uint temp, t;
uchar a;
void show(uint temp);
void delay();
void delays();
void init_8255();
void init_8255()
{
  COM = 0x80; // c = out, a = out,b = out
}
void delay() //延时500MS子程序
{
  uint i, j;
  for (i = 500; i > 0; i--)
    for (j = 120; j > 0; j--)
      ;
}
void delays() //延时1S子程序
{
  uint i, j;
  for (i = 1000; i > 0; i--)
    for (j = 120; j > 0; j--)
      ;
}
/***************************主程序*****************************/
void main()
{
  init_8255();
  TMOD = 0x01; //设定工作模式1
  EA = 1;      //开放中断
  ET0 = 1;     //允许T0中断
  TF0 = 0;     //定时器0溢出标志位
  while (1)
  {
    //之所以分开考虑，是因为需要用BCD码
    a = 0x26;                          //变量a赋初值
    for (temp = 25; temp > 19; temp--) //绿灯倒计时25s
    {
      a = a - 1;
      PA = 0x69; //东西绿灯亮
      show(a);   //调用数码管显示子程序
      delays();  //延时1s
    }
    a = 0x1a;                         //变量a赋值
    for (temp = 19; temp > 9; temp--) //倒计时
    {
      a = a - 1;
      PA = 0x69; //东西绿灯亮
      show(a);
      delays();
    }
    a = 0x0a;
    for (temp = 9; temp > 0; temp--)
    {
      a = a - 1;
      PA = 0x69;
      show(a);
      delays();
    }
    for (t = 5; t > 0; t--) //绿灯闪烁5s
    {
      a = 0; //当计时值为0时闪烁
      show(a);
      PA = 0xeb; //东西绿灯灭
      delay();   //延时0.5s
      PA = 0x69; //东西绿灯亮
      delay();   //延时0.5s
    }
    a = 0x26;
    for (temp = 25; temp > 19; temp--) //绿灯倒计时25s
    {
      a = a - 1;
      PA = 0x96; //南北绿灯亮
      show(a);
      delays();
    }
    a = 0x1a;
    for (temp = 19; temp > 9; temp--)
    {
      a = a - 1;
      PA = 0x96;
      show(a);
      delays();
    }
    a = 0x0a;
    for (temp = 9; temp > 0; temp--)
    {
      a = a - 1;
      PA = 0x96;
      show(a);
      delays();
    }
    for (t = 5; t > 0; t--) //绿灯闪烁5s
    {
      a = 0; //当计时值为0时闪烁
      show(a);
      PA = 0xbe; //南北绿灯灭
      delay();   //延时0.5s
      PA = 0x96; //南北绿灯亮
      delay();   //延时0.5s
    }
  }
}
void show(uint a) //显示子程序
{
  SCON = 0x00; //工作模式0
  SBUF = a;    //将变量a的值送给缓存寄存器
  while (!TI)  //等待发送是否完成
    TI = 0;    //中断标志位清零
}
