<?php
###################################################################
# Unlabelled News Extractor (UNE)
# Deskripsi: script sederhana untuk mengekstrak berita yang belum berlabel
#
# author: a.dwisaty4@yahoo.com
##################################################################
	set_time_limit(0);

	$conn = mysql_connect("localhost","root","");
	mysql_select_db("news_aggregator");

	$start = 0;
	$end	=	100;
	$sql	=	"SELECT ID_ARTIKEL,JUDUL from artikel WHERE ID_ARTIKEL NOT IN (SELECT ID_ARTIKEL from artikel_kategori_verified) AND (ID_ARTIKEL <= $end) AND (ID_ARTIKEL >= $start)";
	$hasil	=	mysql_query($sql);
	while($hasil_eksekusi = mysql_fetch_array($hasil)) {
		# code...
		echo $hasil_eksekusi['ID_ARTIKEL']."         |     ".$hasil_eksekusi['JUDUL'];
		echo "<br>";
	}
?>