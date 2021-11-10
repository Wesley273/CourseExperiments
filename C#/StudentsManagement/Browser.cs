using System;
using System.Drawing;
using System.Windows.Forms;

namespace StudentsManagement
{
    public partial class Browser : Form
    {
        public Browser()
        {
            InitializeComponent();
            //这里需要引入System.Drawing包
            if (StudentsManagement.Update.path != null)
            {
                pictureBox1.Image = Image.FromFile(StudentsManagement.Update.path);
            }
            else
            {
                MessageBox.Show("该用户未上传照片", "提示");
            }
            textBox1.Text = StudentsManagement.Update.info;
        }

        private void Update_Load(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }
    }
}
