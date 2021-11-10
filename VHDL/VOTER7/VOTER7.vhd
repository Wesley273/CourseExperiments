LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY VOTER7 IS
    PORT (
        A, B, C, D, E, F, G : IN STD_LOGIC;
        Y : OUT STD_LOGIC);
END;
ARCHITECTURE BHV OF VOTER7 IS
BEGIN
    PROCESS (A, B, C, D, E, F, G)
        VARIABLE SUM : INTEGER RANGE 0 TO 7;
    BEGIN
        SUM := 0;
        IF A = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF B = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF C = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF D = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF E = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF F = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF G = '1' THEN
            SUM := SUM + 1;
        END IF;
        IF SUM >= 4 THEN
            Y <= '1';
        ELSE
            Y <= '0';
        END IF;
    END PROCESS;
END;