<?php

	function getIni(){
		$fichier = "config/config.ini";
		return parse_ini_file($fichier,1);
	}

	function connexion(){
		$tab = getIni();

		$host   = $tab['DB']['ip'];
		$user  = $tab['DB']['loginDB'];
		$pdw   = $tab['DB']['passDB'];
		$db = $tab['DB']['nameDB'];
	
		$connect = new mysqli($host,$user,$pdw,$db);

		return $connect;

	}

	function selectClient($db,$user,$pwd){
		$query = "SELECT Id FROM CLIENT WHERE nameUser = '".$user."' AND pwdUser = '".$pwd."'";

		$result = $db->query($query);

		$find = $result->num_rows;

		if($find > 0 ){
			$result->data_seek(0);

			$row = $result->fetch_row();
			echo(json_encode($row));
		}
		else{
			echo json_encode("-1");
		}


	}

	function deconnexion($db){

		$db->close();
	}
?>