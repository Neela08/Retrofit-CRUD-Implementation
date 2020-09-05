<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
$con=mysqli_connect("localhost","user","pass","dbname");
$id=$_POST['id'];
$name=$_POST['name'];
$number=$_POST['number'];

$sql = "UPDATE callapp SET name='$name', number='$number' WHERE id='$id'";


	if(mysqli_query($con,$sql)){
		echo("Yes");
	}else{
		echo("No");
	}

}
 


?>