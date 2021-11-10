LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
ENTITY CNT29 IS
    PORT (
        CLK : IN STD_LOGIC;
        EN : IN STD_LOGIC := '0';
        RST : IN STD_LOGIC := '0';
        SG_OUT : OUT STD_LOGIC_VECTOR(15 DOWNTO 0);
        DATA_OUT : BUFFER STD_LOGIC_VECTOR(7 DOWNTO 0);
        GREEN : BUFFER STD_LOGIC := '0';
        RED : OUT STD_LOGIC := '0'
    );
END CNT29;
ARCHITECTURE BHV OF CNT29 IS
    SIGNAL CLKIN : STD_LOGIC;
    COMPONENT DECODER7 IS
        PORT (
            BIN_IN : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
            SG_OUT : OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
    END COMPONENT DECODER7;
    COMPONENT FDIVIDER IS
        PORT (
            CLK : IN STD_LOGIC; --50MHz
            CP2 : BUFFER STD_LOGIC := '0';
            CP1 : BUFFER STD_LOGIC := '0');
    END COMPONENT FDIVIDER;
BEGIN
    PROCESS (CLKIN, RST, EN)
        VARIABLE Q : STD_LOGIC_VECTOR(7 DOWNTO 0) := "00000000";
        VARIABLE TWINK : INTEGER RANGE 0 TO 5;
    BEGIN
        IF EN = '1' THEN
            IF RST = '1' THEN
                Q := "00000000";
            ELSE
                IF CLKIN'EVENT AND CLKIN = '1' THEN
                    IF Q = X"29" THEN
                        IF TWINK <= 5 THEN
                            GREEN <= NOT GREEN;
                            TWINK := TWINK + 1;
                        ELSE
                            RED <= '1';
                        END IF;
                    ELSE
                        IF Q(3 DOWNTO 0) < 9 THEN
                            Q(3 DOWNTO 0) := Q(3 DOWNTO 0) + 1;
                        ELSE
                            IF Q(7 DOWNTO 4) < 2 THEN
                                Q(7 DOWNTO 4) := Q(7 DOWNTO 4) + 1;
                            END IF;
                            Q(3 DOWNTO 0) := (OTHERS => '0');
                        END IF;
                    END IF;
                END IF;
            END IF;
        END IF;
        DATA_OUT <= Q;
    END PROCESS;
    GETCLK : FDIVIDER PORT MAP(CLK => CLK, CP2 => CLKIN);
    DECODER_HIGH : DECODER7 PORT MAP(BIN_IN => DATA_OUT(7 DOWNTO 4), SG_OUT => SG_OUT(15 DOWNTO 8));
    DECODER_LOW : DECODER7 PORT MAP(BIN_IN => DATA_OUT(3 DOWNTO 0), SG_OUT => SG_OUT(7 DOWNTO 0));
END BHV;