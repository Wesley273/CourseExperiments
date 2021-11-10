using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace StudentsManagement
{
    public partial class Update : Form
    {
        //这两个成员是public static的，使之可以在其他窗口被直接用类访问
        public static string path;
        public static string info;
        public Update()
        {
            InitializeComponent();
        }

        private void Browser_Load(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void dateTimePicker1_ValueChanged(object sender, EventArgs e)
        {

        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {

        }

        private void pictureBox1_Click_1(object sender, EventArgs e)
        {

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }


        private void timer1_Tick(object sender, EventArgs e)
        {
            toolStripStatusLabelNowTime.Text = string.Format("现在时间：{0}",System.DateTime.Now.ToLongTimeString());
            toolStripProgressBar1.PerformStep();
            if (toolStripProgressBar1.Value == toolStripProgressBar1.Maximum)
            {
                toolStripProgressBar1.Value = toolStripProgressBar1.Minimum;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Environment.Exit(0);
        }

        private void myOpenPictureBtn_Click_1(object sender, EventArgs e)
        {
            this.openFileDialog1.InitialDirectory = @"C:\Users\Wesley\Desktop";
            this.openFileDialog1.Filter = "bmp 文件(*.bmp)|*.bmp|gif 文件(*.gif)|*.gif|jpg 文件(*.jpg)|*.jpg";
            //设置为3，默认打开对话框中显示的文件是所有文件
            this.openFileDialog1.FilterIndex = 3;
            this.openFileDialog1.RestoreDirectory = true;
            this.openFileDialog1.Title = "选择学生照片";
            if (this.openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                path = this.openFileDialog1.FileName;
            }
            pictureBox1.Image = Image.FromFile(path);
        }

        private void listBox1_SelectedIndexChanged_1(object sender, EventArgs e)
        {

        }

        private void listBox1_MouseDoubleClick_1(object sender, MouseEventArgs e)
        {
            listBox1.Items.Clear();
            listBox1.Items.Add("2020年度校优秀团员");
            listBox1.Items.Add("2020年度国家奖学金");
            listBox1.Items.Insert(2, "2020年度校运动会10000米游泳冠军");
            listBox1.SelectedIndex = 1;
            string msg = string.Format("已填加奖惩记录{0}条", listBox1.Items.Count);
            MessageBox.Show(msg, "提示");
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            //先对姓名和学号进行输出
            //\r为移至行首，\n为换行
            info = "学号：" + NumBox.Text + "\r\n";
            info += "姓名：" + NameBox.Text + "\r\n";
            //对性别识别输出
            foreach (Control control in GroupSex.Controls)
            {
                if ((control as RadioButton).Checked)
                {
                    info += "性别：" + (control as RadioButton).Text;
                }
            }
            //对爱好识别输出
            info += "\r\n\r\n爱好：";
            foreach (Control control in GroupFavor.Controls)
            {
                if ((control as CheckBox).Checked)
                {
                    info += (control as CheckBox).Text + " ";
                }
            }
            //对出生日期与专业识别显示
            info += "\r\n\r\n出生日期：" + dateTimePicker1.Text;
            info += "\r\n专    业：" + comboBox2.Text;
            //对获奖情况识别显示
            info += "\r\n\r\n获奖情况：";
            int count = 1;
            foreach (string i in listBox1.Items)
            {
                if (i != "")
                {
                    info += "\r\n " + count.ToString() + "." + i;
                    count++;
                }
            }
            //打开预览窗口
            Welcome.browserForm = new Browser();
            Welcome.browserForm.Show();
            //这里使用透明度调为100%，实现窗口隐藏，其实用Hide更好
            this.Opacity = 0;
        }
    }
}
