<?php
include "function.php";
$db = connexion();

if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['id'])) { 
		deleteUser($db,$_POST['id']);
	} else{
		echo " requète non compris !";
	}
}

deconnexion($db);
?>
