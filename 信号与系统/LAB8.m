%%
clc;clear all;close all;
times=0.5;%ѹ��/��չ����,����1Ϊѹ����С��1Ϊ��չ
[x,fs]=audioread('C:\Users\Wesley\Desktop\music.wav');
fs=fs*times;%�����ʳ�ѹ��/��չ����
x=x(:,1);
n=length(x);
dt=1/fs;
time=(0:n-1)*dt;
subplot(3,1,1);
plot(time,x),grid on
title('�����ź�ʱ����')
xlabel('ʱ��');
axis([0,max(time),min(x),max(x)]);
fw=fft(x,fs);%fwΪfs����ɢ����Ҷ�任
n=length(fw);%��ȡfw�ĵ���
w=(0:1:n-1);
angf=angle(fw);
subplot(3,1,2);
plot(w,abs(fw)),grid on
title('�����źŷ�����');
xlabel('Ƶ��');ylabel('����')
subplot(3,1,3);
plot(w,angle(fw)),grid on
title('�����ź���λ��')
xlabel('Ƶ��'),ylabel('��λ');
axis([0,max(w),min(angf),max(angf)]);
%%
RGB = imread('C:\Users\Wesley\Desktop\pic.jpg'); %���� jpg ͼ���ļ��� RGB ��
I = rgb2gray(RGB);%ת��Ϊ�Ҷ�ͼ�񣬷���Ƶ�����
F=fft2(I); % ���ٸ���Ҷ�任��fft2 Ϊ��ά����Ҷ�任����
S=log(abs(F)+1); % ��F��ģ������
F2=fftshift(F);%��Ƶ���������м�
S1=log(abs(F2)+1); % ��F2��ģ������
fr=ifft2(ifftshift(F2));%�渵��Ҷ�任
ang=log(angle(F2)*180/pi+1);%��ø���Ҷ�任����λ��
fa=ifft2(exp(1i*(ang)));%��λ���ع�
fb=ifft2(abs(F));
subplot(2,3,1);imshow(RGB,[]);title('ԭͼ��');
subplot(2,3,2);imshow(S1,[]); title('Ƶ�ƺ�ķ�Ƶ����')
subplot(2,3,3);imshow(ang,[]); title('����Ҷ�任��Ƶ����')
subplot(2,3,4);imshow(fr,[]); title('����Ҷ��任�ָ���ͼ��')
subplot(2,3,5);imshow(abs(log(fb)),[]); title('�����׻ָ�')
subplot(2,3,6);imshow(log(fa),[]); title('��λ�׻ָ�')