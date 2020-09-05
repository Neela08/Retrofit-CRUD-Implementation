<?php

class Constants
{
    //DATABASE DETAILS
    static $DB_SERVER="localhost";
    static $DB_NAME="dbname";
    static $USERNAME="user";
    static $PASSWORD="pass";

    //STATEMENTS
    static $SQL_SELECT_ALL="SELECT * FROM callapp";




}

class LoadData
{
    
    public function connect()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,Constants::$DB_NAME);
        if($con->connect_error)
        {
            // echo "Cant Connect";
            return null;
        }else
        {
            //echo "Connected";
            return $con;
        }
    }
  
    public function select()
    {
        $con=$this->connect();
        if($con != null)
        {
            $result=$con->query(Constants::$SQL_SELECT_ALL);
            if($result->num_rows>0)
            {
              $loaddata=array();
                while($row=$result->fetch_array())
                {
                    array_push($loaddata, array(
                   
                    "name"=>$row['name'],"number"=>$row['number'],"id"=>$row['id']));
                }
                print(json_encode(array_reverse($loaddata)));
            }else
            {
                print(json_encode(array("PHP EXCEPTION : CAN'T RETRIEVE FROM MYSQL. ")));
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
    }
}
$loaddata=new LoadData();
$loaddata->select();

