using System;

namespace LaboratoryManagement
{
    public class Materials
    {
        private string name, user;
        private int total;
        //这里定义了三个属性，每个属性都是可读可写的
        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        public string User
        {
            get { return user; }
            set { user = value; }
        }
        public int Total
        {
            get { return total; }
            set { total = value; }
        }
        //构造函数
        public Materials()
        {
            name = "";
            user = "";
            total = 0;
        }
        public Materials(string name, string user, int total)
        {
            this.name = name;
            this.user = user;
            this.total = total;
        }
        //若不加ref，传入的是一个引用值的副本，本实验中加与不加ref效果是一样的
        public void store(ref Materials material)
        {
            name = material.name;
            user = material.user;
            total = material.total;
        }
        public void show()
        {
            Console.WriteLine("耗材名：{0}，使用者：{1}，用量：{2}", name, user, total);
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            //这里是一个中转的对象，用于将数据存入类数组
            Materials card = new Materials();
            Console.Write("请问总共要记录多少种耗材？");
            string strCounts = Console.ReadLine();
            int counts = int.Parse(strCounts);
            //这里是一个类类型的数组
            Materials[] material = new Materials[counts];
            //建立一个“目录”方便后面排序
            int[] index = new int[counts];
            //类数组内的元素需要手动进行实例化
            for (int i = 0; i < counts; i++)
            {
                material[i] = new Materials();
            }
            for (int i = 0; i < counts; i++)
            {
                Console.Write("请输入耗材名： ");
                card.Name = Console.ReadLine();
                Console.Write("请输入耗材使用者： ");
                card.User = Console.ReadLine();
                Console.Write("请输入这种耗材的用量： ");
                strCounts = Console.ReadLine();
                card.Total = int.Parse(strCounts);
                material[i].store(ref card);
                index[i] = i;
            }
            Console.Write("请选择按什么关键字进行排序(1.按耗材名，2.按使用者，3.按用量)");
            strCounts = Console.ReadLine();
            int choice = int.Parse(strCounts);
            sortMaterials(choice, material, index);
            for (int i = 0; i < counts; i++)
            {
                material[index[i]].show();
            }
            Console.Read();
        }
        static void sortMaterials(int choice, Materials[] material, int[] index)
        {
            switch (choice)
            {
                case 1:
                    //对耗材名进行排序，使用的是冒泡法
                    for (int i = 0; i < index.Length - 1; i++)
                    {
                        for (int m = 0; m < index.Length - i - 1; m++)
                        {
                            if (string.Compare(material[index[m]].Name, material[index[m + 1]].Name) > 0)
                            {
                                int temp = index[m];
                                index[m] = index[m + 1];
                                index[m + 1] = temp;
                            }
                        }

                    }
                    break;
                case 2:
                    //对使用者进行排序，使用的是冒泡法
                    for (int i = 0; i < index.Length - 1; i++)
                    {
                        for (int m = 0; m < index.Length - i - 1; m++)
                        {
                            if (string.Compare(material[index[m]].User, material[index[m + 1]].User, false) > 0)
                            {
                                int temp = index[m];
                                index[m] = index[m + 1];
                                index[m + 1] = temp;
                            }
                        }

                    }
                    break;
                case 3:
                    //对用量进行排序，使用的是冒泡法
                    for (int i = 0; i < index.Length - 1; i++)
                    {
                        for (int m = 0; m < index.Length - i - 1; m++)
                        {
                            if (material[index[m]].Total > material[index[m + 1]].Total)
                            {
                                int temp = index[m];
                                index[m] = index[m + 1];
                                index[m + 1] = temp;
                            }
                        }

                    }
                    break;
                default: break;
            }
        }
    }
}
