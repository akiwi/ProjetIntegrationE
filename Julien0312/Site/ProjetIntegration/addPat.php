<?php
include "function.php";
$db = connexion();

if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['name'])) { 
		$name = $_POST['name'];
		$surname = $_POST['surname'];
		$note = $_POST['com'];
		addPatient($db,$name,$surname,$note);
	} else{
		echo " requète non compris !";
	}
}

deconnexion($db);
?>
