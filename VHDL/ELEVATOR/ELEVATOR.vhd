LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
ENTITY ELEVATOR IS
  PORT (
    CLK, RST : IN STD_LOGIC;
    UP : IN STD_LOGIC_VECTOR(10 DOWNTO 1);
    DOWN : IN STD_LOGIC_VECTOR(10 DOWNTO 1);
    INSIDE : IN STD_LOGIC_VECTOR(10 DOWNTO 1);
    POSITION : OUT INTEGER RANGE 1 TO 10;
    DOOR_LIGHT : OUT STD_LOGIC
  );
END ELEVATOR;

ARCHITECTURE BHV OF ELEVATOR IS
  TYPE STATES IS(UPING, DOWNING, STOPPING, OPENDOOR);
  SIGNAL UPREC1 : STD_LOGIC_VECTOR(10 DOWNTO 1) := "0000000000";
  SIGNAL DOWNREC1 : STD_LOGIC_VECTOR(10 DOWNTO 1) := "0000000000";
  SIGNAL PST : STATES;
  SIGNAL WAITT : STATES;
BEGIN
  PROCESS (UP, INSIDE)
  BEGIN
    UPREC1 <= UP OR INSIDE;
  END PROCESS;
  PROCESS (DOWN, INSIDE)
  BEGIN
    DOWNREC1 <= DOWN OR INSIDE;
  END PROCESS;
  PROCESS (CLK, RST)
    VARIABLE UPREC2 : STD_LOGIC_VECTOR(10 DOWNTO 1) := "0000000000";
    VARIABLE DOWNREC2 : STD_LOGIC_VECTOR(10 DOWNTO 1) := "0000000000";
    VARIABLE CMP : STD_LOGIC_VECTOR(10 DOWNTO 1) := "0000000000";
    VARIABLE LEVEL : INTEGER RANGE 1 TO 10 := 1;
    VARIABLE CNT, JUDGE1 : INTEGER RANGE 0 TO 10 := 0;
    VARIABLE JUDGE2 : INTEGER RANGE 0 TO 11 := 0;
  BEGIN
    IF RST = '1' THEN
      PST <= STOPPING;
      POSITION <= 1;
      DOOR_LIGHT <= '0'; --RST�ߵ�ƽΪ��λ����
    ELSIF RISING_EDGE(CLK) THEN
      POSITION <= LEVEL;

      CASE PST IS
        WHEN STOPPING =>
          WAITT <= STOPPING;
          CMP := UPREC1 OR DOWNREC1;
          JUDGE1 := 0;
          L0 : FOR I IN 10 DOWNTO 1 LOOP
            IF (CMP(I) = '1') THEN
              JUDGE1 := I;
              EXIT;
            ELSE
              NEXT L0;
            END IF;
          END LOOP;

          IF (JUDGE1 = 0) THEN
            PST <= STOPPING;
          ELSIF (JUDGE1 = LEVEL) THEN
            PST <= OPENDOOR;
          ELSIF (JUDGE1 < LEVEL) THEN
            PST <= DOWNING;
          ELSE
            PST <= UPING;
          END IF;--�ж�Ӧ������/����

        WHEN UPING =>
          WAITT <= UPING;
          UPREC2 := UPREC1;
          L11 : FOR I IN 10 DOWNTO 1 LOOP
            IF (UPREC1(I) = '1' OR DOWNREC1(I) = '1') THEN
              JUDGE1 := I;
              EXIT;
            ELSE
              NEXT L11;
            END IF;
          END LOOP;--L11��Ϊ���ж�����������¥��
          L12 : FOR I IN 10 DOWNTO 1 LOOP
            IF (UPREC1(I) = '1' AND I = 10) THEN
              JUDGE2 := I;
              EXIT;
            ELSIF (UPREC1(I) = '1') THEN
              JUDGE2 := I + 1;
              UPREC2(10 DOWNTO (JUDGE2 + 1)) := DOWNREC1(10 DOWNTO (JUDGE2 + 1));
              EXIT;
            ELSE
              NEXT L12;
            END IF;
          END LOOP;--L12��Ϊ���ж���������������¥�����һ��

          LEVEL := LEVEL + 1;
          IF (UPREC2(LEVEL) = '1') THEN--������������Ҳ������Ӧ�µ�����
            PST <= OPENDOOR;
          ELSE
            IF (LEVEL >= JUDGE1) THEN
              PST <= STOPPING;
              LEVEL := LEVEL - 1;--������Ϊ�����������һ��Ӱ��
            ELSE
              PST <= UPING;
            END IF;
          END IF;

        WHEN DOWNING =>
          WAITT <= DOWNING;
          DOWNREC2 := DOWNREC1 OR UPREC1;
          L21 : FOR I IN 1 TO 10 LOOP
            IF (DOWNREC2(I) = '1') THEN
              JUDGE1 := I;
              EXIT;
            ELSE
              NEXT L21;
            END IF;
          END LOOP;--L21��������L11ǡ���෴
          L22 : FOR I IN 1 TO 10 LOOP
            IF (DOWNREC1(I) = '1' AND I = 1) THEN
              JUDGE2 := I;
              EXIT;
            ELSIF (DOWNREC1(I) = '1') THEN
              JUDGE2 := I;
              DOWNREC2((JUDGE2 - 1) DOWNTO 1) := UPREC1((JUDGE2 - 1) DOWNTO 1);
              EXIT;
            ELSE
              NEXT L22;
            END IF;
          END LOOP;--L22��Ϊ���ж���������������¥�����һ��

          LEVEL := LEVEL - 1;
          IF (DOWNREC2(LEVEL) = '1') THEN--������ʱ����
            PST <= OPENDOOR;
          ELSE
            IF (LEVEL <= JUDGE1) THEN
              PST <= STOPPING;
              LEVEL := LEVEL + 1;--������Ϊ�����������һ��Ӱ��
            ELSE
              PST <= DOWNING;
            END IF;
          END IF;

        WHEN OPENDOOR =>
          DOOR_LIGHT <= '1';
          CNT := CNT + 1;
          IF (CNT = 5) THEN
            CASE WAITT IS
              WHEN STOPPING => PST <= STOPPING;
              WHEN UPING => PST <= UPING;
              WHEN DOWNING => PST <= DOWNING;
              WHEN OTHERS => PST <= STOPPING;
            END CASE;
            CNT := 0;
            DOOR_LIGHT <= '0';
          END IF;--��һ״̬������һ���½�ͣվ������5��ʱ�ӵĿ���ʱ��

      END CASE;
    END IF;
  END PROCESS;
END BHV;