<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['userName'], $_POST['password']))
{
    $userName = $_POST['userName'];
    $password = $_POST['password'];
    $query="SELECT * FROM Users where username='$userName' and password='$password' and (approval='1' or approval ='3')";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["getlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $pLogin = array();
            $pLogin["userName"] = $row["username"];
            array_push($response["getlist"], $pLogin);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["getlist"] = "";
        echo json_encode($response);
    }
}

?>