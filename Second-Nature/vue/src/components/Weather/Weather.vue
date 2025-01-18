<template>
  <h3 class="title">Weather Forecast for {{ cityName }}</h3>

  <div class="content">
    <div class="document-container">
      <div class="filter-section">
        <label
          @click="fetchWeatherForecast('Youngstown')"
          class="clickable-label"
          >Youngstown</label
        >

        <span class="separator"> | </span>

        <label
          @click="fetchWeatherForecast('Columbiana')"
          class="clickable-label"
          >Columbiana</label
        >
      </div>

      <div v-if="forecastData.length" class="forecast-list">
        <div
          v-for="period in forecastData"
          :key="period.number"
          class="forecast-item"
        >
          <hr />
          <h4>{{ period.name }}</h4>
          <span class="label">Temperature: </span>{{ period.temperature }}°{{
            period.temperatureUnit
          }}
          <br />
          <span class="label">Detailed Forecast: </span> <br />
          {{ period.detailedForecast }}
        </div>
      </div>

      <div v-if="loading" class="loading">
        <span>Loading forecast...</span>
      </div>

      <div v-if="error" class="error">
        <span>Error fetching weather data. Please try again later.</span>
      </div>
    </div>
  </div>
</template>
  
  <script>
export default {
  mounted() {
    this.fetchWeatherForecast(this.cityName);
  },
  data() {
    return {
      forecastData: [],
      loading: false,
      cityName: "Youngstown",
      error: false, 
    };
  },
  methods: {
    fetchWeatherForecast(city) {
      this.cityName = city;
      this.loading = true;
      this.error = false;

      const cityCoordinates = this.getCityCoordinates(city);

      if (!cityCoordinates) {
        this.loading = false;
        this.error = true;
        return;
      }

      fetch(
        `https://api.weather.gov/gridpoints/${cityCoordinates.gridId}/${cityCoordinates.x},${cityCoordinates.y}/forecast`,
        {
          headers: {
            "User-Agent": "(myweatherapp.com, contact@myweatherapp.com)",
          },
        }
      )
        .then((response) => response.json())
        .then((data) => {
          this.forecastData = data.properties.periods;
        })
        .catch((error) => {
          console.error("Error fetching forecast:", error);
          this.error = true; 
        })
        .finally(() => {
          this.loading = false;
        });
    },
    getCityCoordinates(city) {
      const cityCoordinates = {
        Columbiana: { gridId: "PBZ", x: 30, y: 60 },
        Youngstown: { gridId: "PBZ", x: 29, y: 61 },
      };
      return cityCoordinates[city] || null;
    },
  },
};
</script>
  
  <style scoped>
.clickable-label {
  color: #2a64bd;
  text-decoration: none;
}

.clickable-label:hover {
  cursor: pointer;
  text-decoration: underline;
}

.clickable-label:visited {
  color: #a1af9f;
}

.filter-section {
  width: 100%;
  text-align: center;
}

.separator {
  color: #333;
}

.forecast-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  width: 100%;
}

.forecast-item {
  width: 90%;
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.loading {
  text-align: center;
  font-size: 1.2em;
  color: #555;
}

.error {
  text-align: center;
  font-size: 1.2em;
  color: red;
}
.title {
  text-align: center;
}
</style>
  