<?php
###################################################################
# Pembersih Tanda Baca (PTB)
# Deskripsi: script sederhana membesihkan tanda baca dari text
#
# author: a.dwisaty4@yahoo.com
##################################################################
	set_time_limit(0);

	require_once("function.php");
	$conn = mysql_connect("localhost","root","");
	mysql_select_db("news_aggregator");


	/* Bagian iterasi */
	$start = 0;
	$end	=	1001;
	for($id=$start;$id<=$end;$id++){
		$query	=	mysql_query("SELECT JUDUL,FULL_TEXT FROM artikel WHERE ID_ARTIKEL='$id'");
		$hasil_eksekusi = mysql_fetch_array($query);
		updateBerita($id,"FULL_TEXT",trimTandaBaca($hasil_eksekusi['FULL_TEXT']));
		updateBerita($id,"JUDUL",trimTandaBaca($hasil_eksekusi['JUDUL']));
	}

	print "Record ke-$start hingga ke-$end sudah dibersihkan";

?>