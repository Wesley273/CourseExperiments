using System;
namespace PackingAndUnpacking
{
    class Schedule
    {
        static void Main(string[] args)
        {
            int x1 = 111;
            int x2 = x1;
            int x3 = 0;
            Console.WriteLine("尝试查看x1与x2内存地址：x1={0},x2={1}", (object)x1, (object)x2);
            Console.WriteLine("查看x1与x2内存地址是否相等：" + ((object)x1 == (object)x2));
            object obj1 = x1;
            object obj2 = obj1;
            Console.WriteLine("查看obj1与obj2指向的地址是否相等：" + ((object)obj1 == (object)obj2));
            x1 = 2;
            Console.WriteLine("尝试不进行类型转换直接输出obj1：" + obj1);
            Console.WriteLine("进行类型转换再输出obj1：" + (int)obj1);
            x3 = (int)obj1;
            Console.WriteLine("将obj1拆箱给x3，看x3与x1内存地址是否相等：" + ((object)x1 == (object)x3));
            string s1 = "Visual Studio Code";
            string s2 = s1;
            Console.WriteLine("查看s1与s2是否指向同一个地址：" + ((object)s1 == (object)s2));
            s1 = "C#";
            Console.WriteLine("只改变s1，查看s1与s2是否指向同一个地址：" + ((object)s1 == (object)s2));
            s2 = "C#";
            Console.WriteLine("将s1、s2改成相同值，查看s1与s2是否指向同一个地址：" + ((object)s1 == (object)s2));
        }
    }
}