<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['username'], $_POST['approval'],$_POST['courseID']))
{
$username = $_POST['username'];
$approval = $_POST['approval'];
$courseID = $_POST['courseID'];
$query="UPDATE CourseRegistration SET approval='$approval' WHERE username='$username' and courseID='$courseID'";
    $post_submit = mysqli_query($con, $query) or die(mysqli_error($con));
if ($post_submit) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Data successfully Stored.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An Error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
}
?>