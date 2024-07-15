-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER second_nature_owner
WITH PASSWORD 'secondnature';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO second_nature_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO second_nature_owner;

CREATE USER second_nature_appuser
WITH PASSWORD 'secondnature';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO second_nature_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO second_nature_appuser;
