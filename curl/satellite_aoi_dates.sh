curl -X 'POST' \
  'https://app.agroapi.com.br/api/data/image/satellite/aoi/dates' \
  -H 'accept: application/json' \
  -H 'Authorization: Token {your-token}' \
  -H 'Content-Type: application/json' \
  -d '{
   "aoi":{
      "geometry":{
         "type":"Polygon",
         "coordinates":[
            [
               [
                  -49.38726069127057,
                  -25.401838511292922
               ],
               [
                  -49.38726069127057,
                  -25.40535115133693
               ],
               [
                  -49.3837083460175,
                  -25.40535115133693
               ],
               [
                  -49.3837083460175,
                  -25.401838511292922
               ],
               [
                  -49.38726069127057,
                  -25.401838511292922
               ]
            ]
         ]
      }
   },
  "date": "2023-01-30",
  "offset": 0,
  "limit": 100,
  "source": "SENTINEL-2"
}'
