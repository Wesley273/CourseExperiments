%%
dt=0.01
n1=0:dt:1;
n2=0:dt:2;%��t1,t2��ֵʱ�����˶�����
n3=0:dt:3; %����ѧ֪ʶ����֪�������������о������0��3��ֵ
x1t=2*rectpuls((n1-0.5),1);
x2t=rectpuls((n2-1),2);
y=conv(x1t,x2t)*dt;%�������������ɢ��
subplot(2,2,1),plot(n1,x1t),grid on
axis([0 5 0 3])
title('x1(t)')
subplot(2,2,2),plot(n2,x2t),grid on
axis([0 5 0 3])
title('x2(t)')
subplot(2,2,3),plot(n3,y),grid on
axis([0 5 0 3])
title('y(t)')
%%
dt=1
n1=0:dt:2;
n2=0:dt:3;%��t1,t2��ֵʱ�����˶�����
n3=0:dt:5; %����ѧ֪ʶ����֪�������������о������0��5��ֵ
xn=[2,1,2];
hn=[0,1,2,3];
yn=conv(xn,hn)*dt;
subplot(2,2,1),stem(n1,xn),grid on
axis([-1 5 0 4])
title('x(n)')
subplot(2,2,2),stem(n2,hn),grid on
axis([-1 5 0 4])
title('h(n)')
subplot(2,2,3),stem(n3,yn),grid on
axis([-1 6 0 20])
title('y(n)')
