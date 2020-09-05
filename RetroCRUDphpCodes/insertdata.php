<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
$con=mysqli_connect("localhost","user","pass","dbname");
$name=$_POST['name'];
$number=$_POST['number'];


$sql="INSERT INTO callapp (name, number) VALUES ('".$name."','".$number."')";
	if(mysqli_query($con,$sql)){
		echo("Yes");
	}else{
		echo("No");
	}

}
 


?>