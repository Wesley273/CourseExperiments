LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY ADDER1_B IS
     PORT (
          AIN : IN STD_LOGIC;
          BIN : IN STD_LOGIC;
          CIN : IN STD_LOGIC;
          COUTF : OUT STD_LOGIC;
          SUMF : OUT STD_LOGIC
     );
END ADDER1_B;
ARCHITECTURE ONE OF ADDER1_B IS
     COMPONENT HADDER1
          PORT (
               A : IN STD_LOGIC;
               B : IN STD_LOGIC;
               CO : OUT STD_LOGIC;
               SO : OUT STD_LOGIC
          );
     END COMPONENT;
     COMPONENT GATE_OR
          PORT (
               A : IN STD_LOGIC;
               B : IN STD_LOGIC;
               C : OUT STD_LOGIC
          );
     END COMPONENT;
     SIGNAL D, E, F : STD_LOGIC;
BEGIN
     U1 : HADDER1 PORT MAP(A => AIN, B => BIN, CO => D, SO => E);
     U2 : HADDER1 PORT MAP(A => E, B => CIN, CO => F, SO => SUMF);
     U3 : GATE_OR PORT MAP(A => D, B => F, C => COUTF);
END ONE;