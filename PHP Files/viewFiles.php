<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['username']))
{
$username = $_POST['username'];
$query="SELECT courseID,fileName,fileType,fileUrl FROM Files where username='$username'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["fileslist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $files = array();
            $files["courseID"] = $row["courseID"];
            $files["fileName"] = $row["fileName"];
            $files["fileUrl"] = $row["fileUrl"];
            $files["fileType"] = $row["fileType"];
            
            array_push($response["fileslist"], $files);
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