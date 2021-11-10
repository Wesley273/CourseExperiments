%%
%使用step求解
t=0:0.001:40;
sys=tf([1,3,7],[1,4,6,4,1]);
g=step(sys,t);
plot(t,g),grid on;
title('step计算阶跃响应')
xlabel('Time(sec)')
ylabel('g(t)')
%%
%使用lsim求解
t=0:0.001:40;
sys=tf([1,3,7],[1,4,6,4,1]);
u=heaviside(t);%这里定义了一个门函数
g=lsim(sys,u,t,0);%g为零初始条件下对向量u的响应
plot(t,g),grid on;
title('lsim计算阶跃响应')
xlabel('Time(sec)')
ylabel('g(t)')
%%
t=0:0.0001:40;
sys=tf([1,1,1],[1,2,1]);
xt=cos(t).*heaviside(t);
g=lsim(sys,xt,t,0);%g为零初始条件下对向量xt的响应
plot(t,g),grid on;
title('零状态响应')
xlabel('Time(sec)')
ylabel('g(t)')
%%
n=0:100;
x=sin(n*pi/4).*heaviside(n);
a=1;
b=[1,-0.5];
z=filter(b,a,x);
subplot(2,1,1),stem(x);title('输入序列')
subplot(2,1,2),stem(z);title('输出序列')