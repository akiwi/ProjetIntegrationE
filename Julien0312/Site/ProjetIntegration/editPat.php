<?php
include "function.php";
$db = connexion();

if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['port'])){
		$port = $_POST['port'];
		$name = $_POST['name'];
		$surname = $_POST['surname'];
		$note = $_POST['note'];

		updatePatient($db,$port,$name,$surname,$note);
	}
	else{
		echo " requète non compris !";
	}
	
}

deconnexion($db);
?>
