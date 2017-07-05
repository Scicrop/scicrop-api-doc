<?php
/*******************************************************************************
/*
/* Copyright [2017]	Jose Ricardo de Oliveira Damico
/* 			SciCrop Informacao e Tecnologia S.A.
/* 			info@scicrop.com / https://scicrop.com
/*
/* Licensed under the Apache License, Version 2.0 (the "License");
/* you may not use this file except in compliance with the License.
/* You may obtain a copy of the License at
/*
/*     http://www.apache.org/licenses/LICENSE-2.0
/*
/* Unless required by applicable law or agreed to in writing, software
/* distributed under the License is distributed on an "AS IS" BASIS,
/* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
/* See the License for the specific language governing permissions and
/* limitations under the License.
*
/******************************************************************************/

    $username = $_GET["username"];
    $password = $_GET["password"];
    $api_id = $_GET["api_id"];

    $getResponse = processRequest("/status/my", null, "GET", $username, $password);


    echo("<h1>getResponse</h1><br>");


    var_dump($getResponse);

    $sciCropEntity = "{'authEntity':{'userEntity':{'email':$username, 'api_id': $api_id}},'payloadEntity':{},responseEntity:{returnId:0}}";

    $postResponse = processRequest("/station/scicrop", $sciCropEntity, "POST", $username, $password);

    echo("<h1>postResponse</h1><br>");

    var_dump($postResponse);


    function processRequest($path_string, $sciCropEntity, $method, $username, $password){


      $scicrop_json = json_encode($sciCropEntity);

      $url = "https://engine.scicrop.com/scicrop-engine-web/api/v1".$path_string;

      $ch = curl_init();
      curl_setopt($ch, CURLOPT_URL, $url);
      curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/xml', $additionalHeaders));
      curl_setopt($ch, CURLOPT_HEADER, 1);
      curl_setopt($ch, CURLOPT_USERPWD, $username . ":" . $password);
      if($method == 'POST') curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/x-www-form-urlencoded','Content-Length: ' . strlen($scicrop_json)));
      curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);
      if($method == 'POST') curl_setopt($ch, CURLOPT_POSTFIELDS,$scicrop_json);
      curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

      $response  = curl_exec($ch);

      curl_close();

      return $response;
    }


?>
