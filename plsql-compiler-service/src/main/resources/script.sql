
CREATE OR REPLACE PACKAGE plsql_service IS
    v_sql_text varchar2(32000) := '';
    v_sql_clob clob;

    FUNCTION executeQuery(v_tableName varchar, v_query varchar, v_userId varchar) RETURN VARCHAR2;
    FUNCTION getMapperForTable(v_tableName varchar, v_id number) RETURN VARCHAR;
    PROCEDURE getMapperForstudenti(student studenti%rowtype);
    PROCEDURE getMapperFornote(nota note%rowtype);
    PROCEDURE getMapperForcursuri(curs cursuri%rowtype);
    PROCEDURE getMapperForprofesori(profesor profesori%rowtype);
END plsql_service;

CREATE OR REPLACE PACKAGE BODY plsql_service IS
    FUNCTION getMapperForTable(v_tableName varchar, v_id number) RETURN VARCHAR AS
        v_response varchar(32000) := '';
BEGIN
case (v_tableName)
            when 'studenti' then v_response := 'helloWorld'||v_id;
end case;
return v_response;
END getMapperForTable;

    FUNCTION executeQuery(v_tableName varchar, v_query varchar, v_userId varchar) RETURN VARCHAR2 AS
        v_execution varchar(30000) := 'Hello world PLSQL';
        l_sql VARCHAR(10000);
        v_rows number := 0;
BEGIN
select COUNT(*) into v_rows from tab where tname = UPPER(v_tableName);
if v_rows ~= 1 then
            raise_application_error(-20000, 'Table '||v_tableName||' doesn''t exists!');
end if;
        DBMS_OUTPUT.PUT_LINE(v_userId);

        l_sql := 'DECLARE '||
                 '   CURSOR c1 IS '||
                 '   '||v_query||' '||
                 '   r1 c1%ROWTYPE; '||
                 'BEGIN '||
                 '   FOR r1 IN c1 LOOP '||
                 '      plsql_service.getMapperFor'||v_tableName||'(r1); '||
                 '   END LOOP; '||
                 'END; ';
        v_sql_clob := '';
EXECUTE IMMEDIATE l_sql;
RETURN v_sql_clob;
END executeQuery;

    PROCEDURE getMapperForstudenti(student studenti%rowtype) AS
BEGIN
        v_sql_text := student.id||' '||student.nume||' '||student.prenume||' '||NVL(student.bursa, 0)||' '||student.email||'\n';
SELECT CONCAT(v_sql_clob, v_sql_text)  INTO v_sql_clob FROM DUAL;
END getMapperForstudenti;

    PROCEDURE getMapperFornote(nota note%rowtype) AS
BEGIN
        v_sql_text := nota.id||','||nota.id_student||','||nota.id_curs||','||nota.valoare||'\n';
SELECT CONCAT(v_sql_clob, v_sql_text)  INTO v_sql_clob FROM DUAL;
END getMapperFornote;

    PROCEDURE getMapperForcursuri(curs cursuri%rowtype) AS
BEGIN
        v_sql_text := curs.id||','||curs.titlu_curs||','||curs.an||','||curs.semestru||','||curs.credite||'\n';
SELECT CONCAT(v_sql_clob, v_sql_text)  INTO v_sql_clob FROM DUAL;
END getMapperForcursuri;
     PROCEDURE getMapperForprofesori(profesor profesori%rowtype) AS
BEGIN
        v_sql_text := profesor.id||','||profesor.nume||','||profesor.prenume||','||profesor.grad_didactic||'\n';
SELECT CONCAT(v_sql_clob, v_sql_text)  INTO v_sql_clob FROM DUAL;
END getMapperForprofesori;
END plsql_service;

declare
v_response varchar(10000);
begin
    v_response := plsql_service.executeQuery('note','SELECT * FROM note;', 'helloWorld');

    DBMS_OUTPUT.PUT_LINE(v_response);
end;
/