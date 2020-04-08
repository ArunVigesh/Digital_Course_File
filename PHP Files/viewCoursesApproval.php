<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));

$query="SELECT username,courseID FROM CourseRegistration where approval='0' ";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["courselist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $course = array();
            $course["username"] = $row["username"];
            $course["courseID"] = $row["courseID"];
            array_push($response["courselist"], $course);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["courselist"] = "";
        echo json_encode($response);
    }

?>