LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY FDIVIDER IS
    PORT (
        CLK : IN STD_LOGIC; --50MHz
        CP1 : BUFFER STD_LOGIC := '0'; --1Hz
        CP2 : BUFFER STD_LOGIC := '0');--2Hz
END FDIVIDER;
ARCHITECTURE BHV OF FDIVIDER IS
BEGIN
    PROCESS (CLK) --50MHz
        VARIABLE T0 : INTEGER RANGE 0 TO 24999999 := 0; --1Hz
        VARIABLE T1 : INTEGER RANGE 0 TO 12499999 := 0; --2Hz
    BEGIN
        IF (CLK'event AND CLK = '1') THEN
            IF (T0 = 24999999) THEN
                T0 := 0;
                CP1 <= NOT CP1;
            ELSE
                T0 := T0 + 1;
            END IF;
            IF (T1 = 12499999) THEN
                T1 := 0;
                CP2 <= NOT CP2;
            ELSE
                T1 := T1 + 1;
            END IF;
        END IF;
    END PROCESS;
END BHV;