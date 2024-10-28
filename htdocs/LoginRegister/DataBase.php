<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $emailid, $password)
    {
        $emailid = $this->prepareData($emailid);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $emailid . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $emailid && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $firstname, $lastname, $middlename, $gender , $dob, $mobile , $emailid , $password)
    {
        $firstname = $this->prepareData($firstname);
        $lastname = $this->prepareData($lastname);
        $middlename = $this->prepareData($middlename);
        $gender = $this->prepareData($gender);
        $dob = $this->prepareData($dob);
        $dob = $this->prepareData($dob);
        $mobile = $this->prepareData($mobile);
        $emailid = $this->prepareData($emailid);
        $password = password_hash($password,PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (firstname, lastname, middlename, gender, dob, mobile, username, password) VALUES ('" . $firstname . "','" . $lastname . "','" . $middlename . "','" . $gender . "','" . $dob . "','" . $mobile . "','" . $emailid . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>
