<script>
import axios from 'axios';
const BASE_API_URL = "http://localhost:8080/api/v1";
export default {
  data() {
    return {
      pizza: {
        nome: "",
        descrizione: "",
        foto: "",
        prezzo: ""
      }
    }
  },
  methods: {
    createPizza() {
      axios.post(BASE_API_URL + "/pizza", this.pizza)
        .then(res => {
          this.$router.push('/');
        })
        .catch(err => console.log(err));
    }
  },
}
</script>

<template lang="">
  <div>
    <h1>Crea una nuova pizza</h1>
    <form @submit.prevent="createPizza">
      <label for="name">Nome</label><br>
      <input type="text" name="nome" v-model="pizza.nome">
      <br>
      <label for="descrizione">Descrizione</label><br>
      <input type="text" name="descrizione" v-model="pizza.descrizione">
      <br>
      <label for="foto">URL Foto</label><br>
      <input type="text" name="foto" v-model="pizza.foto">
      <br>
      <label for="prezzo">Prezzo</label><br>
      <input type="number" name="prezzo" v-model="pizza.prezzo">
      <br>
      <input type="submit" value="CREATE">
    </form>
  </div>
</template>

<style lang="">

</style>