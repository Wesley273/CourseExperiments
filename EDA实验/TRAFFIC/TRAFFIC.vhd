LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY TRAFFIC IS
     PORT (
          CLK, RST : IN STD_LOGIC;
          YM, GM, RM, YF, GF, RF : OUT STD_LOGIC
     );
END TRAFFIC;
ARCHITECTURE BHV OF TRAFFIC IS
     TYPE STATES IS(ST0, ST1, ST2, ST3);
     SIGNAL PST : STATES;
BEGIN
     PROCESS (CLK, RST)
          VARIABLE CNT : INTEGER RANGE 0 TO 45;
     BEGIN
          IF RST = '1' THEN
               PST <= ST0;
               CNT := 0;
          ELSIF RISING_EDGE(CLK) THEN
               CASE PST IS
                    WHEN ST0 =>
                         YM <= '0';
                         GM <= '1';
                         RM <= '0';
                         YF <= '0';
                         GF <= '0';
                         RF <= '1';
                         CNT := CNT + 1;
                         IF (CNT = 45) THEN
                              PST <= ST1;
                              CNT := 0;
                         END IF;

                    WHEN ST1 =>
                         YM <= '1';
                         GM <= '0';
                         RM <= '0';
                         YF <= '0';
                         GF <= '0';
                         RF <= '1';
                         CNT := CNT + 1;
                         IF (CNT = 5) THEN
                              PST <= ST2;
                              CNT := 0;
                         END IF;

                    WHEN ST2 =>
                         YM <= '0';
                         GM <= '0';
                         RM <= '1';
                         YF <= '0';
                         GF <= '1';
                         RF <= '0';
                         CNT := CNT + 1;
                         IF (CNT = 25) THEN
                              PST <= ST3;
                              CNT := 0;
                         END IF;

                    WHEN ST3 =>
                         YM <= '0';
                         GM <= '0';
                         RM <= '1';
                         YF <= '1';
                         GF <= '0';
                         RF <= '0';
                         CNT := CNT + 1;
                         IF (CNT = 5) THEN
                              PST <= ST0;
                              CNT := 0;
                         END IF;

                    WHEN OTHERS =>
                         PST <= ST0;
                         CNT := 0;
               END CASE;
          END IF;
     END PROCESS;
END BHV;