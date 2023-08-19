import sys
import requests
import shutil


def main(argv):
    if len(argv) != 3 and len(argv[1] != 40):
        print('ERROR: INVALID TOKEN')
    else:
        try:
            host = 'https://app.agroapi.com.br'
            endpoint = '/api/data/image/satellite/aoi'
            url = host + endpoint
            token = 'Token '+argv[1]
            request_body = {
                    "aoi": {
                            "geometry": {
                                "type": "Polygon",
                                "coordinates": [
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
                        "date": argv[2],
                        "source": "SENTINEL-2",
                        "mode": "NDVI",
                        "legend": "true"
                    }
            request_headers = {
                "Accept": "image/png",
                "Content-Type": "application/json",
                "Authorization": token
            }
            r = requests.post(url, json=request_body , headers=request_headers, stream=True)
            print('ENDPOINT:', endpoint)
            print('STATUS CODE:', r.status_code)
            path = argv[2]+'-'+'ndvi.png'
            if r.status_code == 200:
                with open(path, 'wb') as f:
                    r.raw.decode_content = True
                    shutil.copyfileobj(r.raw, f)
                    print(path+' Image Downloaded Successfully')

        except Exception as e:
            print(e)
            print(type(e))


if __name__ == '__main__':
    main(sys.argv)
