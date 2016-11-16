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
	$q= "SET NAMES 'utf8'";
	$result = $db->query($q);
	
	$query = "SELECT idClient FROM CLIENT WHERE nameUser = '".$user."' AND pwdUser = '".$pwd."'";
	$result = $db->query($query);
	$find = $result->num_rows;
	if($find > 0 ){
		//force à travailler en utf8 avec la DB ;-)
		$result->data_seek(0);
		$id = $result->fetch_row();
		//$arrUser = array("id" => $row[0]); //récupération info de l'user

		$sql = "SELECT port,nom,prenom,estPresent,note FROM patient";
		$res = $db->query($sql);
		$res->data_seek(0);

		$arrPatients = [];
		while($row = $res->fetch_assoc()) {// récupération de la liste des patients
			$arrPatients[] = array("port"=>$row['port'],"Nom" =>$row['nom'],"Prenom"=>$row['prenom'],"estPresent"=>$row['estPresent'],"note"=>$row['note']);
			$resAla = $db->query($sql);
			$resAla->data_seek(0);

		}

		$arrFinal = array("id"=>$id[0],"infoPatients"=>$arrPatients);
		echo(json_encode($arrFinal));
	}
	else{
		$arr = array("id" => "-1");
		echo(json_encode($arr));
	}
}
function selectCalendar($db,$port){
	$q= "SET NAMES 'utf8'";
	$result = $db->query($q);

	$query = "SELECT * FROM `sortie` WHERE `port` ='".$port."'";
	$result = $db->query($query);
	$result->data_seek(0);

	$arrSortie = [];
	while($row = $result->fetch_assoc()) {// récupération de la liste des patients
		$arrSortie[] = array("raison" => $row['raison'],"DateSortie" => $row['DateSortie'],"DateRentrer" => $row['DateRentrer'],"idSortie"=> $row['idSortie']);
	}

	$query = "SELECT * FROM `alarme` WHERE `port` ='".$port."'";
	$result = $db->query($query);
	$result->data_seek(0);

	$arrAlarme = [];
	while($row = $result->fetch_assoc()) {// récupération de la liste des patients
		$arrAlarme[] = array("raison" => $row['raison'],"heure" => $row['heure'],"minute" => $row['minute'],"idAlarme"=> $row['idAlarme']);
	}
	$arrFinal = array("alarme"=>$arrAlarme,"sortie"=>$arrSortie);
	echo (json_encode($arrFinal));
}
function setAlarm($db,$heure,$minute,$port,$raison)
{
	$insert = "INSERT INTO alarme (heure,minute,raison,port) VALUES('" . $heure . "','" . $minute . "','" . $raison . "','" . $port . "')";
	$result = $db->query($insert);
	if ($result) {
		$arr = array("id" => "1");
		echo(json_encode($arr));
	} else {
		$arr = array("id" => "-1");
		echo(json_encode($arr));
	}
}
function deconnexion($db){
	$db->close();
}
?>
