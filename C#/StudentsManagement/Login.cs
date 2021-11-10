using System;
using System.Windows.Forms;

namespace StudentsManagement
{
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private void loginForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textUserName.Text == string.Empty || textPassword.Text == string.Empty)
            {
                MessageBox.Show("用户名和密码均不能为空！", "提示");
            }
            else
            {
                if (textUserName.Text == "wesley" && textPassword.Text == "123")
                {
                    Welcome.updateForm.Show();
                    this.Hide();
                }
                else
                {
                    //DialogResult是MessageBox.Show()的返回值，它是一个枚举类型
                    DialogResult result = MessageBox.Show("用户名或密码不正确，要重新输入吗？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Information);
                    if (result == DialogResult.OK)
                    {
                        textUserName.Clear();
                        textPassword.Clear();
                    }
                }
            }
        }

        private void clickToCancel_Click(object sender, EventArgs e)
        {
            textUserName.Clear();
            textPassword.Clear();
        }
    }
}
