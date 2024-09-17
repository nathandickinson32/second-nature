import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      equipmentDetailView : 'detail'
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_EQUIPMENT_DETAIL_VIEW(state, view) {
        state.equipmentDetailView = view;
      }
    },
    //Check if a logged-in user is a manager
    getters: {
      isManager(state) {
        return state.user.title === 'manager'; 

      },
      loggedInUserId(state) {
        return state.user.id;
      },
      getEquipmentDetailView(state) {
        return state.equipmentDetailView;
      },
      // check if someone is logged in
      isLoggedIn(state){
        return !!state.token;
      }
    }
  });
  return store;
}
