--
-- User peluquerias
--

create user 'peluquerias'@'localhost' identified by 'nichufia';
GRANT SELECT, INSERT, UPDATE, DELETE ON `peluquerias`.* TO 'peluquerias'@'localhost';
