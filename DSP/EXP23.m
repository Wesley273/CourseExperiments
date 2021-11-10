%%
op=0.2*pi; rp=1;
os=0.3*pi; as=16;
figure;
[b,a]=afd_butt(op,os,rp,as);
[H,w]=freqs(b,a); h=impulse(b,a); t=0:length(h)-1;
subplot(221),plot(w/(2*pi),abs(H));title('巴特幅频特性');xlabel('Hz');
subplot(222),plot(w/(2*pi),angle(H));title('巴特相频特性');xlabel('Hz');
subplot(223),plot(w/(2*pi),20*log10(abs(H)/max(abs(H))));title('巴特幅度分贝表示 ');xlabel('Hz');
subplot(224),plot(t,h);title('巴特单位冲激响应h(t)');xlabel('sec');

figure;
[b,a]=afd_chb1(op,os,rp,as);
[H,w]=freqs(b,a); h=impulse(b,a); t=0:length(h)-1;
subplot(221),plot(w/(2*pi),abs(H));title('切比1幅频特性');xlabel('Hz');
subplot(222),plot(w/(2*pi),angle(H));title('切比1相频特性');xlabel('Hz');
subplot(223),plot(w/(2*pi),20*log10(abs(H)/max(abs(H))));title('切比1幅度分贝表示 ');xlabel('Hz');
subplot(224),plot(t,h);title('切比1单位冲激响应h(t)');xlabel('sec');

figure;
[b,a]=afd_chb2(op,os,rp,as);
[H,w]=freqs(b,a); h=impulse(b,a); t=0:length(h)-1;
subplot(221),plot(w/(2*pi),abs(H));title('切比2幅频特性');xlabel('Hz');
subplot(222),plot(w/(2*pi),angle(H));title('切比2相频特性');xlabel('Hz');
subplot(223),plot(w/(2*pi),20*log10(abs(H)/max(abs(H))));title('切比2幅度分贝表示 ');xlabel('Hz');
subplot(224),plot(t,h);title('切比2单位冲激响应h(t)');xlabel('sec');

figure;
[b,a]=afd_elip(op,os,rp,as);
[H,w]=freqs(b,a); h=impulse(b,a); t=0:length(h)-1;
subplot(221),plot(w/(2*pi),abs(H));title('椭圆幅频特性');xlabel('Hz');
subplot(222),plot(w/(2*pi),angle(H));title('椭圆相频特性');xlabel('Hz');
subplot(223),plot(w/(2*pi),20*log10(abs(H)/max(abs(H))));title('椭圆幅度分贝表示 ');xlabel('Hz');
subplot(224),plot(t,h);title('椭圆单位冲激响应h(t)');xlabel('sec');