<?php
$response = array();
$con = mysqli_connect("localhost", "id12643325_dcf", "files", "id12643325_dcf") or die(mysqli_error($con));
if (isset($_POST['username']))
{
$username = $_POST['username'];
$query="SELECT courseID , feedback FROM UserFeedback where username='$username'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["feedbacklist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $feedback = array();
            $feedback["courseID"] = $row["courseID"];
            $feedback["feedback"] = $row["feedback"];
            array_push($response["feedbacklist"], $feedback);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["feedbacklist"] = "";
        echo json_encode($response);
    }
}
?>