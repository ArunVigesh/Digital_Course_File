<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['username']))
{
$username = $_POST['username'];
$query="SELECT fileName FROM Files where username='$username'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["fileslist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $course = array();
            $course["fileName"] = $row["fileName"];
            array_push($response["fileslist"], $course);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["fileslist"] = "";
        echo json_encode($response);
    }
}
?>