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

module.exports = function(application){
	application.get('/agroapi', function(req, res){
		application.app.controllers.agroapi.api_get(application, req, res);
	});

	application.post('/agroapi', function(req, res){
		application.app.controllers.agroapi.api_post(application, req, res);
	});

	application.all('/*', function(req, res, next) {
		res.header("Access-Control-Allow-Origin", "*");
		res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
		res.header('Access-Control-Allow-Headers', 'Origin, Content-type, API, USER, PASS');
		if (req.method == 'OPTIONS') {
			res.status(200).end();
		} else {
			next();
		}
	})

}
