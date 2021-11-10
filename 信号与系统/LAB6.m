%%
%例7-1（1）
omega=logspace(-3,1,500);
h=1./(1+sqrt(-1)*10*omega);
figure;
subplot(2,1,1)
semilogx(omega,20*log10(abs(h)));%绘制x轴为对数刻度，y轴为线性刻度的半对数图
grid on;title('对数幅频特性')
subplot(2,1,2)
semilogx(omega,angle(h));
grid on;title('对数相频特性')
%%
%例7-1（2）
w=0:0.02:100;
hw=abs(sqrt(-1).*w./(1-w.^2+sqrt(-1)*w));
semilogx(w,hw);
xlabel('Frequency in rad/sec-log scale');
ylabel('Magnitude of Vout/Vin');
grid on;
%%
%例7-2
s=1; 
E=1;
tau=pi;
dt=0.1;
t1=-4:dt:4;
ft=(E*(1+cos(pi*t1/tau))/2).*(heaviside(t1+pi)-heaviside(t1-pi));
subplot(2,2,1)
plot(t1,ft),grid on
axis([-4 4 -0.1 1.1])
xlabel('Time(ec)'),ylabel('f(t)');
title('升余弦脉冲信号')
N=500;
k=-N:N;
W=pi*k/(N*dt);
Fw=dt*ft*exp(-1i*t1'*W);%利用矩阵计算傅里叶级数和
subplot(2,2,2)
plot(W,abs(Fw)),grid on
axis([-10 10 -0.2 1.1*pi])
xlabel('\omega'),ylabel('F(w)');
title('升余弦脉冲信号的频谱')
Ts=2;%抽样间隔
t2=-4:Ts:4;
fst=((E*(1+cos(pi*t2/tau))/2)).*(heaviside(t2+pi)-heaviside(t2-pi));%对信号抽样
subplot(2,2,3)
plot(t1,ft,':'),hold on
stem(t2,fst),grid on
axis([-4 4 -0.1 1.1])
xlabel('Time(sec)'),ylabel('f_s(t)');
title('抽样后的信号'),hold off
Fsw=Ts*fst*exp(-1i*t2'*W);
subplot(224)
plot(W ,abs(Fsw)),grid on
axis([-10 10 -0.2 1.1*pi])
xlabel('\omega'),ylabel('F_s(w)');
title('抽样后的信号的频谱')
%%
%抽样信号的重建
wm=2;
wc=1.2*wm;
Ts=1;
n=-100:100;
nTs=n*Ts;%对-100到100以1为间隔抽样，本题中实质上不需要如此大的范围
E=1;
tau=pi;
fs=(E*(1+cos(pi*nTs/tau))/2).*(heaviside(nTs+pi)-heaviside(nTs-pi));%抽样得到的函数
t=-4:0.1:4;
ft=fs*Ts*wc/pi*sinc((wc/pi)*(ones(length(nTs),1)*t-nTs'*ones(1,length(t))));%进行重构，要注意矩阵乘法的意义，计算公式为课本P182的3-164
t1=-4:0.1:4;
f1=(E*(1+cos(pi*t1/tau))/2).*(heaviside(t1+pi)-heaviside(t1-pi));%原函数
subplot(3,1,1)
plot(t1,ft,':'),hold on
stem(nTs,fs),grid on
axis([-4 4 -0.1 1.1])
xlabel('nTs'),ylabel('f(nTs)');
title('抽样间隔Ts=1时的抽样信号f(nTs)')
hold off
subplot(3,1,2)
plot(t,ft),grid on
axis([-4 4 -0.1 1.1])
xlabel('t'),ylabel('f(t)')
title('由f(nTs)信号重建得到升余弦脉冲信号')
error=abs(ft-f1);subplot(3,1,3)
plot(t,error),grid on
xlabel('t'),ylabel('error(t)');
title('重建信号与原升余弦脉冲信号的绝对误差')
%%
%调制与解调
clear clc
fs=40;
Fs=400;
Fc=40;
N=400;
n=0:N;
t=n/Fs;
xt=cos(2*pi*5*t);
xct=cos(2*pi*Fc*t);
yt=xt.*xct;
Xw=fftshift(abs(fft(xt,512)));%fftshift会将0频率分量移到坐标图中央，更加易于观察
Yw=fftshift(abs(fft(yt,512)));
ww=-256:255;
ww=ww*Fs/512;
figure,subplot(2,1,1),plot(t,xt),title('调制信号波形')
subplot(2,1,2),plot(ww,Xw),title('调制信号频谱')
figure,subplot(2,1,1),plot(t,yt),title('已调信号波形')
subplot(2,1,2),plot(ww,Yw),title('已调信号频谱')
y1t=yt.*xct;
figure,subplot(2,1,1),plot(t,y1t),title('解调过程中间信号波形')
Y1w=fftshift(abs(fft(y1t,512)));
subplot(2,1,2),plot(ww,Y1w),title('解调过程中间信号频谱')
h=fir1(20,40/200,hamming(21));%构造了一个20阶低通FIR滤波器
figure,freqz(h,1),title('滤波器频率特性')
y2t=filter(h,1,y1t);
Y2w=fftshift(abs(fft(y2t,512)));
figure,subplot(2,1,1),plot(t,y2t),title('解调信号波形')
subplot(2,1,2),plot(ww,Y2w),title('解调信号频谱')
%%
%单边带信号产生
t=0:0.01:4.2;
t1=0:0.01:2.1;
t2=0:0.01:2.09;
f=ones(size(t)).*(t<0.5);%构造了一个门函数
wc=100;
fa=f.*cos(wc*t);%产生一个载波信号
ga=fft(fa);
h1=[1i*ones(size(t1))];
h2=[-1i*ones(size(t2))];
h=[h1 h2];
g=fft(f).*h;
fb=ifft(g);
fc=fb.*sin(wc*t);
gc=fft(fc);
y1=ga+gc;
y2=ga-gc;
figure
subplot(4,1,1),plot(abs(ga));
subplot(4,1,2),plot(abs(gc));
subplot(4,1,3),plot(abs(y1));
subplot(4,1,4),plot(abs(y2));