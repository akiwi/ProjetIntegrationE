DELIMITER $$

CREATE TRIGGER `majAlarmes_after_insert` AFTER INSERT ON `test`.`Alarme`
FOR EACH ROW BEGIN

SET @raison = NEW.raison;
SET @raison = REPLACE(@raison, ' ', '%');
SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/majAlarmes.py ', NEW.port,' ', NEW.heure, ' ', NEW.minute, ' ', @raison ));
END;
$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER `majAlarmes_after_update` AFTER UPDATE ON `test`.`Alarme`
FOR EACH ROW BEGIN

SET @raison = NEW.raison;
SET @raison = REPLACE(@raison, ' ', '%');
SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/majAlarmes.py ', NEW.port,' ', NEW.heure, ' ', NEW.minute, ' ', @raison, ' ', NEW.idAlarme));
END;
$$

DELIMITER ;