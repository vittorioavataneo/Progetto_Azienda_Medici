INSERT INTO public.codice_medici(id_codice_medici, codice)
SELECT NEXTVAL('id_codice_medici_sequence'), '00000' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '11111' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '22222' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '33333' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '44444' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '55555' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '66666' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '77777' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '88888' UNION ALL
SELECT NEXTVAL('id_codice_medici_sequence'), '99999';

