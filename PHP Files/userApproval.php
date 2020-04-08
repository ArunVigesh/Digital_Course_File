<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
$query="SELECT firstName,lastName,username,department,email,phoneNo FROM Users where  approval=0";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["userlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $user = array();
            $user["firstName"] = $row["firstName"];
            $user["lastName"] = $row["lastName"];
            $user["userName"] = $row["username"];
            $user["department"] = $row["department"];
            $user["email"] = $row["email"];
            $user["phone"] = $row["phoneNo"];
            array_push($response["userlist"], $user);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["userlist"] = "";
        echo json_encode($response);
    }
?>