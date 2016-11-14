<?php
	include "function.php";

	$db = connexion();

	if(mysqli_connect_errno()){
		echo "Failled to connect to MySQL : ".mysqli_connect_errno();
		exit();
	}
	else{
		
		$heure = $_POST['heure'];
		$minute = $_POST['minute'];
		$port = $_POST['port'];
		$raison = $_POST['raison'];

		setAlarm($db,$heure,$minute,$port,$raison);
	}

	
	deconnexion($db);

?>
