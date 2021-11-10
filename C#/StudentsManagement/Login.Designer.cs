namespace StudentsManagement
{
    partial class Login
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.textUserName = new System.Windows.Forms.TextBox();
            this.textPassword = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.clickToLogin = new System.Windows.Forms.Button();
            this.clickToCancel = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(167, 103);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(124, 28);
            this.label1.TabIndex = 0;
            this.label1.Text = "用户名：";
            // 
            // textUserName
            // 
            this.textUserName.Location = new System.Drawing.Point(322, 103);
            this.textUserName.Name = "textUserName";
            this.textUserName.Size = new System.Drawing.Size(236, 28);
            this.textUserName.TabIndex = 1;
            // 
            // textPassword
            // 
            this.textPassword.Location = new System.Drawing.Point(322, 206);
            this.textPassword.Name = "textPassword";
            this.textPassword.PasswordChar = '*';
            this.textPassword.Size = new System.Drawing.Size(236, 28);
            this.textPassword.TabIndex = 3;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("宋体", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(167, 206);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(124, 28);
            this.label2.TabIndex = 2;
            this.label2.Text = "密  码：";
            // 
            // clickToLogin
            // 
            this.clickToLogin.Location = new System.Drawing.Point(170, 352);
            this.clickToLogin.Name = "clickToLogin";
            this.clickToLogin.Size = new System.Drawing.Size(152, 56);
            this.clickToLogin.TabIndex = 4;
            this.clickToLogin.Text = "登录";
            this.clickToLogin.UseVisualStyleBackColor = true;
            this.clickToLogin.Click += new System.EventHandler(this.button1_Click);
            // 
            // clickToCancel
            // 
            this.clickToCancel.Location = new System.Drawing.Point(476, 352);
            this.clickToCancel.Name = "clickToCancel";
            this.clickToCancel.Size = new System.Drawing.Size(152, 56);
            this.clickToCancel.TabIndex = 5;
            this.clickToCancel.Text = "重写";
            this.clickToCancel.UseVisualStyleBackColor = true;
            this.clickToCancel.Click += new System.EventHandler(this.clickToCancel_Click);
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(144F, 144F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Dpi;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.clickToCancel);
            this.Controls.Add(this.clickToLogin);
            this.Controls.Add(this.textPassword);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.textUserName);
            this.Controls.Add(this.label1);
            this.Name = "Login";
            this.Text = "登录";
            this.Load += new System.EventHandler(this.loginForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textUserName;
        private System.Windows.Forms.TextBox textPassword;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button clickToLogin;
        private System.Windows.Forms.Button clickToCancel;
    }
}