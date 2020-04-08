<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['username'],$_POST['fileName']))
{
$username = $_POST['username'];
$fileName=$_POST['fileName'];

$query="SELECT courseID,fileType,fileUrl FROM Files where username='$username'and fileName='$fileName'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["fileslist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $course = array();
            $course["fileType"] = $row["fileType"];
            $course["courseID"] = $row["courseID"];
            $course["fileUrl"] = $row["fileUrl"];
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