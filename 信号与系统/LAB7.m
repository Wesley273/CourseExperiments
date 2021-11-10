%%
syms t s
f1=t*exp(-2*t);
fs=laplace(f1)
%%
syms s t
fs=(s^2+4*s+5)/(s^2+3*s+2);
f=ilaplace(fs)
%%
syms s t
z1=1;
z2=1/s;
z3=1/(1+s);%这是Z1与Z2的并联结果
z4=1+1/s;%这是Z1与Z2串联的结果
fs=z3/(z3+z4);
fs=simplify(fs)
f=ilaplace(fs);
f=simplify(f)
%%
syms s t
fs=(30*(s+2)*(s-1))/((s+1)*(s-2)*(s+3));
ys=1*fs;
ft=ilaplace(ys)*heaviside(t)
figure
num=[0 30 30 -60];
den=[1 2 -5 -6];
zplane(num,den)
%%
syms w L C r
fs=1/((1/r+1i*w*L)+(1/1i*w*C))
r=1;
L=1;
C=4;
PW=atan(imag(fs)/real(fs))
ff=abs(fs)
