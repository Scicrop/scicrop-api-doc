/*******************************************************************************
/*
/* Copyright [2017]	Jose Ricardo de Oliveira Damico
/* 					SciCrop Informacao e Tecnologia S.A.
/* 					info@scicrop.com / https://scicrop.com
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
/*
/********************************************************************************/

var request = require('request');
var site = 'engine.scicrop.com/scicrop-engine-web/api/v1';

module.exports.api_get = function(application, req, res){
  var acesso = {
    user: req.headers.username,
    pass: req.headers.password,
    api : req.headers.api
  }

  testGET(site,acesso, res);

};

module.exports.api_post = function(application, req, res){

  var acesso = {
    user: req.headers.username,
    pass: req.headers.password,
    api : req.headers.api
  }

  testePOST(site, acesso, res);

};


function testGET(site, acesso, res){
  var site_get = 'https://'+  acesso.user + ':' + acesso.pass + '@' + site + '/status/my';
  request(site_get, function (err, response, body) {
      if(error){
        res.json('erro');
        console.log(error);
      } else {
        if(response.statusCode == 200){
          res.json(body);
        }
      }
  });
}

function testePOST(site, acesso, res) {

  var site_post = 'https://'+  acesso.user + ':' + acesso.pass + '@'+ site + '/station/scicrop';
  var entity = "{'authEntity':{'userEntity':{'email':"+ acesso.username +", 'api_id': '"+acesso.api+"'}},'payloadEntity':{},responseEntity:{returnId:0}}";

  request.post({
    headers: {'content-type' : 'application/x-www-form-urlencoded'},
    url:     site_post,
    body:    entity
  }, function(err, response, body){
    if(err){
      res.json('erro');
      console.log(err);
    } else {
      if(response.statusCode == 200){
        res.json(body);
      }
    }

  });

}
