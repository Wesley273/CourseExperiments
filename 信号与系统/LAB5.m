%%
t=-11:0.001:11;
T0=5;
tau=2;
E=1;
ft=E*rectpuls(t,2)+E*rectpuls(t-T0,2)+E*rectpuls(t+T0,2)+E*rectpuls(t-2*T0,2)+E*rectpuls(t+2*T0,2);
subplot(2,2,1),plot(t,ft),grid on
axis([-8,8,0,1.5])
title('���������ź�')
n_max=[3 39 399];
N=length(n_max);
for k=1:N
    n=0:1:(n_max(k));
    b=(2*E./(pi.*n)).*sin(n*pi*tau/T0);
    b(1)=E*tau/T0;
    cn=cos(2*pi*n'*t/T0);%cn��ÿһ����nȡֵ��ȣ�ÿһ����t��ȡֵ���
    x=b*cn;%�˴�Ϊ�������
    subplot(2,2,k+1),plot(t,x),grid on
    axis([-8,8,0,1.5])
    title(['���г������=',num2str(n_max(k)+1)])
end
%%
%����Ƶ�׻���
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
%��һ����������������źŲ�����Ҷ����չ��
Um=1;
T=0.02;
w=2*pi/T;
ht=0;
num_points=200;%�趨�ź�ԭ������ĸ���
n_max=100;%�趨���г������
t=linspace(-T/2,T/2,num_points);%�������ڵȼ������num_points����
y=Um*abs(sin(2*w*t)).*(t>0);
n=randn(38,1);
n=[0;n;0];%��β������һ��0
n = interp1([0:39],n,linspace(0,39,num_points),'linear');%ʹ�÷ֶ����Բ�ֵ
y=y+0.4*n;
subplot(3,1,1),plot(t,y),grid on,title('����źŲ���(��������=200)')
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
ht=ht+sqrt(a(i)^2+b(i)^2).*cos(i*w*t-atan2(b(i),a(i)));%����Ҫע��ʹ��atan2
ht1=ht1+a(i)*cos(i*w*t)+b(i)*sin(i*w*t);
subplot(3,1,2),plot(t,ht),grid on,title(['���г������=',num2str(i),'��������ʽ��'])
subplot(3,1,3),plot(t,ht1),grid on,title(['���г������=',num2str(i),'����������ʽ��'])
pause(0.005);
end
%%
%��������⸵��Ҷ���任
syms t;
ft=exp(-abs(t))+3*dirac(t);
fw=fourier(ft)
PW=atan(imag(fw)/real(fw))
subplot(1,3,1),ezplot(ft)
grid on,title('ʱ��ͼ')
subplot(1,3,2),ezplot(abs(fw))
grid on,title('��Ƶ����')
subplot(1,3,3),ezplot(PW)
grid on,title('��Ƶ����')
%%
%��������⸵��Ҷ���任
syms w;
fw=3/(3*sqrt(-1)*w+2+w^2);
ft=ifourier(fw)
ezplot(ft)
grid on,title('ʱ��ͼ')