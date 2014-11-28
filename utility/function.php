<?php
/* Menghilangkant anda baca */
function trimTandaBaca($input){
	$tandaBaca = array("`","\”","{","}","”","“",'”',",","'",".","/","?","\"","/","\\","-",":","(",")","  ");
	foreach ($tandaBaca as $key) {
		$input	=	str_replace($key, " ", $input);
	}
	return $input;
}

/* update berita */
function updateBerita($id,$kolom,$konten){
	$sql = "UPDATE artikel ".
	       "SET $kolom = '$konten' ".
	       "WHERE ID_ARTIKEL = $id" ;
	$retval = mysql_query($sql);
}

?>