%%
t=-100:0.0001:+100;
y1t=3*t.^2+t+5;
y2t=t.*sin(5*pi*t).*heaviside(t);
y3t=exp(-t).*sin(10*pi*t)+exp(-t/2).*sin(9*pi*t);
subplot(1,3,1),plot(t,y1t),grid on
axis([-10 10 0 20])
title('第一问函数图像')
subplot(1,3,2),plot(t,y2t),grid on
axis([-1 10 -5 5])
title('第二问函数图像')
subplot(1,3,3),plot(t,y3t),grid on
axis([-8 8 -100 100])
title('第三问函数图像')
%%
syms a x y
a=1
y=sin(a*x^2)
dy=diff(y,'x')
d2y=diff(dy,'x')
xt=int(d2y)
subplot(1,2,1),ezplot(y)
subplot(1,2,2),ezplot(d2y)

