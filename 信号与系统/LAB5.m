%%
t=-11:0.001:11;
T0=5;
tau=2;
E=1;
ft=E*rectpuls(t,2)+E*rectpuls(t-T0,2)+E*rectpuls(t+T0,2)+E*rectpuls(t-2*T0,2)+E*rectpuls(t+2*T0,2);
subplot(2,2,1),plot(t,ft),grid on
axis([-8,8,0,1.5])
title('矩形脉冲信号')
n_max=[3 39 399];
N=length(n_max);
for k=1:N
    n=0:1:(n_max(k));
    b=(2*E./(pi.*n)).*sin(n*pi*tau/T0);
    b(1)=E*tau/T0;
    cn=cos(2*pi*n'*t/T0);%cn的每一行中n取值相等，每一列中t的取值相等
    x=b*cn;%此处为矩阵相乘
    subplot(2,2,k+1),plot(t,x),grid on
    axis([-8,8,0,1.5])
    title(['最大谐波次数=',num2str(n_max(k)+1)])
end
%%
%上题频谱绘制
n=-30:30;
T0=5;
w0=2*pi/T0;
tau=2;
E=1;
x=n*tau/T0;
fn=tau/T0*sinc(x);
stem(n,fn),grid on
title('\tau=1,T0=5')
%%
%在一个周期内随机生成信号并傅里叶级数展开
Um=1;
T=0.02;
w=2*pi/T;
ht=0;
num_points=200;%设定信号原样本点的个数
n_max=100;%设定最大谐波次数
t=linspace(-T/2,T/2,num_points);%在周期内等间隔生成num_points个点
y=Um*abs(sin(2*w*t)).*(t>0);
n=randn(38,1);
n=[0;n;0];%首尾各插入一个0
n = interp1([0:39],n,linspace(0,39,num_points),'linear');%使用分段线性插值
y=y+0.4*n;
subplot(3,1,1),plot(t,y),grid on,title('随机信号波形(抽样次数=200)')
for i=1:n_max
y1=y.*cos(i*w*t);
y2=y.*sin(i*w*t);
a(i)=2/T*trapz(t,y1);
b(i)=2/T*trapz(t,y2);
end
a0=1/T*trapz(t,y);
ht=a0;
ht1=a0;
for i=1:n_max
ht=ht+sqrt(a(i)^2+b(i)^2).*cos(i*w*t-atan2(b(i),a(i)));%这里要注意使用atan2
ht1=ht1+a(i)*cos(i*w*t)+b(i)*sin(i*w*t);
subplot(3,1,2),plot(t,ht),grid on,title(['最大谐波次数=',num2str(i),'（余弦形式）'])
subplot(3,1,3),plot(t,ht1),grid on,title(['最大谐波次数=',num2str(i),'（正余弦形式）'])
pause(0.005);
end
%%
%符号运算解傅里叶正变换
syms t;
ft=exp(-abs(t))+3*dirac(t);
fw=fourier(ft)
PW=atan(imag(fw)/real(fw))
subplot(1,3,1),ezplot(ft)
grid on,title('时域图')
subplot(1,3,2),ezplot(abs(fw))
grid on,title('幅频特性')
subplot(1,3,3),ezplot(PW)
grid on,title('相频特性')
%%
%符号运算解傅里叶反变换
syms w;
fw=3/(3*sqrt(-1)*w+2+w^2);
ft=ifourier(fw)
ezplot(ft)
grid on,title('时域图')