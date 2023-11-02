create or replace TRIGGER tr_Examenes_Upd
AFTER UPDATE ON examenes
FOR EACH ROW
DECLARE
    v_nota examenes.nnotaexam%TYPE;
    v_num_exa cursos.nnumexa%TYPE;
    v_nota_media NUMBER;
BEGIN

    select nnumexa into v_num_exa from cursos where ccodcurso = :NEW.ccodcurso;
    
    v_nota := :NEW.nnotaexam - :OLD.nnotaexam;
    v_nota_media := v_nota / v_num_exa;

    UPDATE matriculas
    SET nnotamedia = nnotamedia + v_nota_media
    WHERE ccodalu = :NEW.ccodalu AND ccodcurso = :NEW.ccodcurso;
END;


create or replace procedure SP_ALTAMATRICULA
(
    cod_alu in varchar,
    cod_curso in varchar
)
as
    num_exa number;
    examen number := 1;
begin
    select nnumexa into num_exa from cursos where ccodcurso = cod_curso;

    INSERT INTO matriculas(ccodalu, ccodcurso) VALUES (cod_alu, cod_curso);

    while examen <= num_exa loop
        insert into examenes (ccodalu, ccodcurso, nnumexam) values (cod_alu, cod_curso, examen);
        examen := examen + 1;
    end loop;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
end;