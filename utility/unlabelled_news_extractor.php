<?php
###################################################################
# Unlabelled News Extractor (UNE)
# Deskripsi: script sederhana untuk mengekstrak berita yang belum berlabel
# + bisa bikin text untuk arff
# author: a.dwisaty4@yahoo.com
##################################################################
	set_time_limit(0);

	$conn = mysql_connect("localhost","root","");
	mysql_select_db("news_aggregator");

	$start = 0;
	$end	=	100;
	//$sql	=	"SELECT ID_ARTIKEL,JUDUL,FULL_TEXT from artikel WHERE ID_ARTIKEL NOT IN (SELECT ID_ARTIKEL from artikel_kategori_verified) AND (ID_ARTIKEL <= $end) AND (ID_ARTIKEL >= $start)";
	$sql	=	"SELECT ID_ARTIKEL,JUDUL,FULL_TEXT from artikel WHERE ID_ARTIKEL NOT IN (SELECT ID_ARTIKEL from artikel_kategori_verified)";

	$hasil	=	mysql_query($sql);
	
	echo "@relation weather.symbolic<br>";
	echo "@attribute text<br>";

	echo "@data<br>";
	while($hasil_eksekusi = mysql_fetch_array($hasil)) {
		# code...
		echo "\"".$hasil_eksekusi['JUDUL']." ".$hasil_eksekusi['FULL_TEXT']."\","."?";
		echo "<br>";
	}
?>