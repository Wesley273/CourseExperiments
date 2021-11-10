LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
ENTITY DECODER7 IS
    PORT (
        BIN_IN : IN STD_LOGIC_VECTOR(3 DOWNTO 0); --输入四位BCD码
        SG_OUT : OUT STD_LOGIC_VECTOR(7 DOWNTO 0)); --0~7分别对应a,b,c,d,e,f,g,db
END;
ARCHITECTURE BHV OF DECODER7 IS
BEGIN
    PROCESS (BIN_IN)
    BEGIN
        CASE BIN_IN IS
            WHEN "0000" => SG_OUT <= X"C0";
            WHEN "0001" => SG_OUT <= X"F9";
            WHEN "0010" => SG_OUT <= X"A4";
            WHEN "0011" => SG_OUT <= X"B0";
            WHEN "0100" => SG_OUT <= X"99";
            WHEN "0101" => SG_OUT <= X"92";
            WHEN "0110" => SG_OUT <= X"82";
            WHEN "0111" => SG_OUT <= X"F8";
            WHEN "1000" => SG_OUT <= X"80";
            WHEN "1001" => SG_OUT <= X"90";
            WHEN "1010" => SG_OUT <= X"88";
            WHEN "1011" => SG_OUT <= X"83";
            WHEN "1100" => SG_OUT <= X"C6";
            WHEN "1101" => SG_OUT <= X"A1";
            WHEN "1110" => SG_OUT <= X"86";
            WHEN "1111" => SG_OUT <= X"8E";
            WHEN OTHERS => NULL;
        END CASE;
    END PROCESS;
END;