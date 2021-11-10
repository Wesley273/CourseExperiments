%%
%��7-1��1��
omega=logspace(-3,1,500);
h=1./(1+sqrt(-1)*10*omega);
figure;
subplot(2,1,1)
semilogx(omega,20*log10(abs(h)));%����x��Ϊ�����̶ȣ�y��Ϊ���Կ̶ȵİ����ͼ
grid on;title('������Ƶ����')
subplot(2,1,2)
semilogx(omega,angle(h));
grid on;title('������Ƶ����')
%%
%��7-1��2��
w=0:0.02:100;
hw=abs(sqrt(-1).*w./(1-w.^2+sqrt(-1)*w));
semilogx(w,hw);
xlabel('Frequency in rad/sec-log scale');
ylabel('Magnitude of Vout/Vin');
grid on;
%%
%��7-2
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
title('�����������ź�')
N=500;
k=-N:N;
W=pi*k/(N*dt);
Fw=dt*ft*exp(-1i*t1'*W);%���þ�����㸵��Ҷ������
subplot(2,2,2)
plot(W,abs(Fw)),grid on
axis([-10 10 -0.2 1.1*pi])
xlabel('\omega'),ylabel('F(w)');
title('�����������źŵ�Ƶ��')
Ts=2;%�������
t2=-4:Ts:4;
fst=((E*(1+cos(pi*t2/tau))/2)).*(heaviside(t2+pi)-heaviside(t2-pi));%���źų���
subplot(2,2,3)
plot(t1,ft,':'),hold on
stem(t2,fst),grid on
axis([-4 4 -0.1 1.1])
xlabel('Time(sec)'),ylabel('f_s(t)');
title('��������ź�'),hold off
Fsw=Ts*fst*exp(-1i*t2'*W);
subplot(224)
plot(W ,abs(Fsw)),grid on
axis([-10 10 -0.2 1.1*pi])
xlabel('\omega'),ylabel('F_s(w)');
title('��������źŵ�Ƶ��')
%%
%�����źŵ��ؽ�
wm=2;
wc=1.2*wm;
Ts=1;
n=-100:100;
nTs=n*Ts;%��-100��100��1Ϊ���������������ʵ���ϲ���Ҫ��˴�ķ�Χ
E=1;
tau=pi;
fs=(E*(1+cos(pi*nTs/tau))/2).*(heaviside(nTs+pi)-heaviside(nTs-pi));%�����õ��ĺ���
t=-4:0.1:4;
ft=fs*Ts*wc/pi*sinc((wc/pi)*(ones(length(nTs),1)*t-nTs'*ones(1,length(t))));%�����ع���Ҫע�����˷������壬���㹫ʽΪ�α�P182��3-164
t1=-4:0.1:4;
f1=(E*(1+cos(pi*t1/tau))/2).*(heaviside(t1+pi)-heaviside(t1-pi));%ԭ����
subplot(3,1,1)
plot(t1,ft,':'),hold on
stem(nTs,fs),grid on
axis([-4 4 -0.1 1.1])
xlabel('nTs'),ylabel('f(nTs)');
title('�������Ts=1ʱ�ĳ����ź�f(nTs)')
hold off
subplot(3,1,2)
plot(t,ft),grid on
axis([-4 4 -0.1 1.1])
xlabel('t'),ylabel('f(t)')
title('��f(nTs)�ź��ؽ��õ������������ź�')
error=abs(ft-f1);subplot(3,1,3)
plot(t,error),grid on
xlabel('t'),ylabel('error(t)');
title('�ؽ��ź���ԭ�����������źŵľ������')
%%
%��������
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
Xw=fftshift(abs(fft(xt,512)));%fftshift�Ὣ0Ƶ�ʷ����Ƶ�����ͼ���룬�������ڹ۲�
Yw=fftshift(abs(fft(yt,512)));
ww=-256:255;
ww=ww*Fs/512;
figure,subplot(2,1,1),plot(t,xt),title('�����źŲ���')
subplot(2,1,2),plot(ww,Xw),title('�����ź�Ƶ��')
figure,subplot(2,1,1),plot(t,yt),title('�ѵ��źŲ���')
subplot(2,1,2),plot(ww,Yw),title('�ѵ��ź�Ƶ��')
y1t=yt.*xct;
figure,subplot(2,1,1),plot(t,y1t),title('��������м��źŲ���')
Y1w=fftshift(abs(fft(y1t,512)));
subplot(2,1,2),plot(ww,Y1w),title('��������м��ź�Ƶ��')
h=fir1(20,40/200,hamming(21));%������һ��20�׵�ͨFIR�˲���
figure,freqz(h,1),title('�˲���Ƶ������')
y2t=filter(h,1,y1t);
Y2w=fftshift(abs(fft(y2t,512)));
figure,subplot(2,1,1),plot(t,y2t),title('����źŲ���')
subplot(2,1,2),plot(ww,Y2w),title('����ź�Ƶ��')
%%
%���ߴ��źŲ���
t=0:0.01:4.2;
t1=0:0.01:2.1;
t2=0:0.01:2.09;
f=ones(size(t)).*(t<0.5);%������һ���ź���
wc=100;
fa=f.*cos(wc*t);%����һ���ز��ź�
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