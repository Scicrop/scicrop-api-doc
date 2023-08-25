 async cloud(aoi: Object, date: Date) {
    try {

      const response = await axios.post(`https://app.agroapi.com.br/api/data/image/satellite/aoi`,
        {
          "mode": "SCL",
          "source": "SENTINEL-2",
          "date": date,
          "aoi": aoi,
          "crop": true
        },
        { headers: { "authorization": `Token ${token}`, 'content-type': "application/json", 'accept': 'application/json' } });

      return response.data;
    } catch (error) {
      console.error(error);
      return false;
    }
  }

  async ndviBase64(aoi: Object, date: Date) {
    try {

      const response = await axios.post(`https://app.agroapi.com.br/api/data/image/satellite/aoi`,
        {
          "mode": "NDVI",
          "source": "SENTINEL-2",
          "date": date,
          "aoi": aoi,
          "legend": false,
          "crop": true
        },
        { headers: { "authorization": `Token ${token}`, 'content-type': "application/json", 'accept': 'text/plain;base64' } });

      return response.data;
    } catch (error) {
      console.error(error);
      return false;
    }
  }

  async ndvi(aoi: Object, date: Date) {
    try {

      const response = await axios.post(`https://app.agroapi.com.br/api/data/image/satellite/zonal-statistic/aoi`,
        {
          "mode": "NDVI",
          "metric": "mean",
          "epsg": 4326,
          "source": "SENTINEL-2",
          "date": date,
          "aoi": aoi

        },
        { headers: { "authorization": `Token ${token}`, 'content-type': "application/json", 'accept': 'application/geo+json' } });

      return response.data;
    } catch (error) {
      console.error(error);
      return false;
    }
  }

  async ndviDates(aoi: Object) {
    try {

      const response = await axios.post(`https://app.agroapi.com.br/api/data/image/satellite/aoi/dates`,
        {
          "date": "9999-12-31",
          "source": "SENTINEL-2",
          "offset": 0,
          "limit": 100000,
          "aoi": aoi
        },
        { headers: { "authorization": `Token ${token}`, 'content-type': "application/json", 'accept': 'application/json' } });

      return response.data.data;
    } catch (error) {
      console.error(error);
      return false;
    }
  }