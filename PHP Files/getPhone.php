<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
$username=$_POST['username'];
$query="SELECT phoneNo FROM Users where username='$username'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["phone"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $phoneno = array();
            $phoneno["phoneNo"] = $row["phoneNo"];
            array_push($response["phone"], $phoneno);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["phone"] = "";
        echo json_encode($response);
    }
?>