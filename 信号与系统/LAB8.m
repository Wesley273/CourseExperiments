%%
clc;clear all;close all;
times=0.5;%压缩/扩展因子,大于1为压缩，小于1为扩展
[x,fs]=audioread('C:\Users\Wesley\Desktop\music.wav');
fs=fs*times;%采样率乘压缩/扩展因子
x=x(:,1);
n=length(x);
dt=1/fs;
time=(0:n-1)*dt;
subplot(3,1,1);
plot(time,x),grid on
title('声音信号时域波形')
xlabel('时间');
axis([0,max(time),min(x),max(x)]);
fw=fft(x,fs);%fw为fs的离散傅里叶变换
n=length(fw);%获取fw的点数
w=(0:1:n-1);
angf=angle(fw);
subplot(3,1,2);
plot(w,abs(fw)),grid on
title('声音信号幅度谱');
xlabel('频率');ylabel('幅度')
subplot(3,1,3);
plot(w,angle(fw)),grid on
title('声音信号相位谱')
xlabel('频率'),ylabel('相位');
axis([0,max(w),min(angf),max(angf)]);
%%
RGB = imread('C:\Users\Wesley\Desktop\pic.jpg'); %读入 jpg 图像文件到 RGB 中
I = rgb2gray(RGB);%转换为灰度图像，方便频域分析
F=fft2(I); % 快速傅里叶变换，fft2 为二维傅里叶变换函数
S=log(abs(F)+1); % 求F的模并缩放
F2=fftshift(F);%零频分量置于中间
S1=log(abs(F2)+1); % 求F2的模并缩放
fr=ifft2(ifftshift(F2));%逆傅里叶变换
ang=log(angle(F2)*180/pi+1);%获得傅里叶变换的相位谱
fa=ifft2(exp(1i*(ang)));%相位谱重构
fb=ifft2(abs(F));
subplot(2,3,1);imshow(RGB,[]);title('原图像');
subplot(2,3,2);imshow(S1,[]); title('频移后的幅频特性')
subplot(2,3,3);imshow(ang,[]); title('傅里叶变换相频特性')
subplot(2,3,4);imshow(fr,[]); title('傅里叶逆变换恢复的图像')
subplot(2,3,5);imshow(abs(log(fb)),[]); title('幅度谱恢复')
subplot(2,3,6);imshow(log(fa),[]); title('相位谱恢复')