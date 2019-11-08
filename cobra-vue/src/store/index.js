import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
    state:{
        user:{}
    },
    mutations:{
        login(state,user){
            state.user = user;
            window.localStorage.setItem('user',JSON.stringify(user))
        }
    },
    actions:{}
});