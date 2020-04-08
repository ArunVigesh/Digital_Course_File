<?php
ini_set('display_errors', 1); ini_set('display_startup_errors', 1); error_reporting(E_ALL);
if (isset($_POST['file_path']))
{
$file_path =$_POST['file_path'];
    if(unlink($file_path)) {
        echo "success";
    } else{
        echo "$file_path";
    }
}
?>