<?php
include "function.php";
$db = connexion();

if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['id'])) {
		if($_POST['id'] != 1){

			$id = $_POST['id'];
			$name = $_POST['name'];
			$pwd = $_POST['mdp'];
			$droit = $_POST['droit'];

			updateUser($db,$id,$name,$pwd,$droit);
		} 		
	} else{
		echo " requète non compris !";
	}
}

deconnexion($db);
?>
