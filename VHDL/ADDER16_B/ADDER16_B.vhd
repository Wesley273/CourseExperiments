LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY ADDER16_B IS
     PORT (
          A : IN STD_LOGIC_VECTOR(15 DOWNTO 0);
          B : IN STD_LOGIC_VECTOR(15 DOWNTO 0);
          CIN : IN STD_LOGIC;
          SUM : OUT STD_LOGIC_VECTOR(15 DOWNTO 0);
          COUT : OUT STD_LOGIC
     );
END;
ARCHITECTURE ONE OF ADDER16_B IS
     COMPONENT ADDER1_B
          PORT (
               AIN : IN STD_LOGIC;
               BIN : IN STD_LOGIC;
               CIN : IN STD_LOGIC;
               COUTF : OUT STD_LOGIC;
               SUMF : OUT STD_LOGIC
          );
     END COMPONENT;
     SIGNAL DATA : STD_LOGIC_VECTOR(16 DOWNTO 0);
BEGIN
     DATA(0) <= CIN;
     ADD : FOR I IN 0 TO 15 GENERATE
          LOOPADD : ADDER1_B PORT MAP(
               AIN => A(I),
               BIN => B(I),
               CIN => DATA(I),
               COUTF => DATA(I + 1),
               SUMF => SUM(I)
          );
     END GENERATE ADD;
     COUT <= DATA(16);