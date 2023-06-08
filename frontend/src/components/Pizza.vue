<script>
import axios from 'axios';
const BASE_API_URL = "http://localhost:8080/api/v1";

export default {
  props: {
    pizza: {}
  },
  data() {
    return {
      deleted: false,
      open: false
    }
  },
  methods: {
    deletePizza(id) {
      axios.delete(BASE_API_URL + "/pizza/" + id)
        .then(res => this.deleted = true)
        .catch(err => console.log(err));
    }
  }
}
</script>
<template lang="">
  <ul>
    <li v-if="!deleted">
      <span @click="open = !open">{{ pizza.nome }} - {{ pizza.prezzo }}€</span>
      <button @click="deletePizza(pizza.id)">X</button>
      <div v-if="open">
        {{ pizza.descrizione }}
      </div>
    </li>
  </ul>
</template>

<style lang="">

</style>