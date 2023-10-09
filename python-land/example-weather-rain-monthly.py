import sys
import requests


def main(argv):

    if len(argv) !=2 and len(argv[1] != 40):
        print('ERROR: INVALID TOKEN')
    else:
        try:
            host = 'https://app.agroapi.com.br'
            endpoint = '/api/data/weather/rain/monthly'
            url = host + endpoint
            token = 'Token ' + argv[1]

            poi = {'lat': -12.5474488, 'lon': -55.7269264}

            with open('rain.csv', 'w+') as f:

                for year in range(2017, 2024):
                    for month in range(1, 13):
                        r = requests.post(url, json={'poi': poi, 'date_year': year, 'date_month': month},
                                          headers={"Authorization": token})

                        if r.status_code != 200: raise ValueError(r.text)

                        data = r.json()['data']
                        print(data)
                        f.write(f"{'-'.join(data['date'].split('-')[0:-1])}, {data['value']}\n")
                        print(year, month)
        except Exception as e:
            print(e)
            print(type(e))


if __name__ == '__main__':
    main(sys.argv)

