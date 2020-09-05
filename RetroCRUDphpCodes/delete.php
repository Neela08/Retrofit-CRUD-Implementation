<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
$con=mysqli_connect("localhost","user","pass","dbname");
$id=$_POST['id'];




$sql="DELETE FROM callapp WHERE id='$id'";
	if(mysqli_query($con,$sql)){
		echo("Yes");
	}else{
		echo("No");
	}

}
 


?>