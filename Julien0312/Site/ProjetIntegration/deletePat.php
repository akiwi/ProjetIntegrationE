<?php
include "function.php";
$db = connexion();

if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['port'])) { 
		//deleteUser($db,$_POST['id']);
		deletePatient($db,$_POST['port']);
	} else{
		echo " requète non compris !";
	}
}

deconnexion($db);
?>
