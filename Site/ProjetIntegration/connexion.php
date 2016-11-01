<?php
	include "function.php";

	$db = connexion();

	if(mysqli_connect_errno()){
		echo "Failled to connect to MySQL : ".mysqli_connect_errno();
		exit();
	}
	else{
		$user = $_POST['nameUser'];
		$pwd = $_POST['pwdUser'];
		selectClient($db, $user, $pwd);
	}

	
	deconnexion($db);

?>
