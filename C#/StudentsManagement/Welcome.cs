using System;
using System.Windows.Forms;

namespace StudentsManagement
{
    public partial class Welcome : Form
    {
        public static Login loginForm;
        public static Update updateForm;
        public static Browser browserForm;
        public Welcome()
        {
            //这里是构造函数，实例化出来了这两个对象
            InitializeComponent();
            loginForm = new Login();
            updateForm = new Update();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void linkLabel1_LinkClicked_1(object sender, LinkLabelLinkClickedEventArgs e)
        {
            linkLabel1.LinkVisited = true;
            loginForm.Show();
            this.Hide();
        }
    }
}
