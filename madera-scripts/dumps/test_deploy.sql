--
-- POC for deployment script
-- init database and push test data
--

DROP DATABASE madera;

CREATE DATABASE madera;

EXEC madera_dump.sql;
EXEC data_dump.sql;

COMMIT;