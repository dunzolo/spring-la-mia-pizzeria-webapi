<script>
import axios from 'axios';
import Pizza from '../components/Pizza.vue';

const BASE_API_URL = "http://localhost:8080/api/v1";
export default {
  components: {
    Pizza
  },
  data() {
    return {
      pizze: [],
      ricerca: "",
    }
  },
  methods: {
    getBooks() {
      axios.get(BASE_API_URL + "/pizze")
        .then(res => {
          const pizze = res.data;
          this.pizze = pizze;
        })
        .catch(err => console.log(err));
    },
    filter() {
      axios.get(BASE_API_URL + "/pizza" + "?nome=" + this.ricerca)
        .then(res => {
          const pizze = res.data;
          this.pizze = pizze;
        })
        .catch(err => console.log(err));
    },
  },
  mounted() {
    this.getBooks();
  }
}
</script>

<template>
  <main>
    <h1>Le mie pizze</h1>
    <router-link to="/pizza/create">Aggiugi una nuova pizza</router-link>
    <div>
      <input type="text" v-model="ricerca" @keyup="filter()">
    </div>
    <Pizza v-for="pizza in pizze" :pizza="pizza" />
  </main>
</template>
