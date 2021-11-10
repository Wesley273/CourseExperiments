using System;

namespace AreaCalculator
{
    interface IShape
    {
        //理论上多边形的面积都是可以这样计算的
        public double getArea(double bottom, double height);
        //周长使用参数数组，可实现任意多边形周长的求解
        public double getPerimeter(params double[] sides);
    }
    class Triangle : IShape
    {
        public double getArea(double bottom, double height)
        {
            //这是使用底乘高计算面积的方法
            return height * bottom / 2;
        }
        public double getArea(double[] sides)
        {
            //这是使用海伦公式计算面积的方法
            double halfPerimeter = getPerimeter(sides) / 2;
            return Math.Sqrt(halfPerimeter * (halfPerimeter - sides[0]) * (halfPerimeter - sides[1]) * (halfPerimeter - sides[2]));
        }
        public double getArea(double a, double b, double angle)
        {
            //这是使用两边和其夹角计算面积的方法
            return a * b * Math.Sin(angle) / 2;
        }
        public double getPerimeter(params double[] sides)
        {
            double perimeter = 0;
            foreach (double i in sides)
            {
                perimeter += i;
            }
            return perimeter;
        }
    }
    class IsoscelesTriangle : Triangle
    {
        public double getArea(double waist, double bottom, int judge)
        {
            //这是等腰三角形利用腰和底计算面积
            double halfPerimeter = getPerimeter(waist, bottom) / 2;
            return Math.Sqrt(halfPerimeter * (halfPerimeter - bottom)) * (halfPerimeter - waist);
        }
        public double getPerimeter(double waist, double bottom)
        {
            return 2 * waist + bottom;
        }
    }
    class EquilateralTriangle : IsoscelesTriangle
    {
        public double getArea(double side)
        {
            //这是等边三角形利用边长求面积
            return Math.Pow(side, 2) * Math.Sqrt(3) / 4;
        }
        public double getPerimeter(double side)
        {
            return 3 * side;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Triangle tri = new Triangle();
            IsoscelesTriangle isoTri = new IsoscelesTriangle();
            EquilateralTriangle eqtri = new EquilateralTriangle();
            double[] sides = new double[] { 3, 4, 5 };
            Console.WriteLine("首先是比较普遍的面积求法\n如用底乘高求，设底为3，高为4：则面积为：{0}\n还可以海伦公式求面积\n如设三边长分别为3，4，5，则面积为：{1}\n还可用两边与其夹角求面积\n如设两边长3，4，夹角为π/2，则面积为：{2}", tri.getArea(3, 4), tri.getArea(sides), tri.getArea(3, 4, Math.PI / 2));
            Console.WriteLine("下面是等腰三角形\n除去继承来的前面的三个方法，还可以利用腰长和底长求面积\n如设腰长为根号2，底长为2，则面积为：{0}", isoTri.getArea(Math.Sqrt(2), 2, 1));
            Console.WriteLine("下面是等边三角形\n除去从等腰三角形和基本三角形继承来的所有方法，即底乘高、海伦公式、腰长与底长求、两边与夹角求，还可以用一个边长求面积\n如设边长为2，则面积为：{0}", eqtri.getArea(2));
            Console.Read();
        }
    }
}
