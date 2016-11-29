DELIMITER $$

CREATE TRIGGER `majAlarmes_after_insert` AFTER INSERT ON `CareWatch`.`Alarme`
FOR EACH ROW BEGIN

SET @raison = NEW.raison;
SET @raison = REPLACE(@raison, ' ', '%');
SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/majAlarmes.py ', NEW.port,' ', NEW.heure, ' ', NEW.minute, ' ', @raison ));
END;
$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER `majAlarmes_after_update` AFTER UPDATE ON `CareWatch`.`Alarme`
FOR EACH ROW BEGIN

SET @raison = NEW.raison;
SET @raison = REPLACE(@raison, ' ', '%');
SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/majAlarmes.py ', NEW.port,' ', NEW.heure, ' ', NEW.minute, ' ', @raison, ' ', NEW.idAlarme));
END;
$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER `majAlarmes_after_delete` AFTER DELETE ON `CareWatch`.`Alarme`
FOR EACH ROW BEGIN
DECLARE x INT;
SET x = old.port;
SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/majAlarmes.py ', x, ' ', old.heure, ' ', old.minute, ' ', old.raison));
END;
$$

DELIMITER ;


DELIMITER $$

CREATE TRIGGER `majPresence` AFTER UPDATE ON `CareWatch`.`patient`
FOR EACH ROW BEGIN
	IF NEW.estPresent = 0 THEN
		SET @exec_var = sys_exec(CONCAT('python /home/pi/scriptPython/envoiNotifSortie.py ', new.port));
	END IF;
END;
$$

DELIMITER ;