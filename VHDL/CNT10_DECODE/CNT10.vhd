LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY CNT10 IS
    PORT (
        CLK, RST, EN, LOAD : IN STD_LOGIC;
        DATA : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
        DOUT : OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
        COUT : OUT STD_LOGIC);
END CNT10;
ARCHITECTURE BHV OF CNT10 IS
BEGIN
    PROCESS (CLK, RST, EN, LOAD) --50MHz
        VARIABLE Q : STD_LOGIC_VECTOR(3 DOWNTO 0);
    BEGIN
        IF RST = '0' THEN
            Q := (OTHERS => '0');
        ELSIF CLK'EVENT AND CLK = '1' THEN
            IF EN = '1' THEN
                IF (LOAD = '0') THEN
                    Q := DATA;
                ELSE
                    IF Q < 9 THEN
                        Q := Q + 1;
                    ELSE
                        Q := (OTHERS => '0');
                    END IF;
                END IF;
            END IF;
        END IF;
        IF Q = "1001" THEN
            COUT <= '1';
        ELSE
            COUT <= '0';
        END IF;
        DOUT <= Q;
    END PROCESS;
END BHV;