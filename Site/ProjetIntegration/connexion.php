<?php
include "function.php";
$db = connexion();
if(mysqli_connect_errno()){
	echo "Failled to connect to MySQL : ".mysqli_connect_errno();
	exit();
}
else{
	if(isset($_POST['nameUser']) && isset($_POST['pwdUser'])) {

		selectClient($db, $_POST['nameUser'], $_POST['pwdUser']);

	} elseif(isset($_POST['portPat'])){

		selectCalendar($db,$_POST['portPat']);

	} else{
		echo " requète non compris !";
	}
}

deconnexion($db);
?>
