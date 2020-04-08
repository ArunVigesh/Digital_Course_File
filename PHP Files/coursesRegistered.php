<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
$username = $_POST['username'];
$query="SELECT c.courseID,c.courseName from Courses c, CourseRegistration cr where c.courseID=cr.courseID and (cr.username='$username' and cr.approval=1)";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["courselist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $course = array();
            $course["courseName"] = $row["courseName"];
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