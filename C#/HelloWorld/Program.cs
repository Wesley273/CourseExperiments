using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
namespace Learn
{
    class Program
    {
        static void Main(string[] args)
        {
            double d1=3.14;
            double d2=d1;
            Console.WriteLine("1111:"+((object)d1==(object)d2));
        }
    }
}
