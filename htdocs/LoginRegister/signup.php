<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password']))  {
    if ($db->dbConnect()) {
        if ($db->signUp("registration", $_POST['firstname'], $_POST['lastname'], $_POST['middlename'], $_POST['gender']
            ,$_POST['dob'],$_POST['mobile'],$_POST['username'],$_POST['password'])) {
            echo "Registration Successful";
        } else echo "Username already exists";
    } else echo "Error: Database connection";
} else echo "All fields are required !";
?>
