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
		$id = $result->fetch_row();
		//$arrUser = array("id" => $row[0]); //récupération info de l'user

		$sql = "SELECT port,nom,prenom,estPresent,note FROM patient";
		$res = $db->query($sql);
		$res->data_seek(0);
		//$res = $res->fetch_row();
		$arrPatients = [];
		while($row = $res->fetch_assoc()) {// récupération de la liste des patients
			$arrPatients[$row['port']] = array("Nom" =>$row['nom'],"Prenom"=>$row['prenom'],"estPresent"=>$row['estPresent'],"note"=>$row['note']);
		}
		$arrFinal = array("id"=>$id[0],"infoPatients"=>$arrPatients);
		echo(json_encode($arrFinal));
	}
	else{
		$arr = array("id" => "-1");
		echo(json_encode($arr));
	}
}
function deconnexion($db){
	$db->close();
}
?>
