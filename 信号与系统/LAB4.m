%%
%ʹ��step���
t=0:0.001:40;
sys=tf([1,3,7],[1,4,6,4,1]);
g=step(sys,t);
plot(t,g),grid on;
title('step�����Ծ��Ӧ')
xlabel('Time(sec)')
ylabel('g(t)')
%%
%ʹ��lsim���
t=0:0.001:40;
sys=tf([1,3,7],[1,4,6,4,1]);
u=heaviside(t);%���ﶨ����һ���ź���
g=lsim(sys,u,t,0);%gΪ���ʼ�����¶�����u����Ӧ
plot(t,g),grid on;
title('lsim�����Ծ��Ӧ')
xlabel('Time(sec)')
ylabel('g(t)')
%%
t=0:0.0001:40;
sys=tf([1,1,1],[1,2,1]);
xt=cos(t).*heaviside(t);
g=lsim(sys,xt,t,0);%gΪ���ʼ�����¶�����xt����Ӧ
plot(t,g),grid on;
title('��״̬��Ӧ')
xlabel('Time(sec)')
ylabel('g(t)')
%%
n=0:100;
x=sin(n*pi/4).*heaviside(n);
a=1;
b=[1,-0.5];
z=filter(b,a,x);
subplot(2,1,1),stem(x);title('��������')
subplot(2,1,2),stem(z);title('�������')