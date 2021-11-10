%%
t=-10:0.0001:+10;
n=-10:1:10
y1t=exp(-2*abs(t));
y2n=power(0.9,n);
y3n=real(exp(sqrt(-1)*pi*n/3));
subplot(1,3,1),plot(t,y1t),grid on
title('第一问函数图像')
subplot(1,3,2),stem(n,y2n),grid on
title('第二问函数图像')
subplot(1,3,3),stem(n,y3n),grid on
title('第三问函数图像')
%%
t=-1:0.0001:+1;
n=-0:1:4
y2t=1+cos(pi*t);
y3n=exp(n);
subplot(1,2,1),plot(t,y2t),grid on
title('第二问函数图像')
subplot(1,2,2),stem(n,y3n),grid on
axis([0 5 0 100])
title('第三问函数图像')
%%
%sinc
t=-10:0.0001:+10;
y2t=sinc(t);
plot(t,y2t),grid on
title('rectpuls')
axis([-11 11 -0.5 1.2])
%%
%rectpuls
t=-5:0.0001:+5;
y2t=rectpuls(t,1);
plot(t,y2t),grid on
title('rectpuls')
axis([-5 5 -0.5 1.2])
%%
%square
t=-11:0.0001:+11;
y2t=square(t);
plot(t,y2t),grid on
title('square')
axis([-11 11 -2 2])
%%
%tripuls
t=-11:0.0001:+11;
y2t=tripuls(t);
plot(t,y2t),grid on
title('tripuls')
axis([-6 6 -0.2 1.5])
%%
%sawtooth
t=-11:0.0001:+11;
y2t=sawtooth(t);
plot(t,y2t),grid on
title('sawtooth')
axis([-10 10 -5 5])