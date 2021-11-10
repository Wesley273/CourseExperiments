LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY GENBUCKCODE IS
    PORT (
        CLK : IN STD_LOGIC;
        BUCKOUT : OUT STD_LOGIC;
        BEATOUT : BUFFER STD_LOGIC := '0'
    );
END GENBUCKCODE;
ARCHITECTURE BHV OF GENBUCKCODE IS
BEGIN
    PROCESS (CLK)
        VARIABLE BUCK : STD_LOGIC_VECTOR(7 DOWNTO 0) := "01110010";
    BEGIN
        IF CLK'EVENT AND CLK = '1' THEN
            BUCKOUT <= BUCK(6);
            BUCK(7 DOWNTO 1) := BUCK(6 DOWNTO 0);
            BUCK(0) := BUCK(7);
        END IF;
    END PROCESS;
    BEATOUT <= CLK;
END BHV;